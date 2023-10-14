package com.gong.baidu.translate.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * TODO 语种枚举
 *
 * @author xiaogong
 * @since 2023/9/28 14:59
 */
public enum LanguageEnum {
    Auto("auto", "自动检测"),
    Chinese("zh", "中文"),
    TraditionalChinese("cht", "繁体中文"),
    English("en", "英语");

    private final String value;

    private final String desc;

    LanguageEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static LanguageEnum findByValue(String value) {
        value = value.trim();
        if (StringUtils.isBlank(value)) {
            return null;
        }
        for (LanguageEnum languageEnum : values()) {
            if (StringUtils.equals(languageEnum.getValue(), value)) {
                return languageEnum;
            }
        }
        throw new IllegalArgumentException(value + " is not found");
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
