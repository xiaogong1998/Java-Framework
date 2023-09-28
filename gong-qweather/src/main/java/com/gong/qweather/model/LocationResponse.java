package com.gong.qweather.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class LocationResponse {

    // API状态码
    // https://dev.qweather.com/docs/resource/status-code/
    private String code;

    // 地区/城市信息
    private List<LocationEntity> location;

    // 原始数据信息
    // refer.sources 原始数据来源，或数据源说明，可能为空
    // refer.license 数据许可或版权声明，可能为空
    private Map<String, List<String>> refer;

    public LocationEntity getFirstCity() {
        return location.stream().findFirst().orElse(null);
    }
}
