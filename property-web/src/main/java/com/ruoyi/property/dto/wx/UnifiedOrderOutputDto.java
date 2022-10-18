package com.ruoyi.property.dto.wx;

import cn.hutool.core.annotation.Alias;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Value;
import lombok.With;

@Data
public class UnifiedOrderOutputDto {
    private String signType;
    private String paySign;
    private String nonceStr;
    private long timestamp;
    @JsonProperty("package")
    private String packageStr;
}
