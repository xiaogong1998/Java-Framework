package com.gong.baidu.translate.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * TODO 翻译结果
 *
 * @author xiaogong
 * @since 2023/9/28 15:05
 */
@Getter
@Setter
public class TransResultModel implements Serializable {

    /**
     * 原文
     */
    private String src;

    /**
     * 译文
     */
    private String dst;
}
