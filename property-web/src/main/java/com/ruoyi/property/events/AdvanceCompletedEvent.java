package com.ruoyi.property.events;

import com.ruoyi.property.domain.Advance;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AdvanceCompletedEvent {
    private Advance advance;
    private String transactionId;
    private String outTradeNo;
    private BigDecimal paidAmount;
    private Date paidAt;
}
