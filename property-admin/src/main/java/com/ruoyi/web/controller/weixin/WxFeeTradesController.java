package com.ruoyi.web.controller.weixin;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.FeeTradeState;
import com.ruoyi.property.domain.Deposit;
import com.ruoyi.property.domain.FeeTrade;
import com.ruoyi.property.domain.Wallet;
import com.ruoyi.property.dto.wx.FeeTradeOutputDto;
import com.ruoyi.property.service.IDepositService;
import com.ruoyi.property.service.IEasyTrService;
import com.ruoyi.property.service.IFeeTradeService;
import com.ruoyi.property.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{tradeNo}")
    public FeeTradeOutputDto getFeeTrade(@PathVariable String tradeNo)
    {
        Map trade = feeTradeService.getTradeByNo(tradeNo);
        FeeTradeOutputDto dto = BeanUtil.mapToBean(trade, FeeTradeOutputDto.class, false, new CopyOptions());
        return dto;
    }

    @PreAuthorize("@ss.hasPermi('property:feetrades:paydeposit')")
    @PostMapping("/pay/deposit")
    public AjaxResult depositPay(@RequestBody Map params)
    {
        TransactionStatus transactionStatus = transactionManager.getTransaction(null);

        try {
            String ownerId = easyTrService.userIdToOwnerId(getUserId(), 2);
            String tradeNo = (String)params.get("tradeNo");

            Map queryTradeParams = new HashMap();
            queryTradeParams.put("no", tradeNo);
            queryTradeParams.put("ownerId", ownerId);
            queryTradeParams.put("withLock", 2);
            FeeTrade trade = BeanUtil.mapToBean(
                    feeTradeService.getTrade(queryTradeParams),
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
}
