package com.gong.baidu.translate.enums;

/**
 * TODO 语种枚举
 *
 * @author xiaogong
 * @since 2023/9/28 14:59
 */
public enum LanguageEnum {
    Auto("auto","自动检测"),
    Chinese("zh", "中文"),
    TraditionalChinese("cht", "繁体中文"),
    English("en", "英语");

    private String value;

    private String desc;

    LanguageEnum(String value, String desc) {
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
