package com.gong.alipay.properties;

import com.alipay.api.AlipayConstants;
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
@ConfigurationProperties(prefix = "alipay.config")
public class AliPayProperties implements Serializable {
    private static final long serialVersionUID = -7707187860556296754L;

    private String serverUrl = "https://openapi.alipay.com/gateway.do";

    private String sellerId;

    private String appId;

    private String format = AlipayConstants.FORMAT_JSON;

    private String charset = AlipayConstants.CHARSET_UTF8;

    private String signType = AlipayConstants.SIGN_TYPE_RSA2;

    private String privateKey;

    private String alipayPublicKey;

    private String returnUrl;

    private String notifyUrl;
}
