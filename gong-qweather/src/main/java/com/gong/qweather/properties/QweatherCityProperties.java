package com.gong.qweather.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "qweather.city.config", ignoreUnknownFields = true)
public class QweatherCityProperties {

    private String cityName;

    private String apiUrl = "https://geoapi.qweather.com";
}
