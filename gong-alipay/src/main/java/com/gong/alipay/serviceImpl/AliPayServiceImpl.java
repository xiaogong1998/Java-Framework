package com.gong.alipay.serviceImpl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.gong.alipay.model.OrderModel;
import com.gong.alipay.properties.AliPayProperties;
import com.gong.alipay.service.AliPayService;
import com.gong.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO 阿里支付
 *
 * @author xiaogong
 * @since 2023/8/16 15:03
 */
@Service
@Slf4j
public class AliPayServiceImpl implements AliPayService {

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private AliPayProperties aliPayProperties;

    @Override
    public String aliPayAppTradePageCreate(AlipayTradeAppPayModel model) {
        try {
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            request.setNotifyUrl(aliPayProperties.getNotifyUrl());
            request.setBizModel(model);
            AlipayTradeAppPayResponse response = alipayClient.pageExecute(request);
            if (response.isSuccess()) {
                log.info("调用支付宝APP页面支付成功 ===> {}", response.getBody());
                return response.getBody();
            } else {
                log.info("调用支付宝APP页面支付失败，返回码 ===> {}，返回描述 ===> {}", response.getCode(), response.getMsg());
                throw new ServiceException("创建支付宝APP页面交易订单失败，失败原因：" + response.getMsg());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new ServiceException("创建支付宝APP页面交易订单失败!");
        }
    }

    @Override
    public String aliPayWapTradePageCreate(AlipayTradeWapPayModel model) {
        try {
            AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
            request.setNotifyUrl(aliPayProperties.getNotifyUrl());
            request.setBizModel(model);
            AlipayTradeWapPayResponse response = alipayClient.pageExecute(request);
            if (response.isSuccess()) {
                log.info("调用支付宝手机网站页面支付成功 ===> {}", response.getBody());
                return response.getBody();
            } else {
                log.info("调用支付宝手机网站页面支付失败，返回码 ===> {}，返回描述 ===> {}", response.getCode(), response.getMsg());
                throw new ServiceException("创建支付宝手机网站页面交易订单失败，失败原因：" + response.getMsg());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new ServiceException("创建支付宝手机网站页面交易订单失败!");
        }
    }

    @Override
    public String aliPayPageTradePageCreate(AlipayTradePagePayModel model) {
        try {
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            request.setNotifyUrl(aliPayProperties.getNotifyUrl());
            request.setReturnUrl(aliPayProperties.getReturnUrl());
            model.setProductCode("FAST_INSTANT_TRADE_PAY");
            request.setBizModel(model);
            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
            if (response.isSuccess()) {
                log.info("调用支付宝电脑网站页面支付成功 ===> {}", response.getBody());
                return response.getBody();
            } else {
                log.info("调用支付宝电脑网站页面支付失败，返回码 ===> {}，返回描述 ===> {}", response.getCode(), response.getMsg());
                throw new ServiceException("创建支付宝电脑网站页面交易订单失败，失败原因：" + response.getMsg());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new ServiceException("创建支付宝电脑网站页面交易订单失败!");
        }
    }

    @Override
    public String aliPayAppTradeSdkCreate(AlipayTradeAppPayModel model) {
        try {
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            request.setNotifyUrl(aliPayProperties.getNotifyUrl());
            request.setBizModel(model);
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            if (response.isSuccess()) {
                log.info("调用支付宝APP SDK支付成功 ===> {}", response.getBody());
                return response.getBody();
            } else {
                log.info("调用支付宝APP SDK支付失败，返回码 ===> {}，返回描述 ===> {}", response.getCode(), response.getMsg());
                throw new ServiceException("创建支付宝APP SDK交易订单失败，失败原因：" + response.getMsg());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new ServiceException("创建支付宝APP SDK交易订单失败!");
        }
    }

    @Override
    public String aliPayWapTradeSdkCreate(AlipayTradeWapPayModel model) {
        try {
            AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
            request.setNotifyUrl(aliPayProperties.getNotifyUrl());
            request.setBizModel(model);
            AlipayTradeWapPayResponse response = alipayClient.sdkExecute(request);
            if (response.isSuccess()) {
                log.info("调用支付宝手机网站SDK支付成功 ===> {}", response.getBody());
                return response.getBody();
            } else {
                log.info("调用支付宝手机网站SDK支付失败，返回码 ===> {}，返回描述 ===> {}", response.getCode(), response.getMsg());
                throw new ServiceException("创建支付宝手机网站SDK交易订单失败，失败原因：" + response.getMsg());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new ServiceException("创建支付宝手机网站SDK交易订单失败!");
        }
    }

    @Override
    public String aliPayPageTradeSdkCreate(AlipayTradePagePayModel model) {
        try {
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            request.setNotifyUrl(aliPayProperties.getNotifyUrl());
            request.setReturnUrl(aliPayProperties.getReturnUrl());
            model.setProductCode("FAST_INSTANT_TRADE_PAY");
            request.setBizModel(model);
            AlipayTradePagePayResponse response = alipayClient.sdkExecute(request);
            if (response.isSuccess()) {
                log.info("调用支付宝电脑网站SDK支付成功 ===> {}", response.getBody());
                return response.getBody();
            } else {
                log.info("调用支付宝电脑网站SDK支付失败，返回码 ===> {}，返回描述 ===> {}", response.getCode(), response.getMsg());
                throw new ServiceException("创建支付宝电脑网站SDK交易订单失败，失败原因：" + response.getMsg());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new ServiceException("创建支付宝电脑网站SDK交易订单失败!");
        }
    }
}
