package com.gong.wechat.pay.serviceImpl;


import com.gong.core.exception.ServiceException;
import com.gong.wechat.pay.properties.WeChatPayProperties;
import com.gong.wechat.pay.service.WeChatService;
import com.wechat.pay.java.service.payments.app.AppService;
import com.wechat.pay.java.service.payments.app.model.PrepayRequest;
import com.wechat.pay.java.service.payments.app.model.PrepayResponse;
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

    @Override
    public String appPrepayCreate(PrepayRequest request) {
        try {
            request.setNotifyUrl(properties.getNotifyUrl());
            PrepayResponse prepay = appService.prepay(request);
            log.info("创建微信APP预支付订单成功 ===> {}", prepay.toString());
            return prepay.getPrepayId();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("创建微信APP预支付订单失败 ===> {}", e.getMessage());
        }
        throw new ServiceException("创建创建微信APP预支付订单失败!");
    }
}
