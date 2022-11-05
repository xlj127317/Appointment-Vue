package com.ruoyi.property.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NamedAggregatedSumDto {
    private int index;
    private String name;
    private BigDecimal amount;
}
