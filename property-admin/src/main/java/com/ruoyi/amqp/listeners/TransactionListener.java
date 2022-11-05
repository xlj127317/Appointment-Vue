package com.ruoyi.amqp.listeners;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateUtil;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.rabbitmq.client.Channel;
import com.ruoyi.common.config.RabbitConfig;
import com.ruoyi.common.config.WxConfig;
import com.ruoyi.common.enums.SqlLockMode;
import com.ruoyi.common.enums.FeeTradeState;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.domain.Advance;
import com.ruoyi.property.domain.FeeTrade;
import com.ruoyi.property.dto.FeeTradeOutputDto;
import com.ruoyi.property.dto.payment.UnifiedOrderExtra;
import com.ruoyi.property.dto.wx.PayNotifyDto;
import com.ruoyi.property.events.AdvanceCompletedEvent;
import com.ruoyi.property.events.FeeTradeCompletedEvent;
import com.ruoyi.property.service.IFeeTradeService;
import com.ruoyi.property.service.IPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
@RabbitListener(
        queues = RabbitConfig.TRANSACTION_QUEUE
)
public class TransactionListener {
    @Autowired
    private IFeeTradeService feeTradeService;

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private WxConfig wxConfig;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void handleMessage(PayNotifyDto event, Channel channel, Message message) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            handlePayNotify(event);
            channel.basicAck(deliveryTag, false);
        } catch (Exception exception) {
            logger.warn(exception.getMessage());
            channel.basicReject(deliveryTag, true);
        }
    }

    private void handlePayNotify(PayNotifyDto event) throws WxPayException {
        UnifiedOrderExtra unifiedOrderExtra = paymentService.getUnifiedOrderExtra(event.getOutTradeNo());
        if (unifiedOrderExtra != null && unifiedOrderExtra.getTopic().equals("advance")) {
            AdvanceCompletedEvent advanceCompletedEvent = new AdvanceCompletedEvent();
            advanceCompletedEvent.setAdvance(unifiedOrderExtra.toObject());
            advanceCompletedEvent.setTransactionId(event.getTransactionId());
            advanceCompletedEvent.setOutTradeNo(event.getOutTradeNo());
            advanceCompletedEvent.setPaidAmount(BigDecimal.valueOf(event.getCashFee(), 2));
            advanceCompletedEvent.setPaidAt(DateUtil.parse(event.getTimeEnd(), "yyyyMMddHHmmss"));
            eventPublisher.publishEvent(advanceCompletedEvent);
            paymentService.deleteUnifiedOrderExtra(event.getOutTradeNo());
            return;
        }
        String feeTradeNo = event.getAttach();
        Map feeTradeMap = feeTradeService.getTradeById(feeTradeNo, SqlLockMode.UPDATE);
        FeeTradeOutputDto feeTrade = BeanUtil.mapToBean(feeTradeMap, FeeTradeOutputDto.class, true, CopyOptions.create());
        if (feeTrade.getState() != FeeTradeState.WAIT_PAY) {
            logger.warn("账单[{}]已支付，关联支付单号{}，自动发起退款", feeTradeNo, event.getOutTradeNo());
            WxPayRefundRequest request = WxPayRefundRequest
                    .newBuilder()
                    .transactionId(event.getTransactionId())
                    .outRefundNo(PkeyGenerator.getUniqueString())
                    .totalFee(event.getTotalFee())
                    .refundFee(event.getCashFee())
                    .refundDesc(String.format("账单[%s]不可重复支付", feeTradeNo))
                    .notifyUrl(wxConfig.getRefundNotifyUrl())
                    .build();
            wxPayService.refundV2(request);
            return;
        }

        if (wxConfig.isUseSandbox()) {
            logger.warn("已开始沙盒模式，支付的账单[内部账单号：{}，支付单号：{}]自动发起退款", feeTradeNo, event.getOutTradeNo());
            WxPayRefundRequest request = WxPayRefundRequest
                    .newBuilder()
                    .transactionId(event.getTransactionId())
                    .outRefundNo(PkeyGenerator.getUniqueString())
                    .totalFee(event.getTotalFee())
                    .refundFee(event.getCashFee())
                    .refundDesc(String.format("沙盒模式账单[%s]", feeTradeNo))
                    .notifyUrl(wxConfig.getRefundNotifyUrl())
                    .build();
            wxPayService.refundV2(request);
        }

        Map params = new HashMap();
        params.put("id", feeTrade.getId());
        params.put("paidAmount", feeTrade.getAmount());
        params.put("bizChannel", FeeTrade.BIZ_CHANNEL_WEIXIN_PAY);
        params.put("bizTradeNo", event.getOutTradeNo());
        params.put("stateSucceedValue", FeeTradeState.SUCCEED.getValue());
        feeTradeService.completeTrade(params);

        BigDecimal paidAmount = BigDecimal.valueOf(event.getCashFee(), 2);

        FeeTradeCompletedEvent feeTradeCompletedEvent = new FeeTradeCompletedEvent();
        feeTradeCompletedEvent.setId(feeTrade.getId());
        feeTradeCompletedEvent.setBizChannel(feeTrade.getBizChannel());
        feeTradeCompletedEvent.setOutScope(feeTrade.getOutScope());
        feeTradeCompletedEvent.setOutId(feeTrade.getOutId());
        feeTradeCompletedEvent.setPaidAmount(paidAmount);
        feeTradeCompletedEvent.setPaidAt(DateUtil.parse(event.getTimeEnd(), "yyyyMMddHHmmss"));
        eventPublisher.publishEvent(feeTradeCompletedEvent);
    }
}
