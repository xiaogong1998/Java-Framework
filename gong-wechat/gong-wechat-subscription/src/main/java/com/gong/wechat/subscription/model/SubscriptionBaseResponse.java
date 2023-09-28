package com.gong.wechat.subscription.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * 微信公共返回
 *
 * @author xiaogong
 */
@Getter
@Setter
public abstract class SubscriptionBaseResponse {

    @JSONField(name = "errcode")
    private String errCode;

    @JSONField(name = "errmsg")
    private String errMsg;

}
