package com.gong.baidu.translate.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "baidu.config.translate", ignoreUnknownFields = true)
public class BaiduTranslateProperties {

    private String appid;

    private String secret;
}
