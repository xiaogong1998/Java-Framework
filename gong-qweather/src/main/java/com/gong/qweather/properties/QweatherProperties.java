package com.gong.qweather.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "qweather.config", ignoreUnknownFields = true)
public class QweatherProperties {

    private String keyName;

    private String publicId;

    private String key;

    private String type = "Web API";
}
