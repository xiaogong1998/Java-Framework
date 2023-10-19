package com.gong.wechat.pay.config;

import com.gong.wechat.pay.properties.WeChatPayProperties;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.notification.NotificationConfig;
import com.wechat.pay.java.service.payments.app.AppService;
import com.wechat.pay.java.service.payments.h5.H5Service;
import com.wechat.pay.java.service.payments.jsapi.JsapiService;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.nativepay.NativePayService;
import com.wechat.pay.java.service.refund.RefundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO 微信支付配置
 *
 * @author xiaogong
 * @since 2023/8/3 9:14
 */
@Slf4j
@Configuration
public class WeChatPayConfiguration {

    @Bean
    public Config config(WeChatPayProperties weChatPayProperties) {
        Config config = new RSAAutoCertificateConfig.Builder()
                .merchantId(weChatPayProperties.getMerchantId())
                .privateKeyFromPath(weChatPayProperties.getPrivateKeyPath())
                .merchantSerialNumber(weChatPayProperties.getMerchantSerialNumber())
                .apiV3Key(weChatPayProperties.getApiV3Key())
                .build();
        log.info("微信支付初始化成功");
        return config;
    }

    @Bean
    public RSAAutoCertificateConfig rsaAutoCertificateConfig(Config config) {
        return (RSAAutoCertificateConfig) config;
    }

    @Bean
    public NotificationConfig notificationConfig(Config config) {
        return (NotificationConfig) config;
    }

    @Bean
    public JsapiServiceExtension jsapiServiceExtension(Config config) {
        return new JsapiServiceExtension.Builder().config(config).build();
    }

    @Bean
    public AppService appService(RSAAutoCertificateConfig config) {
        return new AppService.Builder().config(config).build();
    }

    @Bean
    public H5Service h5Service(RSAAutoCertificateConfig config) {
        return new H5Service.Builder().config(config).build();
    }

    @Bean
    public JsapiService jsapiService(RSAAutoCertificateConfig config) {
        return new JsapiService.Builder().config(config).build();
    }

    @Bean
    public NativePayService nativePayService(RSAAutoCertificateConfig config) {
        return new NativePayService.Builder().config(config).build();
    }

    @Bean
    public RefundService refundService(RSAAutoCertificateConfig config) {
        return new RefundService.Builder().config(config).build();
    }

}
