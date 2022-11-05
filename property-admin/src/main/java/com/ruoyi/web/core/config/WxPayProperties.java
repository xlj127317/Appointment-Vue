package com.ruoyi.web.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wxpay")
@Data
public class WxPayProperties {
    private String appId;
    private String mchId;
    private String mchKey;
    private String keyPath;
}
