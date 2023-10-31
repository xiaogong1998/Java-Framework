package com.gong.wechat.applet.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * TODO 登录响应
 *
 * @author xiaogong
 * @since 2023/9/19 8:50
 */
@Getter
@Setter
public class WeChatLoginResponse implements Serializable {

    /**
     * 会话密钥
     */
    private String sessionKey;

    /**
     * 用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台账号下会返回，详见 UnionID 机制说明。
     * <a href="https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/union-id.html">...</a>
     */
    @JSONField(name = "unionid")
    private String unionId;

    /**
     * 错误信息
     */
    @JSONField(name = "errmsg")
    private String errMsg;

    /**
     * 用户唯一标识
     */
    @JSONField(name = "openid")
    private String openId;

    /**
     * 用户唯一标识
     */
    @JSONField(name = "errcode")
    private Integer errCode;

}
