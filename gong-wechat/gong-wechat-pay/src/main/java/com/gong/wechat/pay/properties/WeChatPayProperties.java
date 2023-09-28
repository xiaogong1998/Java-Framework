package com.gong.wechat.pay.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * TODO 阿里配置
 *
 * @author xiaogong
 * @since 2023/8/3 9:16
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "wechat.pay.config",ignoreInvalidFields = false)
public class WeChatPayProperties implements Serializable {
    private static final long serialVersionUID = -7707187860556296754L;

    /**
     * 应用程序Id
     */
    private String appId;

    /**
     * 商户号
     */
    private String merchantId;

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 私钥路径
     */
    private String privateKeyPath;

    /**
     * 商户证书序列号
     */
    private String merchantSerialNumber;

    /**
     * 商户APIV3密钥
     */
    private String apiV3Key;

    /**
     * 回调通知地址
     */
    private String notifyUrl;
}
