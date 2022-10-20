package com.ruoyi.property.dto.wx;

import lombok.Data;

@Data
public class PayNotifyDto {
    private String attach;
    private int totalFee;
    private int cashFee;
    private String openId;
    private String outTradeNo;
    private String transactionId;
    private String resultCode;
    private String errCode;
    private String errCodeDes;
    private String timeEnd;
}
