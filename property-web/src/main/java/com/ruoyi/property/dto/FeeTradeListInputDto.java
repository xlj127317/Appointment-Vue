package com.ruoyi.property.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FeeTradeListInputDto {
    private String contractId;
    private String outScope;
    private String outId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date paidAtBegin;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date paidAtEnd;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeBegin;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeEnd;
}
