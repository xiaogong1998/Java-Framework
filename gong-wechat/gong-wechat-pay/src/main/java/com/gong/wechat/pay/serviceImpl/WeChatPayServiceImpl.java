package com.gong.wechat.pay.serviceImpl;


import com.gong.core.exception.ServiceException;
import com.gong.wechat.pay.properties.WeChatPayProperties;
import com.gong.wechat.pay.service.WeChatPayService;
import com.wechat.pay.java.core.exception.ValidationException;
import com.wechat.pay.java.core.notification.NotificationConfig;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.core.notification.RequestParam;
import com.wechat.pay.java.service.payments.app.AppService;
import com.wechat.pay.java.service.payments.jsapi.JsapiService;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.refund.RefundService;
import com.wechat.pay.java.service.refund.model.RefundNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * TODO 微信支付服务实现
 *
 * @author xiaogong
 * @since 2023/9/20 13:44
 */
@Slf4j
@Service
public class WeChatPayServiceImpl implements WeChatPayService {

    @Resource
    private WeChatPayProperties properties;

    @Resource
    private AppService appService;

    @Resource
    private JsapiService jsapiService;

    @Resource
    private RefundService refundService;

    @Resource
    private NotificationConfig notificationConfig;

    @Resource
    private JsapiServiceExtension jsapiServiceExtension;

    @Override
    public String appPrepayCreate(com.wechat.pay.java.service.payments.app.model.PrepayRequest request) {
        try {
            request.setAppid(properties.getAppId());
            request.setMchid(properties.getMerchantId());
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
            request.setAppid(properties.getAppId());
            request.setMchid(properties.getMerchantId());
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
    public PrepayWithRequestPaymentResponse jsapiWithRequestPrepayCreate(PrepayRequest request) {
        try {
            request.setAppid(properties.getAppId());
            request.setMchid(properties.getMerchantId());
            request.setNotifyUrl(properties.getNotifyUrl());
            com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse prepay = jsapiServiceExtension.prepayWithRequestPayment(request);
            log.info("创建微信JSAPI预支付订单成功 ===> {}", prepay.toString());
            return prepay;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("创建微信JSAPI预支付订单失败 ===> {}", e.getMessage());
        }
        throw new ServiceException("创建创建微信JSAPI预支付订单失败!");
    }

    @Override
    public com.wechat.pay.java.service.refund.model.Refund refundCreate(com.wechat.pay.java.service.refund.model.CreateRequest request) {
        try {
            request.setNotifyUrl(properties.getRefundNotifyUrl());
            com.wechat.pay.java.service.refund.model.Refund refund = refundService.create(request);
            log.info("创建微信退款订单成功 ===> {}", refund.toString());
            return refund;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("创建微信退款订单失败 ===> {}", e.getMessage());
        }
        throw new ServiceException("创建创建微信退款订单失败!");
    }

    @Override
    public Transaction tradeNotify(HttpServletRequest request) {
        String wechatSignature = request.getHeader("Wechatpay-Signature");
        String wechatPaySerial = request.getHeader("Wechatpay-Serial");
        String wechatPayNonce = request.getHeader("Wechatpay-Nonce");
        String wechatTimestamp = request.getHeader("Wechatpay-Timestamp");
        String wechatPaySignatureType = request.getHeader("Wechatpay-Signature-Type");
        try {
            String requestBody;
            BufferedReader reader = request.getReader();
            String line;
            StringBuilder inputString = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                inputString.append(line);
            }
            requestBody = inputString.toString();
            reader.close();
            RequestParam requestParam = new RequestParam.Builder()
                    .serialNumber(wechatPaySerial)
                    .nonce(wechatPayNonce)
                    .signature(wechatSignature)
                    .timestamp(wechatTimestamp)
                    .body(requestBody)
                    .build();
            // 初始化 NotificationParser
            NotificationParser parser = new NotificationParser(notificationConfig);
            return parser.parse(requestParam, Transaction.class);
        } catch (ValidationException | IOException e) {
            log.error("sign verification failed", e);
            return null;
        }
    }

    @Override
    public RefundNotification refundTradeNotify(HttpServletRequest request) {
        String wechatSignature = request.getHeader("Wechatpay-Signature");
        String wechatPaySerial = request.getHeader("Wechatpay-Serial");
        String wechatPayNonce = request.getHeader("Wechatpay-Nonce");
        String wechatTimestamp = request.getHeader("Wechatpay-Timestamp");
        String wechatPaySignatureType = request.getHeader("Wechatpay-Signature-Type");
        try {
            String requestBody;
            BufferedReader reader = request.getReader();
            String line;
            StringBuilder inputString = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                inputString.append(line);
            }
            requestBody = inputString.toString();
            reader.close();
            RequestParam requestParam = new RequestParam.Builder()
                    .serialNumber(wechatPaySerial)
                    .nonce(wechatPayNonce)
                    .signature(wechatSignature)
                    .timestamp(wechatTimestamp)
                    .body(requestBody)
                    .build();
            // 初始化 NotificationParser
            NotificationParser parser = new NotificationParser(notificationConfig);
            return parser.parse(requestParam, RefundNotification.class);
        } catch (ValidationException | IOException e) {
            log.error("sign verification failed", e);
            return null;
        }
    }
}
