package com.gong.aliyun.pay.service;

import com.alipay.api.domain.AlipayFundTransUniTransferModel;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.response.AlipayFundTransUniTransferResponse;
import com.gong.aliyun.pay.model.OrderModel;

import java.util.Map;
import java.util.function.Function;

/**
 * TODO 阿里支付
 *
 * @author xiaogong
 * @since 2023/8/16 14:58
 */
public interface AliPayService {

    /**
     * App创建页面订单
     *
     * @param model
     * @return
     */
    String appTradePageCreate(AlipayTradeAppPayModel model);

    /**
     * 手机网站创创建页面订单
     *
     * @param model
     * @return
     */
    String wapTradePageCreate(AlipayTradeWapPayModel model);

    /**
     * 电脑网站创建页面订单
     *
     * @param model
     * @return
     */
    String pageTradePageCreate(AlipayTradePagePayModel model);

    /**
     * App创建SDK订单
     *
     * @param model
     * @return
     */
    String appTradeSdkCreate(AlipayTradeAppPayModel model);

    /**
     * 手机网站创创建SDK订单
     *
     * @param model
     * @return
     */
    String wapTradeSdkCreate(AlipayTradeWapPayModel model);

    /**
     * 电脑网站创建SDK订单
     *
     * @param model
     * @return
     */
    String pageTradeSdkCreate(AlipayTradePagePayModel model);

    /**
     * 异步校验
     *
     * @param params   支付宝返回参数
     * @param function 订单查询（传入订单号，返回订单信息）
     * @param runnable 验签成功后执行的处理
     * @return 验签结果
     */
    String tradeNotify(Map<String, String> params, Function<String, OrderModel> function, Runnable runnable);


    /**
     * 提现到支付宝账户
     * @param model
     * @return
     */
    AlipayFundTransUniTransferResponse transferCreate(AlipayFundTransUniTransferModel model);

}
