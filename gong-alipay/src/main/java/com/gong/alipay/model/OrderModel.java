package com.gong.alipay.model;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * TODO 订单
 *
 * @author xiaogong
 * @since 2023/8/16 15:01
 */
public abstract class OrderModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String outTradeNo;

    private BigDecimal totalAmount;

    private String subject;

    private String productCode;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
