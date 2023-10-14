package com.gong.baidu.translate.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * TODO
 *
 * @author xiaogong
 * @since 2023/10/7 10:44
 */
@Getter
@Setter
public class TranslationVO implements Serializable {

    private String language;

    private String content;

    private List<JSONObject> list;

    private List<String> translationFields;
}
