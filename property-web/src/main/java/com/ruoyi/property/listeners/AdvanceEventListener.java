package com.ruoyi.property.listeners;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.ruoyi.common.config.WxConfig;
import com.ruoyi.common.enums.FeeTradeState;
import com.ruoyi.common.enums.SqlLockMode;
import com.ruoyi.property.domain.Advance;
import com.ruoyi.property.domain.Contract;
import com.ruoyi.property.domain.FeeTrade;
import com.ruoyi.property.domain.PaymentType;
import com.ruoyi.property.events.AdvanceCompletedEvent;
import com.ruoyi.property.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class AdvanceEventListener {
    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private IPaymentTypeService paymentTypeService;

    @Autowired
    private IAdvanceService advanceService;

    @Autowired
    private IReceivableService receivableService;

    @Autowired
    private IContractService contractService;

    @Autowired
    private IFeeTradeService feeTradeService;

    @Autowired
    PlatformTransactionManager transactionManager;

    @EventListener
    public void onCompleteEvent(AdvanceCompletedEvent event) throws WxPayException {
        try {
            unsafeComplete(event);
        } catch (Exception exception) {
            refund(event, exception.getMessage());
        }

        if (wxConfig.isUseSandbox()) {
            Advance advance = event.getAdvance();
            String message = String.format(
                    "已开始沙盒模式，支付的账单[预付单号：%s]自动发起退款",
                    advance.getId());
            refund(event, message);
        }
    }

    private void unsafeComplete(AdvanceCompletedEvent event) throws Exception {
        Advance advance = event.getAdvance();
        Contract contract = contractService.newQuery()
                .id(advance.getContractId())
                .ownerId(advance.getOwnerId())
                .lockMode(SqlLockMode.SHARE)
                .findOne();
        if (contract == null) {
            throw new Exception("合同不存在");
        }

        PaymentType paymentType = paymentTypeService.selectPaymentTypeById(advance.getPaymentType());
        if (paymentType == null) {
            throw new Exception("款项类别不存在");
        }

        if (advanceService.exists(advance.getContractId(), paymentType.getId(), advance.getAdvanceDate())) {
            throw new Exception("此款项该月份已有预收费用，无需重复缴纳");
        }

        if (receivableService.exists(advance.getContractId(), paymentType.getId(), advance.getAdvanceDate())) {
            throw new Exception("此款项已生成应收记录，请直接前往应收记录缴费");
        }

        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        advance.setPayType(FeeTrade.BIZ_CHANNEL_WEIXIN_PAY);

        try {
            advanceService.insertAdvance(advance);
            Map feeTradeCreateParams = new HashMap<String, Object>();
            feeTradeCreateParams.put("ownerId", contract.getOwnerId());
            feeTradeCreateParams.put("subject", advance.getPaymentName());
            feeTradeCreateParams.put("description", advance.getPaymentContent());
            feeTradeCreateParams.put("price", event.getPaidAmount());
            feeTradeCreateParams.put("count", BigDecimal.ONE);
            feeTradeCreateParams.put("outScope", "advance");
            feeTradeCreateParams.put("outId", advance.getId());
            feeTradeCreateParams.put("contractId", advance.getContractId());
            FeeTrade feeTrade = feeTradeService.createTrade(feeTradeCreateParams);

            Map params = new HashMap();
            params.put("id", feeTrade.getId());
            params.put("paidAmount", feeTrade.getAmount());
            params.put("bizChannel", FeeTrade.BIZ_CHANNEL_WEIXIN_PAY);
            params.put("bizTradeNo", event.getOutTradeNo());
            params.put("stateSucceedValue", FeeTradeState.SUCCEED.getValue());
            feeTradeService.completeTrade(params);

            transactionManager.commit(transactionStatus);
        } catch (Exception exception) {
            transactionManager.rollback(transactionStatus);
        }
    }

    private void refund(AdvanceCompletedEvent event, String refundDesc) throws WxPayException {
        paymentService.refund()
                .transactionId(event.getTransactionId())
                .totalFee(event.getPaidAmount())
                .refundFee(event.getPaidAmount())
                .refundDesc(refundDesc)
                .send();
    }
}
