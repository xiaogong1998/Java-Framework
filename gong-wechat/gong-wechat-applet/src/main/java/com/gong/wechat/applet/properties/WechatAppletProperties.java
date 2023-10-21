package com.gong.wechat.applet.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "gong.wechat.config.applet", ignoreUnknownFields = true)
public class WechatAppletProperties {

    private String apiUrl = "https://api.weixin.qq.com/sns/jscode2session";

    private String appid;

    private String secret;
}
