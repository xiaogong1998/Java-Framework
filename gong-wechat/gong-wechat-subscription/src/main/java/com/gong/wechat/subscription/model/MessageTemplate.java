package com.gong.wechat.subscription.model;

import com.gong.core.utils.RandomColorUtils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class MessageTemplate {

    private Object value;

    private String color;

    public MessageTemplate(Object value) {
        this.value = value;
        this.color = RandomColorUtils.getRandomColor();
    }

    public MessageTemplate(Object value, String color) {
        this.value = value;
        this.color = color;
    }
}
