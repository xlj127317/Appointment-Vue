package com.ruoyi.property.dto.wx;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AdvanceCreateInput {
    private String contractId;
    private String paymentTypeId;
    private String paymentName;
    private String paymentContent;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date advanceDate;
    private BigDecimal amount;
}
