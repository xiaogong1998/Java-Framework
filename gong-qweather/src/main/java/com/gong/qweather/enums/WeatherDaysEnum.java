package com.gong.qweather.enums;

import com.gong.core.annotation.BaseEnum;

public enum WeatherDaysEnum implements BaseEnum<String> {

    RealTime("now", "实时"),
    ThreeDays("3d", "三天"),
    SevenDays("7d", "七天");

    private final String value;

    private final String desc;

    WeatherDaysEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public WeatherDaysEnum findByValue(String value) {
        for (WeatherDaysEnum weatherDaysEnum : values()) {
            if (weatherDaysEnum.value.equals(value)) {
                return weatherDaysEnum;
            }
        }
        return null;
    }
}
