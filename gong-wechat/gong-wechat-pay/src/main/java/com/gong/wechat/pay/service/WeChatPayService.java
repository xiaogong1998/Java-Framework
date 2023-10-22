package com.gong.wechat.pay.service;

import com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.refund.model.RefundNotification;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO 微信支付服务
 *
 * @author xiaogong
 * @since 2023/9/20 13:44
 */
public interface WeChatPayService {

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

    PrepayWithRequestPaymentResponse jsapiWithRequestPrepayCreate(com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest request);

    /**
     * 退款申请
     * @param request
     * @return
     */
    com.wechat.pay.java.service.refund.model.Refund refundCreate(com.wechat.pay.java.service.refund.model.CreateRequest request);


    /**
     * 异步校验
     *
     * @param request   支付宝返回参数
     * @return 验签结果
     */
    Transaction tradeNotify(HttpServletRequest request);

    /**
     * 异步校验
     *
     * @param request   支付宝返回参数
     * @return 验签结果
     */
    RefundNotification refundTradeNotify(HttpServletRequest request);

}
