package com.gong.wechat.applet.model;

import com.gong.core.utils.RandomColorUtils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class WeChatAppletMessageTemplate {

    private Object value;

    public WeChatAppletMessageTemplate(Object value) {
        this.value = value;
    }
}
