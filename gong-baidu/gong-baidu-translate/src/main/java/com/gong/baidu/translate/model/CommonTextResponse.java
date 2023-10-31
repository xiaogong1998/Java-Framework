package com.gong.baidu.translate.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO 通用文本翻译响应
 *
 * @author xiaogong
 * @since 2023/9/19 8:50
 */
@Getter
@Setter
public class CommonTextResponse implements Serializable {

    /**
     * 源语言
     */
   private String from;

    /**
     * 目标语言
     */
   private String to;

    /**
     * 目标语言
     */
   @JSONField(name = "trans_result")
   private List<TransResultModel> transResult = new ArrayList<>();

    /**
     * 错误码
     */
   @JSONField(name = "error_code")
   private Integer errorCode;

}
