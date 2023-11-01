package com.gong.wechat.applet.enums;

public enum LangEnum {
    zh_CN("zh_CN", "简体中文"),
    en_US("en_US", "英文"),
    zh_HK("zh_HK", "繁体中文"),
    zh_TW("zh_TW", "繁体中文");

    private final String value;

    private final String desc;

    private LangEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
