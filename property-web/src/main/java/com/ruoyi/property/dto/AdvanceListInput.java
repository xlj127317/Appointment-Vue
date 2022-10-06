package com.ruoyi.property.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AdvanceListInput {
    private String contractId;
    private String paymentType;
    private String paymentName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date advanceDate;

    private Integer deductStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTimeBegin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTimeEnd;
}
