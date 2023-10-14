package com.gong.baidu.translate.model;

import com.gong.baidu.translate.enums.LanguageEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * TODO 通用文本翻译请求
 *
 * @author xiaogong
 * @since 2023/9/19 8:48
 */
@Getter
@Setter
public class CommonTextRequest implements Serializable {

    /**
     * 请求翻译query
     */
    private String q;

    /**
     * 翻译源语言
     */
    private LanguageEnum from = LanguageEnum.Auto;

    /**
     * 翻译目标语言
     */
    private LanguageEnum to;

    /**
     * 判断是否需要使用自定义术语干预API
     */
    private Integer action = 0;

}
