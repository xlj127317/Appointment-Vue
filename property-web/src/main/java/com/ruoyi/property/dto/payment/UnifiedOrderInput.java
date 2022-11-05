package com.ruoyi.property.dto.payment;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UnifiedOrderInput {
    private String body;
    private String attach;
    private String outTradeNo;
    private BigDecimal totalFee;
    private String spbillCreateIp;
    private String openId;
    private UnifiedOrderExtra extra;

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private UnifiedOrderInput input;

        public Builder() {
            input = new UnifiedOrderInput();
        }

        public Builder body(String body) {
            input.setBody(body);
            return this;
        }

        public Builder attach(String attach) {
            input.setAttach(attach);
            return this;
        }

        public Builder outTradeNo(String outTradeNo) {
            input.setOutTradeNo(outTradeNo);
            return this;
        }

        public Builder totalFee(BigDecimal totalFee) {
            input.setTotalFee(totalFee);
            return this;
        }

        public Builder spbillCreateIp(String spbillCreateIp) {
            input.setSpbillCreateIp(spbillCreateIp);
            return this;
        }

        public Builder openId(String openId) {
            input.setOpenId(openId);
            return this;
        }

        public Builder extra(UnifiedOrderExtra extra) {
            input.setExtra(extra);
            return this;
        }

        public Builder extra(String topic, Object data) {
            UnifiedOrderExtra extra = new UnifiedOrderExtra();
            extra.setTopic(topic);
            extra.setData(data);
            return extra(extra);
        }

        public UnifiedOrderInput build() {
            return input;
        }
    }
}
