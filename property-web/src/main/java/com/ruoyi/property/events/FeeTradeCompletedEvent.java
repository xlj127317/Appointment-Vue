package com.ruoyi.property.events;

import com.ruoyi.property.dto.FeeTradeOutputDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FeeTradeCompletedEvent {
    private String id;
    private String bizChannel;
    private String outScope;
    private String outId;
    private BigDecimal paidAmount;
    private Date paidAt;
}
