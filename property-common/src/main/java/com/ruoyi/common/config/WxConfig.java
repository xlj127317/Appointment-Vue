package com.ruoyi.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "wxpay")
public class WxConfig {
    private String appId;
    private String mchKey;
    private boolean useSandbox;
    private String payNotifyUrl;
    private String refundNotifyUrl;
}
