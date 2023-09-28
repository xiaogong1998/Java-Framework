package com.gong.wechat.applet.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * TODO 登录请求
 *
 * @author xiaogong
 * @since 2023/9/19 8:48
 */
@Getter
@Setter
public class LoginRequest implements Serializable {

    /**
     * 在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台账号下
     */
    @JSONField(name = "js_code")
    private String jsCode;

    /**
     * 授权类型，此处只需填写 authorization_code
     */
    @JSONField(name = "grant_type")
    private String grantType = "authorization_code";

}
