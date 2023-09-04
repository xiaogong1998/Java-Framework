package com.gong.core.enums;

/**
 * TODO Base64前缀枚举
 *
 * @author xiaogong
 * @since 2023/9/4 9:06
 */
public enum Base64Enum implements BaseEnum<String> {
    PNG("data:image/png;base64,", "png");


    private final String value;

    private final String desc;


    Base64Enum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
