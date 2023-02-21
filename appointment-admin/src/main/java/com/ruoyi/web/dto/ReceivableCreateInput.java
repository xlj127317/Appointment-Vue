package com.ruoyi.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ReceivableCreateInput {
    private String id;
    private String contractId;
    private String paymentTypeId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date paymentDate;
    private String paymentName;
    private BigDecimal receivableMoney;
    private String paymentContent;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date stopTime;

    private BigDecimal itemPrice;
    private BigDecimal itemCount;
}
