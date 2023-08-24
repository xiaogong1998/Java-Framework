package com.gong.qweather.service;


import com.gong.qweather.entity.LocationResponse;

public interface QWeatherCityService {

    /**
     * 获取城市信息
     *
     * @return 城市信息
     */
    LocationResponse getCityInfo();

    /**
     * 获取城市信息
     *
     * @param cityName 城市名称
     * @return 城市信息
     */
    LocationResponse getCityInfo(String cityName);
}
