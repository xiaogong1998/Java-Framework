package com.gong.qweather.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "gong.qweather.config.weather", ignoreUnknownFields = true)
public class QweatherWeatherProperties {

    private String apiUrl = "https://devapi.qweather.com";

}
