package com.ruoyi.property.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AdvanceOutput {
    private String id;
    private String contractId;
    private String paymentType;
    private String paymentName;
    private String paymentContent;
    private String advanceMoney;
    private int payType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date advanceDate;

    private Integer deductStatus;
    private Date deductTime;
    private String creatorName;
    private Date createTime;
}
