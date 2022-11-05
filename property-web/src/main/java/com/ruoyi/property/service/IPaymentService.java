package com.ruoyi.property.service;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.ruoyi.property.dto.payment.*;

import java.math.BigDecimal;

public interface IPaymentService {
    UnifiedOrderOutput unifiedOrder(UnifiedOrderInput input) throws WxPayException;
    UnifiedOrderRequest unifiedOrder();
    RefundOutput refund(RefundInput input) throws WxPayException;
    RefundRequest refund();
    UnifiedOrderExtra getUnifiedOrderExtra(String outTradeNo);
    void deleteUnifiedOrderExtra(String outTradeNo);

    interface UnifiedOrderRequest {
        UnifiedOrderRequest body(String body);
        UnifiedOrderRequest attach(String attach);
        UnifiedOrderRequest outTradeNo(String outTradeNo);
        UnifiedOrderRequest totalFee(BigDecimal totalFee);
        UnifiedOrderRequest spbillCreateIp(String spbillCreateIp);
        UnifiedOrderRequest openId(String openId);
        UnifiedOrderRequest extra(UnifiedOrderExtra extra);
        UnifiedOrderRequest extra(String topic, Object data);
        UnifiedOrderOutput send() throws WxPayException;
    }

    interface RefundRequest {
        RefundRequest transactionId(String transactionId);
        RefundRequest totalFee(BigDecimal totalFee);
        RefundRequest refundFee(BigDecimal refundFee);
        RefundRequest refundDesc(String refundDesc);
        RefundOutput send() throws WxPayException;
    }
}
