package com.gong.alipay.service;

import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.gong.alipay.model.OrderModel;

/**
 * TODO 阿里支付
 *
 * @author xiaogong
 * @since 2023/8/16 14:58
 */
public interface AliPayService {

    /**
     * App创建订单
     *
     * @param model
     * @return
     */
    String aliPayAppTradeCreate(AlipayTradeAppPayModel model);

    /**
     * 手机网站创创建订单
     *
     * @param model
     * @return
     */
    String aliPayWapTradeCreate(AlipayTradeWapPayModel model);

    /**
     * 电脑网站创建订单
     *
     * @param model
     * @return
     */
    String aliPayPageTradeCreate(AlipayTradePagePayModel model);

}
