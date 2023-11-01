package com.gong.wechat.applet.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.gong.wechat.applet.enums.LangEnum;
import com.gong.wechat.applet.enums.MiniprogramStateEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeChatAppletMessageRequest<T> {

    @JSONField(name = "template_id")
    private String templateId;

    @JSONField(name = "page")
    private String page;

    @JSONField(name = "touser")
    private String toUser;

    @JSONField(name = "data")
    private T data;

    @JSONField(name = "miniprogram_state")
    private MiniprogramStateEnum miniProgramState = MiniprogramStateEnum.formal;

    @JSONField(name = "lang")
    private LangEnum lang = LangEnum.zh_CN;
}
