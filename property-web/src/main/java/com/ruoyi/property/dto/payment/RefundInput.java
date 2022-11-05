package com.ruoyi.property.dto.payment;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RefundInput {
    private String transactionId;
    private BigDecimal totalFee;
    private BigDecimal refundFee;
    private String refundDesc;

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private RefundInput input;

        public Builder() {
            input = new RefundInput();
        }

        public Builder transactionId(String transactionId) {
            input.setTransactionId(transactionId);
            return this;
        }

        public Builder totalFee(BigDecimal totalFee) {
            input.setTotalFee(totalFee);
            return this;
        }

        public Builder refundFee(BigDecimal refundFee) {
            input.setRefundFee(refundFee);
            return this;
        }

        public Builder refundDesc(String refundDesc) {
            input.setRefundDesc(refundDesc);
            return this;
        }

        public RefundInput build() {
            return input;
        }
    }
}
