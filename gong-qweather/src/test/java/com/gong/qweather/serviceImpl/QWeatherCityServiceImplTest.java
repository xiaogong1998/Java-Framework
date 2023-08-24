package com.gong.qweather.serviceImpl;

import com.gong.qweather.service.QWeatherCityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO
 *
 * @author xiaogong
 * @since 2023/8/24 14:00
 */
@SpringBootTest
class QWeatherCityServiceImplTest {

    @Autowired
    private QWeatherCityService qWeatherCityService;

    @Test
    void getCityInfo() {
        System.out.println(qWeatherCityService.getCityInfo());
    }

    @Test
    void testGetCityInfo() {
        System.out.println(qWeatherCityService.getCityInfo("北京"));
    }
}