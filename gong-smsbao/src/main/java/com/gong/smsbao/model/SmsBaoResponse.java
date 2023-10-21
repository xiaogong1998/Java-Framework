package com.gong.smsbao.model;

import com.gong.smsbao.enums.ResponseCodeEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * TODO
 *
 * @author xiaogong
 * @since 2023/10/20 18:17
 */
@Getter
@Setter
public class SmsBaoResponse implements Serializable {

    /**
     * 返回码
     */
    private String code;

    /**
     * 描述
     */
    private String message;

    public Boolean isSuccess() {
        return ResponseCodeEnum.Success.getValue().equals(code);
    }
}
