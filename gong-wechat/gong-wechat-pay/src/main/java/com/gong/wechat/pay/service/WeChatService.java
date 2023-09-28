package com.gong.wechat.pay.service;

import com.wechat.pay.java.service.payments.app.model.PrepayRequest;
import com.wechat.pay.java.service.payments.app.model.PrepayResponse;

/**
 * TODO 微信支付服务
 *
 * @author xiaogong
 * @since 2023/9/20 13:44
 */
public interface WeChatService {

    /**
     * APP预付订单创建
     *
     * @param request
     * @return
     */
    String appPrepayCreate(PrepayRequest request);
}
