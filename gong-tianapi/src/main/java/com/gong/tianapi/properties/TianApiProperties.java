package com.gong.tianapi.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "gong.tianapi.config", ignoreUnknownFields = true)
public class TianApiProperties {

    private String apiUrl = "https://apis.tianapi.com";

    private String key;
}
