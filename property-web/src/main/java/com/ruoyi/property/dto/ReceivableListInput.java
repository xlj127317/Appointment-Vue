package com.ruoyi.property.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ReceivableListInput {
    private String contractId;
    private String paymentId;
    private String paymentName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date paymentDate;
    private Integer advanceStatus;
    private Integer actuallyStatus;
    private Integer arrearsStatus;
    private Integer paymentType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date actuallyDateBegin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date actuallyDateEnd;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTimeBegin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTimeEnd;
}
