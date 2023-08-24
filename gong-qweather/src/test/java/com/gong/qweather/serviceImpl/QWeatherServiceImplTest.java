package com.gong.qweather.serviceImpl;

import com.gong.qweather.service.QWeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO
 *
 * @author xiaogong
 * @since 2023/8/24 14:06
 */
@SpringBootTest
class QWeatherServiceImplTest {

    @Autowired
    private QWeatherService qWeatherService;

    @Test
    void getRealTimeWeather() {
        qWeatherService.getRealTimeWeather("101080101");
    }

    @Test
    void getWeatherThreeDay() {
        qWeatherService.getWeatherThreeDay("101080101");
    }

    @Test
    void getWeatherSevenDay() {
        qWeatherService.getWeatherSevenDay("101080101");
    }
}