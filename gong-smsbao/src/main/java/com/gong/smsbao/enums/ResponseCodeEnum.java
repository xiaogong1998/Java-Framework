package com.gong.smsbao.enums;

import com.gong.core.annotation.BaseEnum;

public enum ResponseCodeEnum implements BaseEnum<String> {

    Success("0", "成功"),
    WrongPassword("30", "错误密码"),
    AccountDoesNotExist("40", "账号不存在"),
    InsufficientBalance("41", "余额不足"),
    IPAddressRestrictions("43", "IP地址限制"),
    ContentContainsSensitiveWords("50", "内容含有敏感词"),
    IncorrectPhoneNumber("51", "手机号码不正确");

    private final String value;

    private final String desc;

    ResponseCodeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static ResponseCodeEnum findByValue(String value) {
        for (ResponseCodeEnum responseCodeEnum : values()) {
            if (responseCodeEnum.value.equals(value)) {
                return responseCodeEnum;
            }
        }
        return null;
    }

    public String getDesc() {
        return desc;
    }

}
