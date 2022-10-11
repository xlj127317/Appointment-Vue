package com.ruoyi.web.controller.weixin;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.FeeTradeState;
import com.ruoyi.property.domain.FeeTrade;
import com.ruoyi.property.domain.Wallet;
import com.ruoyi.property.dto.wx.FeeTradeOutputDto;
import com.ruoyi.property.service.IEasyTrService;
import com.ruoyi.property.service.IFeeTradeService;
import com.ruoyi.property.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.*;

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
            FeeTrade trade = feeTradeService.getTrade(queryTradeParams, 2);
            if (trade.getState() != FeeTradeState.WAIT_PAY) {
                throw new Exception("当前订单不是待支付状态");
            }

            Wallet wallet = walletService.getForUpdate(getUserId());
            if (wallet.getBalance().compareTo(trade.getAmount()) < 0) {
                throw new Exception("押金余额不足抵扣");
            }
        } catch (Exception exception) {
            transactionManager.rollback(transactionStatus);
            return error(exception.getMessage());
        }

        transactionManager.commit(transactionStatus);

        return toAjax(true);
    }
}
