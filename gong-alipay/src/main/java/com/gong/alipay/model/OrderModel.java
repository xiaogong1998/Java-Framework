package com.gong.alipay.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * TODO 订单
 *
 * @author xiaogong
 * @since 2023/8/16 15:01
 */
public abstract class OrderModel implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract BigDecimal getTotalAmount();
}
