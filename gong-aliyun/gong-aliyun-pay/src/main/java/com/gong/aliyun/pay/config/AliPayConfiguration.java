package com.gong.aliyun.pay.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.gong.aliyun.pay.properties.AliPayProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * TODO 阿里支付配置
 *
 * @author xiaogong
 * @since 2023/8/3 9:14
 */
@Configuration
public class AliPayConfiguration {

    @Bean
    @Primary
    public AlipayClient alipayClient(AliPayProperties aliPayProperties) throws AlipayApiException {
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl(aliPayProperties.getServerUrl());
        alipayConfig.setAppId(aliPayProperties.getAppId());
        alipayConfig.setFormat(aliPayProperties.getFormat());
        alipayConfig.setCharset(aliPayProperties.getCharset());
        alipayConfig.setSignType(aliPayProperties.getSignType());
//        alipayConfig.setAppCertPath(aliPayProperties.getAppCertPath());
//        alipayConfig.setAlipayPublicCertPath(aliPayProperties.getAipayPublicCertPath());
//        alipayConfig.setRootCertPath(aliPayProperties.getRootCertPath());
        alipayConfig.setPrivateKey(aliPayProperties.getPrivateKey());
        alipayConfig.setAlipayPublicKey(aliPayProperties.getAlipayPublicKey());
        return new DefaultAlipayClient(alipayConfig);
    }

}
