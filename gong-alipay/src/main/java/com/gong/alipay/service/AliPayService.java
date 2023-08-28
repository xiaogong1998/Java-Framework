package com.gong.alipay.service;

import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.gong.alipay.model.OrderModel;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

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
    String aliPayAppTradePageCreate(AlipayTradeAppPayModel model);

    /**
     * 手机网站创创建页面订单
     *
     * @param model
     * @return
     */
    String aliPayWapTradePageCreate(AlipayTradeWapPayModel model);

    /**
     * 电脑网站创建页面订单
     *
     * @param model
     * @return
     */
    String aliPayPageTradePageCreate(AlipayTradePagePayModel model);

    /**
     * App创建SDK订单
     *
     * @param model
     * @return
     */
    String aliPayAppTradeSdkCreate(AlipayTradeAppPayModel model);

    /**
     * 手机网站创创建SDK订单
     *
     * @param model
     * @return
     */
    String aliPayWapTradeSdkCreate(AlipayTradeWapPayModel model);

    /**
     * 电脑网站创建SDK订单
     *
     * @param model
     * @return
     */
    String aliPayPageTradeSdkCreate(AlipayTradePagePayModel model);

    /**
     * 异步校验
     *
     * @param params   支付宝返回参数
     * @param supplier 订单
     * @param runnable 验签成功后执行的处理
     * @return 验签结果
     */
    String alipayTradeNotify(Map<String, String> params, Function<String, OrderModel> function, Runnable runnable);
}
