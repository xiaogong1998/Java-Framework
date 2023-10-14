package com.gong.wechat.pay.serviceImpl;


import com.gong.core.exception.ServiceException;
import com.gong.wechat.pay.properties.WeChatPayProperties;
import com.gong.wechat.pay.service.WeChatService;
import com.wechat.pay.java.service.payments.app.AppService;
import com.wechat.pay.java.service.payments.jsapi.JsapiService;
import com.wechat.pay.java.service.refund.RefundService;
import com.wechat.pay.java.service.refund.model.CreateRequest;
import com.wechat.pay.java.service.refund.model.Refund;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * TODO 微信支付服务实现
 *
 * @author xiaogong
 * @since 2023/9/20 13:44
 */
@Slf4j
@Service
public class WeChatServiceImpl implements WeChatService {

    @Resource
    private WeChatPayProperties properties;

    @Resource
    private AppService appService;

    @Resource
    private JsapiService jsapiService;

    @Resource
    private RefundService refundService;

    @Override
    public String appPrepayCreate(com.wechat.pay.java.service.payments.app.model.PrepayRequest request) {
        try {
            request.setNotifyUrl(properties.getNotifyUrl());
            com.wechat.pay.java.service.payments.app.model.PrepayResponse prepay = appService.prepay(request);
            log.info("创建微信APP预支付订单成功 ===> {}", prepay.toString());
            return prepay.getPrepayId();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("创建微信APP预支付订单失败 ===> {}", e.getMessage());
        }
        throw new ServiceException("创建创建微信APP预支付订单失败!");
    }

    @Override
    public String jsapiPrepayCreate(com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest request) {
        try {
            request.setNotifyUrl(properties.getNotifyUrl());
            com.wechat.pay.java.service.payments.jsapi.model.PrepayResponse prepay = jsapiService.prepay(request);
            log.info("创建微信JSAPI预支付订单成功 ===> {}", prepay.toString());
            return prepay.getPrepayId();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("创建微信JSAPI预支付订单失败 ===> {}", e.getMessage());
        }
        throw new ServiceException("创建创建微信JSAPI预支付订单失败!");
    }

    @Override
    public com.wechat.pay.java.service.refund.model.Refund refundCreate(com.wechat.pay.java.service.refund.model.CreateRequest request) {
        try {
            request.setNotifyUrl(properties.getNotifyUrl());
            com.wechat.pay.java.service.refund.model.Refund refund = refundService.create(request);
            log.info("创建微信退款订单成功 ===> {}", refund.toString());
            return refund;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("创建微信退款订单失败 ===> {}", e.getMessage());
        }
        throw new ServiceException("创建创建微信退款订单失败!");
    }
}
