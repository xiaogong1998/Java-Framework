package com.gong.wechat.applet.enums;

import com.gong.core.annotation.BaseEnum;
import org.apache.commons.lang3.ObjectUtils;

public enum WeChatAppletMessageErrEnum implements BaseEnum<Long> {
    RequestSucceeded(0, "请求成功"),
    InvalidCredentials(40001, "invalid credential  access_token isinvalid or not latest"),
    invalidOpenid(40003, "invalid openid"),
    invalidAccessToken(40014, "invalid access_token"),
    invaliTemplateId(40037, "invalid template_id"),
    UserNotSubscribed(43101, "用户未订阅消息"),
    MessageSubscriptionCapabilityBanned(43107, "订阅消息能力封禁"),
    SimultaneouslySendMessagesToTheSameFan(43108, "并发下发消息给同一个粉丝"),
    HitSensitiveWords(45168, "命中敏感词"),
    ParameterError(47003, "参数错误"),
    ;

    private final Long code;

    private final String msg;

    WeChatAppletMessageErrEnum(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Long code() {
        return code;
    }

    public String msg() {
        return msg;
    }

    @Override
    public Long getValue() {
        return this.code;
    }

    public static WeChatAppletMessageErrEnum findByValue(Long code) {
        if (ObjectUtils.isEmpty(code)) {
            return null;
        }
        for (WeChatAppletMessageErrEnum enumValue : values()) {
            if (enumValue.code().equals(code)) {
                return enumValue;
            }
        }
        return null;
    }
}
