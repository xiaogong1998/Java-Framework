package com.gong.wechat.applet.model;

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
public abstract class WeChatAppletSubscriptionBaseResponse {

    @JSONField(name = "errcode")
    private String errCode;

    @JSONField(name = "errmsg")
    private String errMsg;

}
