package com.gong.smsbao.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "gong.smsbao.config", ignoreUnknownFields = true)
public class SmsBaoProperties {

    private String apiUrl = "https://www.smsbao.com/query";

    /**
     * 短信包用户名
     */
    private String username;

    /**
     * 平台登录密码或ApiKey
     */
    private String apiKey;
}
