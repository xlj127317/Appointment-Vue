package com.ruoyi.property.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AggregatedSumDto {
    private String name;
    private BigDecimal amount;
}
