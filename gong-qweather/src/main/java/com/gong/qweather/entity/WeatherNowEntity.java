package com.gong.qweather.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherNowEntity {

    private String obsTime;

    private String temp;

    private String feelsLike;

    private String icon;

    private String text;

    private String wind360;

    private String windDir;

    private String windScale;

    private String windSpeed;

    private String humidity;

    private String precip;

    private String pressure;

    private String vis;

    private String cloud;

    private String dew;
}
