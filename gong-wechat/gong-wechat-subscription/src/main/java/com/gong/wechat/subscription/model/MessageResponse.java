package com.gong.wechat.subscription.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponse {

    @JSONField(name = "errcode")
    private Long errCode;

    @JSONField(name = "errmsg")
    private String errMsg;

    @JSONField(name = "msgid")
    private Long msgId;

    public Long getErrCode() {
        return errCode;
    }

    public void setErrCode(Long errCode) {
        this.errCode = errCode;
    }
}
