package com.gong.qweather.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gong.core.utils.HttpClientUtil;
import com.gong.qweather.entity.WeatherResponse;
import com.gong.qweather.enums.WeatherDaysEnum;
import com.gong.qweather.properties.QweatherProperties;
import com.gong.qweather.properties.QweatherWeatherProperties;
import com.gong.qweather.service.QWeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class QWeatherServiceImpl implements QWeatherService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final QweatherProperties qweatherProperties;

    private final QweatherWeatherProperties qweatherWeatherProperties;

    private final HttpClientUtil httpClient = new HttpClientUtil();

    private int retry = 0;

    public QWeatherServiceImpl(QweatherProperties qweatherProperties, QweatherWeatherProperties qweatherWeatherProperties) {
        this.qweatherProperties = qweatherProperties;
        this.qweatherWeatherProperties = qweatherWeatherProperties;
    }

    @Override
    public WeatherResponse getRealTimeWeather(String location) {
        return getWeatherInfo(location, WeatherDaysEnum.RealTime);
    }

    @Override
    public WeatherResponse getWeatherThreeDay(String location) {
        return getWeatherInfo(location, WeatherDaysEnum.ThreeDays);
    }

    @Override
    public WeatherResponse getWeatherSevenDay(String location) {
        return getWeatherInfo(location, WeatherDaysEnum.SevenDays);
    }

    private synchronized WeatherResponse getWeatherInfo(String location, WeatherDaysEnum days) {
        StringBuilder url = new StringBuilder();
        url.append(qweatherWeatherProperties.getApiUrl());
        url.append("/v7/weather/").append(days.getValue());
        url.append("?location=").append(location);
        url.append("&key=").append(qweatherProperties.getKey());
        log.info("qweather request url : {}", url);
        WeatherResponse response = httpClient.get(url.toString()).map(r -> {
            log.info("weather response : {}", r);
            return JSONObject.parseObject(r, WeatherResponse.class);
        }).orElse(null);
        if (response == null) {
            log.info("天气信息获取失败，5秒后重试");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 重试次数
            int maxRetry = 5;
            if (retry < maxRetry) {
                retry += 1;
                return getWeatherInfo(location, days);
            } else {
                retry = 0;
            }
        }
        return response;
    }
}
