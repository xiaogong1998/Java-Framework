package com.gong.wechat.applet.enums;

public enum MiniprogramStateEnum {
    developer("developer", "开发版"),
    trial("trial", "体验版"),
    formal("formal", "正式版");

    private final String value;

    private final String desc;

    private MiniprogramStateEnum(String value, String desc) {
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
