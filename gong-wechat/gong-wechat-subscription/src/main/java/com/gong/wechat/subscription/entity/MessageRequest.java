package com.gong.wechat.subscription.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequest<T> {

    @JSONField(name = "touser")
    private String toUser;

    @JSONField(name = "template_id")
    private String templateId;

    @JSONField(name = "topcolor")
    private String topColor;

    @JSONField(name = "data")
    private T data;
}
