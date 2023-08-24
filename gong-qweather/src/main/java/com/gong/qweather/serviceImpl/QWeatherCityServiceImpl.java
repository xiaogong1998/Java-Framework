package com.gong.qweather.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.gong.core.utils.HttpClientUtil;
import com.gong.qweather.entity.LocationResponse;
import com.gong.qweather.properties.QweatherCityProperties;
import com.gong.qweather.properties.QweatherProperties;
import com.gong.qweather.service.QWeatherCityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class QWeatherCityServiceImpl implements QWeatherCityService {

    private static final Logger log = LoggerFactory.getLogger(QWeatherCityServiceImpl.class);

    private final QweatherProperties qweatherProperties;

    private final QweatherCityProperties qweatherCityProperties;

    private final HttpClientUtil httpClient = new HttpClientUtil();

    private int retry = 0;

    public QWeatherCityServiceImpl(QweatherProperties qweatherProperties, QweatherCityProperties qweatherCityProperties) {
        this.qweatherProperties = qweatherProperties;
        this.qweatherCityProperties = qweatherCityProperties;
    }

    @Override
    public LocationResponse getCityInfo() {
        return getCityInfo(qweatherCityProperties.getCityName());
    }

    /**
     * 获取城市信息
     *
     * @param cityName 城市名称
     * @return QweatherCityResponse
     */
    @Override
    public synchronized LocationResponse getCityInfo(String cityName) {
        StringBuilder url = new StringBuilder();
        url.append(qweatherCityProperties.getApiUrl());
        url.append("/v2/city/lookup");
        url.append("?location=").append(cityName);
        url.append("&key=").append(qweatherProperties.getKey());
        log.info("qweather city request url : {}", url);
        LocationResponse response = httpClient.get(url.toString()).map(r -> {
            log.info("qweather city response : {}", r);
            return JSONObject.parseObject(r, LocationResponse.class);
        }).orElse(null);
        if (response == null) {
            log.info("城市信息获取失败，5秒后重试");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 重试次数
            int maxRetry = 5;
            if (retry < maxRetry) {
                retry += 1;
                return getCityInfo(cityName);
            } else {
                retry = 0;
            }
        }
        return response;
    }
}
