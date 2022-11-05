package com.ruoyi.property.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AggregatedSumDto {
    private int index;
    private BigDecimal amount;
}
