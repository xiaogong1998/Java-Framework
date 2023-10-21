package com.gong.wechat.subscription.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "gong.wechat.config.mp", ignoreUnknownFields = true)
public class SubscriptionProperties {

    private String apiUrl = "https://api.weixin.qq.com";

    private String appId;

    private String appSecret;
}
