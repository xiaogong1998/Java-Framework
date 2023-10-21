package com.gong.redisson.properties;

import com.alibaba.fastjson.annotation.JSONField;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "gong.redisson.config", ignoreUnknownFields = true)
public class RedissonProperties {

    /**
     * 地址
     */
    private String address;
    /**
     * 密码
     */
    private String password;
    /**
     * 数据库
     */
    private int database;
    /**
     * 启用状态
     */
    private Boolean enabled = Boolean.TRUE;
}
