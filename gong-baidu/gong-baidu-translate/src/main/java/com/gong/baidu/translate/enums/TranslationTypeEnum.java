package com.gong.baidu.translate.enums;

/**
 * TODO 语种枚举
 *
 * @author xiaogong
 * @since 2023/9/28 14:59
 */
public enum TranslationTypeEnum {

    CommonTextTranslation("https://fanyi-api.baidu.com/api/trans/vip/translate", "通用文本翻译");

    private String value;

    private String desc;

    TranslationTypeEnum(String value, String desc) {
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
