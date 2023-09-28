package com.gong.qweather.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
public class WeatherResponse {

    // API状态码
    // https://dev.qweather.com/docs/resource/status-code/
    private String code;

    // 当前API的最近更新时间
    // https://dev.qweather.com/docs/resource/glossary#update-time
    private String updateTime;

    // 当前数据的响应式页面，便于嵌入网站或应用
    private String fxLink;

    // 天气信息
    private List<WeatherDailyEntity> daily;

    private WeatherNowEntity now;

    // 原始数据
    // refer.sources 原始数据来源，或数据源说明，可能为空
    // refer.license 数据许可或版权声明，可能为空
    private Map<String, List<String>> refer;
    //
    public WeatherDailyEntity getFirstDailyWeatherInfo() {
        return Optional.ofNullable(daily).flatMap(v -> v.stream().findFirst()).orElse(null);
    }
}
