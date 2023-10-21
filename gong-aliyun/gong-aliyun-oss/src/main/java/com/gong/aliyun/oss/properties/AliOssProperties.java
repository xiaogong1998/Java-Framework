package com.gong.aliyun.oss.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "gong.aliyun.config.oss", ignoreUnknownFields = true)
public class AliOssProperties {

    /**
     *
     */
    private String accessKey;

    private String secretKey;

    private String endpoint;

    private String bucketName;

}
