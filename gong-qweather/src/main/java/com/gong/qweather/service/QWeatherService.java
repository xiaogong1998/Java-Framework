package com.gong.qweather.service;


import com.gong.qweather.entity.WeatherResponse;

public interface QWeatherService {

    WeatherResponse getRealTimeWeather(String location);
    /**
     * 获取三天预报
     *
     * @param location
     * 需要查询地区的LocationID或以英文逗号分隔的经度,纬度坐标（十进制，最多支持小数点后两位
     * LocationID可通过城市搜索服务获取
     * 例如 location=101010100 或 location=116.41,39.92
     * @return 天气信息响应
     */
    WeatherResponse getWeatherThreeDay(String location);

    /**
     * 获取七天预报
     *
     * @param location
     * 需要查询地区的LocationID或以英文逗号分隔的经度,纬度坐标（十进制，最多支持小数点后两位）
     * LocationID可通过城市搜索服务获取
     * 例如 location=101010100 或 location=116.41,39.92
     * @return 天气信息响应
     */
    WeatherResponse getWeatherSevenDay(String location);

}
