package com.gong.wechat.subscription.properties;

import com.gong.wechat.subscription.entity.BaseTemplateIdEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "wechat.mp.config", ignoreUnknownFields = true)
public class SubscriptionProperties {

    private String apiUrl = "https://api.weixin.qq.com";

    private String appId;

    private String appSecret;
}
