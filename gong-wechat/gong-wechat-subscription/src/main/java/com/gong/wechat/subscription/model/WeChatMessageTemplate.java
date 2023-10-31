package com.gong.wechat.subscription.model;

import com.gong.core.utils.RandomColorUtils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class WeChatMessageTemplate {

    private Object value;

    private String color;

    public WeChatMessageTemplate(Object value) {
        this.value = value;
        this.color = RandomColorUtils.getRandomColor();
    }

    public WeChatMessageTemplate(Object value, String color) {
        this.value = value;
        this.color = color;
    }
}
