package com.ruoyi.property.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.json.XML;
import com.github.binarywang.wxpay.bean.businesscircle.RefundResult;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;
import com.ruoyi.common.config.WxConfig;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.dto.payment.*;
import com.ruoyi.property.service.IPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements IPaymentService {
    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private RedisCache cache;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UnifiedOrderOutput unifiedOrder(UnifiedOrderInput input) throws WxPayException {
        WxPayUnifiedOrderRequest request = WxPayUnifiedOrderRequest.newBuilder()
                .body(input.getBody())
                .attach(input.getAttach())
                .outTradeNo(input.getOutTradeNo())
                .totalFee(input.getTotalFee().movePointRight(2).intValue())
                .spbillCreateIp(input.getSpbillCreateIp())
                .openid(input.getOpenId())
                .notifyUrl(wxConfig.getPayNotifyUrl())
                .tradeType(WxPayConstants.TradeType.JSAPI)
                .build();
        request.setSignType(WxPayConstants.SignType.MD5);

        if (input.getExtra() != null) {
            cache.setCacheObject(
                    getExtraCacheKey(request.getOutTradeNo()),
                    input.getExtra(),
                    1,
                    TimeUnit.DAYS);
        }

        WxPayUnifiedOrderResult result = wxPayService.unifiedOrder(request);

        UnifiedOrderOutput output = new UnifiedOrderOutput();
        output.setSignType(request.getSignType());
        output.setPaySign(result.getSign());
        output.setNonceStr(result.getNonceStr());
        output.setPackageStr("prepay_id=" + result.getPrepayId());
        output.setTimeStamp(Long.toString(DateUtil.currentSeconds()));

        Map signParams = BeanUtil.beanToMap(output);
        signParams.remove("paySign");
        signParams.put("package", signParams.get("packageStr"));
        signParams.remove("packageStr");
        signParams.put("appId", wxConfig.getAppId());

        output.setPaySign(SignUtils.createSign(signParams, WxPayConstants.SignType.MD5, wxConfig.getMchKey(), null));

        printDebugPayNotifyXml(request);

        return output;
    }

    public UnifiedOrderRequest unifiedOrder() {
        return new UnifiedOrderRequestImpl();
    }

    @Override
    public RefundOutput refund(RefundInput input) throws WxPayException {
        WxPayRefundRequest request = WxPayRefundRequest
                .newBuilder()
                .transactionId(input.getTransactionId())
                .outRefundNo(PkeyGenerator.getUniqueString())
                .totalFee(input.getTotalFee().movePointRight(2).intValue())
                .refundFee(input.getRefundFee().movePointRight(2).intValue())
                .refundDesc(input.getRefundDesc())
                .notifyUrl(wxConfig.getRefundNotifyUrl())
                .build();
        WxPayRefundResult result = wxPayService.refundV2(request);
        RefundOutput output = new RefundOutput();
        output.setOutRefundNo(result.getOutRefundNo());
        return output;
    }

    @Override
    public RefundRequest refund() {
        return new RefundRequestImpl();
    }

    @Override
    public UnifiedOrderExtra getUnifiedOrderExtra(String outTradeNo) {
        return cache.getCacheObject(getExtraCacheKey(outTradeNo));
    }

    @Override
    public void deleteUnifiedOrderExtra(String outTradeNo) {
        cache.deleteObject(getExtraCacheKey(outTradeNo));
    }

    private static String getExtraCacheKey(String outTradeNo) {
        return String.format("Payment:%s:Extra", outTradeNo);
    }

    private class UnifiedOrderRequestImpl implements UnifiedOrderRequest {
        private UnifiedOrderInput.Builder builder;

        public UnifiedOrderRequestImpl() {
            builder = UnifiedOrderInput.newBuilder();
        }

        @Override
        public UnifiedOrderRequest body(String body) {
            builder.body(body);
            return this;
        }

        @Override
        public UnifiedOrderRequest attach(String attach) {
            builder.attach(attach);
            return this;
        }

        @Override
        public UnifiedOrderRequest outTradeNo(String outTradeNo) {
            builder.outTradeNo(outTradeNo);
            return this;
        }

        @Override
        public UnifiedOrderRequest totalFee(BigDecimal totalFee) {
            builder.totalFee(totalFee);
            return this;
        }

        @Override
        public UnifiedOrderRequest spbillCreateIp(String spbillCreateIp) {
            builder.spbillCreateIp(spbillCreateIp);
            return this;
        }

        @Override
        public UnifiedOrderRequest openId(String openId) {
            builder.openId(openId);
            return this;
        }

        @Override
        public UnifiedOrderRequest extra(UnifiedOrderExtra extra) {
            builder.extra(extra);
            return this;
        }

        @Override
        public UnifiedOrderRequest extra(String topic, Object data) {
            UnifiedOrderExtra extra = new UnifiedOrderExtra();
            extra.setTopic(topic);
            extra.setData(data);
            return extra(extra);
        }

        @Override
        public UnifiedOrderOutput send() throws WxPayException {
            return PaymentServiceImpl.this.unifiedOrder(builder.build());
        }
    }

    private class RefundRequestImpl implements  RefundRequest {
        private RefundInput.Builder builder = RefundInput.newBuilder();

        @Override
        public RefundRequest transactionId(String transactionId) {
            builder.transactionId(transactionId);
            return this;
        }

        @Override
        public RefundRequest totalFee(BigDecimal totalFee) {
            builder.totalFee(totalFee);
            return this;
        }

        @Override
        public RefundRequest refundFee(BigDecimal refundFee) {
            builder.refundFee(refundFee);
            return this;
        }

        @Override
        public RefundRequest refundDesc(String refundDesc) {
            builder.refundDesc(refundDesc);
            return this;
        }

        @Override
        public RefundOutput send() throws WxPayException {
            return PaymentServiceImpl.this.refund(builder.build());
        }
    }

    private void printDebugPayNotifyXml(WxPayUnifiedOrderRequest request) {
        Map data = new HashMap();
        data.put("appid", "1");
        data.put("attach", request.getAttach());
        data.put("bank_type", "CFT");
        data.put("fee_type", "CNY");
        data.put("is_subscribe", "Y");
        data.put("mch_id", "");
        data.put("nonce_str", "");
        data.put("openid", request.getOpenid());
        data.put("out_trade_no", request.getOutTradeNo());
        data.put("result_code", "SUCCESS");
        data.put("return_code", "SUCCESS");
        data.put("sign", "");
        data.put("time_end", DateUtil.format(new Date(), "yyyyMMddHHmmss"));
        data.put("total_fee", request.getTotalFee().toString());
        data.put("cash_fee", request.getTotalFee().toString());
        data.put("coupon_fee", "0");
        data.put("coupon_count", "0");
        data.put("coupon_type", "CASH");
        data.put("coupon_id", null);
        data.put("trade_type", "JSAPI");
        data.put("transaction_id", "thisistesttransactionid");
        logger.debug("DebugPayNotifyXml:\n" + XmlUtil.mapToXmlStr(data, "xml", "", true, true));
    }
}
