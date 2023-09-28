package com.gong.qweather.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherDailyEntity {

    // 预报日期
    private String fxDate;

    // 日出时间
    private String sunrise;

    // 日落时间
    private String sunset;

    // 月升时间
    private String moonrise;

    // 月落时间
    private String moonset;

    // 月相名称
    private String moonPhase;

    // 月相图标代码，图标可通过天气状况和图标下载
    // https://dev.qweather.com/docs/resource/icons/
    private String moonPhaseIcon;

    // 预报当天最高温度
    private String tempMax;

    // 预报当天最低温度
    private String tempMin;

    // 预报白天天气状况的图标代码，图标可通过天气状况和图标下载
    // https://dev.qweather.com/docs/resource/icons/
    private String iconDay;

    // 预报白天天气状况文字描述，包括阴晴雨雪等天气状态的描述
    private String textDay;

    // 预报夜间天气状况的图标代码，图标可通过天气状况和图标下载
    // https://dev.qweather.com/docs/resource/icons/
    private String iconNight;

    // 预报晚间天气状况文字描述，包括阴晴雨雪等天气状态的描述
    private String textNight;

    // 预报白天风向360角度
    private String wind360Day;

    // 预报白天风向
    private String windDirDay;

    // 预报白天风力等级
    private String windScaleDay;

    // 预报白天风速，公里/小时
    private String windSpeedDay;

    // 预报夜间风向360角度
    private String wind360Night;

    // 预报夜间当天风向
    private String windDirNight;

    // 预报夜间风力等级
    private String windScaleNight;

    // 预报夜间风速，公里/小时
    private String windSpeedNight;

    // 预报当天总降水量，默认单位：毫米
    private String humidity;

    // 紫外线强度指数
    private String precip;

    // 相对湿度，百分比数值
    private String pressure;

    // 大气压强，默认单位：百帕
    private String vis;

    // 能见度，默认单位：公里
    private String cloud;

    // 云量，百分比数值。可能为空
    private String uvIndex;
}
