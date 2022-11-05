package com.ruoyi.property.dto.payment;

import lombok.Data;

@Data
public class UnifiedOrderOutput {
    private String signType;
    private String paySign;
    private String nonceStr;
    private String packageStr;
    private String timeStamp;
}
