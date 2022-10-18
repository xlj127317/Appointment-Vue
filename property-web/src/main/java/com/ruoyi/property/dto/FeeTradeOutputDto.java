package com.ruoyi.property.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class FeeTradeOutputDto extends com.ruoyi.property.dto.wx.FeeTradeOutputDto {
    private String contractId;
    private String ownerId;
    private String outScope;
    private String outId;
    private String creatorNickname;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
