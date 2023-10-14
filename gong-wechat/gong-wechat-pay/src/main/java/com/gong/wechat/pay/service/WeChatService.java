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
    String appPrepayCreate(com.wechat.pay.java.service.payments.app.model.PrepayRequest request);

    /**
     * JSAPI预付订单创建
     *
     * @param request
     * @return
     */
    String jsapiPrepayCreate(com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest request);

    /**
     * 退款申请
     * @param request
     * @return
     */
    public com.wechat.pay.java.service.refund.model.Refund refundCreate(com.wechat.pay.java.service.refund.model.CreateRequest request);

}
