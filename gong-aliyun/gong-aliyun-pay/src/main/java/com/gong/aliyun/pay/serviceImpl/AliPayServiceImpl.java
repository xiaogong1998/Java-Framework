package com.gong.aliyun.pay.serviceImpl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayFundTransUniTransferRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayFundTransUniTransferResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.gong.aliyun.pay.model.OrderModel;
import com.gong.aliyun.pay.properties.AliPayProperties;
import com.gong.aliyun.pay.service.AliPayService;
import com.gong.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Function;

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
    public String appTradePageCreate(AlipayTradeAppPayModel model) {
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
    public String wapTradePageCreate(AlipayTradeWapPayModel model) {
        try {
            AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
            request.setNotifyUrl(aliPayProperties.getNotifyUrl());
            request.setReturnUrl(aliPayProperties.getReturnUrl());
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
    public String pageTradePageCreate(AlipayTradePagePayModel model) {
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
    public String appTradeSdkCreate(AlipayTradeAppPayModel model) {
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
    public String wapTradeSdkCreate(AlipayTradeWapPayModel model) {
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
    public String pageTradeSdkCreate(AlipayTradePagePayModel model) {
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

    public String tradeNotify(Map<String, String> params, Function<String, OrderModel> function, Runnable runnable) {
        log.info("支付通知正在执行...");
        log.info("通知参数：{}", params);

        String result = "fail";

        try {
            // 调用SDK验证签名
            // 公钥验签示例代码
            boolean signVerified = AlipaySignature.rsaCheckV1(params, aliPayProperties.getAlipayPublicKey(), aliPayProperties.getCharset(), aliPayProperties.getSignType());
            if (!signVerified) {
                // TODO 验签失败则记录异常日志，并在response中返回fail.
                log.error("支付宝异步通知验签失败");
                return result;
            }

            // TODO 验签成功后
            // 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success

            // 1.商家需要验证该通知数据中的 out_trade_no 是否为商家系统中创建的订单号。
            String outTradeNo = params.get("out_trade_no");
            OrderModel orderModel = function.apply(outTradeNo);
            if (null == orderModel) {
                log.error("该订单不存在");
                return result;
            }

            // 2.商家需要验证该通知数据中的 out_trade_no 是否为商家系统中创建的订单号。
            String totalAmount = params.get("total_amount");
            BigDecimal totalAmountBigDecimal = new BigDecimal(totalAmount);
            BigDecimal orderManageTotalAmount = orderModel.getTotalAmount();
            if (!totalAmountBigDecimal.equals(orderManageTotalAmount)) {
                log.error("金额校验失败");
                return result;
            }

            // 3.校验通知中的 seller_id（或者 seller_email) 是否为 out_trade_no 这笔单据的对应的操作方（有的时候，一个商家可能有多个 seller_id/seller_email）。
            String sellerId = params.get("seller_id");
            String sellerIdProperty = aliPayProperties.getSellerId();
            if (!sellerIdProperty.equals(sellerId)) {
                log.error("商家pid校验失败");
                return result;
            }

            // 4.验证 app_id 是否为该商家本身。
            String appId = params.get("app_id");
            String appIdProperty = aliPayProperties.getAppId();
            if (!appIdProperty.equals(appId)) {
                log.error("appId校验失败");
                return result;
            }

            // 状态 TRADE_SUCCESS 的通知触发条件是商家开通的产品支持退款功能的前提下，买家付款成功。
            String tradeStatus = params.get("trade_status");
            if (!"TRADE_SUCCESS".equals(tradeStatus)) {
                log.error("支付未成功");
                return result;
            }

            runnable.run();

            log.info("支付宝异步通知验签成功");
        } catch (Exception e) {
            log.error("支付宝异步通知验签异常，原因：{}", e.getMessage());
            return result;
        }

        result = "success";
        return result;
    }

    public AlipayFundTransUniTransferResponse transferCreate(AlipayFundTransUniTransferModel model){
        try {
            model.setBizScene("DIRECT_TRANSFER");
            model.setProductCode("TRANS_ACCOUNT_NO_PWD");
            AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
            request.setNotifyUrl(aliPayProperties.getNotifyUrl());
            request.setReturnUrl(aliPayProperties.getReturnUrl());
            request.setBizModel(model);
            AlipayFundTransUniTransferResponse response = alipayClient.sdkExecute(request);
            if (response.isSuccess()) {
                log.info("调用支付宝单笔转账SDK支付成功 ===> {}", response.getBody());
                return response;
            } else {
                log.info("调用支付宝单笔转账SDK支付失败，返回码 ===> {}，返回描述 ===> {}", response.getCode(), response.getMsg());
                throw new ServiceException("创建支付宝单笔转账SDK交易订单失败，失败原因：" + response.getMsg());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new ServiceException("创建支付宝单笔转账SDK交易订单失败!");
        }

    }
}
