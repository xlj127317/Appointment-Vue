package com.ruoyi.web.controller.weixin;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateUtil;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.service.WxPayService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.DbLockStrength;
import com.ruoyi.common.enums.FeeTradeState;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.domain.Deposit;
import com.ruoyi.property.domain.FeeTrade;
import com.ruoyi.property.dto.wx.FeeTradeOutputDto;
import com.ruoyi.property.dto.wx.UnifiedOrderInputDto;
import com.ruoyi.property.dto.wx.UnifiedOrderOutputDto;
import com.ruoyi.property.service.IDepositService;
import com.ruoyi.property.service.IEasyTrService;
import com.ruoyi.property.service.IFeeTradeService;
import com.ruoyi.property.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hcx/property/wx/feetrades")
public class WxFeeTradesController extends BaseController {
    @Autowired
    private IFeeTradeService feeTradeService;

    @Autowired
    private IWalletService walletService;

    @Autowired
    private IEasyTrService easyTrService;

    @Autowired
    private IDepositService depositService;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @PreAuthorize("@ss.hasPermi('property:feetrades:list')")
    @GetMapping("")
    public TableDataInfo list() throws Exception {
        String ownerId = easyTrService.mustUserIdToOwnerId(getUserId());
        startPage();
        List<Map> trades = feeTradeService.listUserTrades(ownerId);
        List<FeeTradeOutputDto> output = trades.stream()
                .map(m -> BeanUtil.mapToBean(m, FeeTradeOutputDto.class, false, new CopyOptions()))
                .collect(Collectors.toList());
        return getDataTable(output);
    }

    @GetMapping("/getFeeTrade")
    public FeeTradeOutputDto getFeeTrade(@RequestParam String tradeNo) {
        Map trade = feeTradeService.getTradeByNo(tradeNo);
        FeeTradeOutputDto dto = BeanUtil.mapToBean(trade, FeeTradeOutputDto.class, false, new CopyOptions());
        return dto;
    }

    @PreAuthorize("@ss.hasPermi('property:feetrades:paydeposit')")
    @PostMapping("/pay/deposit")
    public AjaxResult depositPay(@RequestBody Map params) {
        TransactionStatus transactionStatus = transactionManager.getTransaction(null);

        try {
            String ownerId = easyTrService.userIdToOwnerId(getUserId(), 2);
            String tradeNo = (String) params.get("tradeNo");

            FeeTrade trade = BeanUtil.mapToBean(
                    feeTradeService.getOwnedTradeByNo(ownerId, tradeNo, DbLockStrength.UPDATE),
                    FeeTrade.class,
                    false,
                    CopyOptions.create());
            if (trade == null) {
                throw new Exception("账单不存在");
            }
            if (trade.getState() != FeeTradeState.WAIT_PAY) {
                throw new Exception("当前订单不是待支付状态");
            }

            Map queryDepositParams = new HashMap();
            queryDepositParams.put("user_id", ownerId);
            queryDepositParams.put("contractId", trade.getContractId());
            Deposit deposit = depositService.get(queryDepositParams);
            if (deposit == null) {
                throw new Exception("合同押金不足抵扣");
            }

            BigDecimal depositAmount = new BigDecimal(deposit.getDepositMoney());
            if (depositAmount.compareTo(trade.getAmount()) < 0) {
                throw new Exception("合同押金不足抵扣");
            }

            depositAmount = depositAmount.subtract(trade.getAmount());
            deposit.setDepositMoney(depositAmount.toPlainString());
            depositService.updateDeposit(deposit);

            Map updateFeeTradeParams = new HashMap();
            updateFeeTradeParams.put("paidAmount", trade.getAmount());
            updateFeeTradeParams.put("bizChannel", FeeTrade.BIZ_CHANNEL_CONTRACT_DEPOSIT);
            updateFeeTradeParams.put("id", trade.getId());
            int ret = feeTradeService.completeTrade(updateFeeTradeParams);
            if (ret == 0) {
                throw new Exception("无法完成支付，款项自动原路退回");
            }

            transactionManager.commit(transactionStatus);
        } catch (Exception exception) {
            transactionManager.rollback(transactionStatus);
            return error(exception.getMessage());
        }

        return toAjax(true);
    }

    private final String TENANT_ID = "1600304800";

    @Autowired
    private WxPayService wxPayService;

    @PostMapping("/unifiedOrder")
    public UnifiedOrderOutputDto unifiedOrder(
            @RequestBody UnifiedOrderInputDto input,
            HttpServletRequest request) throws Exception {
        if (input.getNo() == null) {
            throw new Exception("账单号不能为空");
        }
        String wxOpenId = getUserId();
        // wxOpenId = "oyM1S41LqTlqnbX_oIK5hjr70Efw";
        String ownerId = easyTrService.mustUserIdToOwnerId(wxOpenId);
        Map feeTradeMap = feeTradeService.getOwnedTradeByNo(ownerId, input.getNo(), DbLockStrength.SHARE);
        if (feeTradeMap == null) {
            throw new Exception("账单不存在");
        }

        String remoteAddr = request.getRemoteAddr();

        FeeTradeOutputDto feeTrade = BeanUtil.mapToBean(feeTradeMap, FeeTradeOutputDto.class, true, CopyOptions.create());

        WxPayUnifiedOrderRequest wxRequest = WxPayUnifiedOrderRequest.newBuilder()
                .body(feeTrade.getSubject() + "-" + feeTrade.getDescription())
                .attach(feeTrade.getId())
                .outTradeNo(PkeyGenerator.getUniqueString())
                .totalFee(feeTrade.getAmount().multiply(new BigDecimal(100)).intValueExact())
                .notifyUrl("https://dev.property.hcxtec.com/hcx/property/wx/pay/notify/completeOrder")
                .spbillCreateIp(remoteAddr)
                .tradeType(WxPayConstants.TradeType.JSAPI)
                .openid(wxOpenId)
                .build();
        wxRequest.setSignType(WxPayConstants.SignType.MD5);
        WxPayUnifiedOrderResult result = wxPayService.unifiedOrder(wxRequest);

        UnifiedOrderOutputDto output = new UnifiedOrderOutputDto();
        output.setSignType(wxRequest.getSignType());
        output.setPaySign(result.getSign());
        output.setNonceStr(result.getNonceStr());
        output.setPackageStr("prepay_id=" + result.getPrepayId());
        output.setTimestamp(DateUtil.currentSeconds());
        return output;
    }
}
