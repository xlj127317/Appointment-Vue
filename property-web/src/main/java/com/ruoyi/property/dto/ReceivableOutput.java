package com.ruoyi.property.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ReceivableOutput {
    private String id;
    private String contractId;
    private String ownerId;
    private String paymentId;
    private String paymentIdStr;
    private String paymentName;
    private String paymentContent;
    private String receivableMoney;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date paymentDate;
    private Integer advanceStatus;
    private String deductMoney;
    private String deductAfterMoney;

    private Date deductDate;

    private Integer actuallyStatus;

    private String actuallyMoney;
    private Date actuallyDate;
    private Date stopTime;
    private Integer arrearsStatus;
    private String arrearsMoney;

    private Date repairDate;

    private Integer paymentType;
    private Integer refundStatus;

    private String creatorName;
    private Date createTime;
}
