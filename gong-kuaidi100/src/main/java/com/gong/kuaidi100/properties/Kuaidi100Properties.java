package com.gong.kuaidi100.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * TODO 快递100
 *
 * @author xiaogong
 * @since 2023/8/21 14:47
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "kuaidi100.config")
public class Kuaidi100Properties implements Serializable {
    private static final long serialVersionUID = -682036306220144150L;

    private String key;

    private String customer;

    private String secret;

    private String siid;

    private String userid;

    private String tid;

    private String secretKey;

    private String secretSecret;
}
