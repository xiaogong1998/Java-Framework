package com.gong.rabbitmq.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "gong.rabbitmq.config", ignoreUnknownFields = true)
public class RabbitMQProperties {

    /**
     * 地址
     */
    private String host;
    /**
     * 端口
     */
    private int port;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
