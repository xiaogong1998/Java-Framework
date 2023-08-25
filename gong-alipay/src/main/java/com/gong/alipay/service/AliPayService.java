package com.gong.alipay.service;

import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;

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

}
