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

    /**
     * 商户PID
     */
    private String sellerId;

    /**
     * APPID
     */
    private String appId;

    /**
     * 仅支持JSON
     */
    private String format = AlipayConstants.FORMAT_JSON;

    /**
     * 请求使用的编码格式，如utf-8,gbk,gb2312等
     */
    private String charset = AlipayConstants.CHARSET_UTF8;

    /**
     * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
     */
    private String signType = AlipayConstants.SIGN_TYPE_RSA2;

    /**
     * 应用私钥证书路径
     */
    private String appCertPath;

    /**
     * 支付宝公钥证书路径
     */
    private String rootCertPath;

    /**
     * 支付宝公钥证书路径
     */
    private String aipayPublicCertPath;

    /**
     * 应用私钥
     */
    private String privateKey;

    /**
     * 支付宝公钥
     */
    private String alipayPublicKey;

    /**
     * 支付成功跳转地址
     */
    private String returnUrl;

    /**
     * 支付宝服务器主动通知商户服务器里指定的页面http/https路径。
     */
    private String notifyUrl;
}
