package com.gong.wechat.applet.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeChatAppletMessageResponse {

    @JSONField(name = "errcode")
    private Long errCode;

    @JSONField(name = "errmsg")
    private String errMsg;

    @JSONField(name = "msgid")
    private Long msgId;
}
