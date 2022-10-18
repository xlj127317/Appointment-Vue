package com.ruoyi.property.dto.wx;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ruoyi.common.enums.FeeTradeState;
import com.ruoyi.common.enums.serialization.FeeTradeStateSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FeeTradeOutputDto {
    private String id;
    private String no;
    private String subject;
    private String description;
    private BigDecimal amount;

    @JsonSerialize(using = FeeTradeStateSerializer.class)
    private FeeTradeState state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date paidAt;

    private BigDecimal paidAmount;

    private String bizChannel;
}
