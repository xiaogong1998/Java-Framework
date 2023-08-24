package com.gong.wechat.subscription.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseTemplateIdEntity {

    // 默认模板ID
    private String defaultId;

    // 凌晨模板ID
    private String dawnId;

    // 早上模板ID
    private String morningId;

    // 上午模板ID
    private String amId;

    // 中午模板ID
    private String noonId;

    // 下午模板ID
    private String afternoonId;

    // 傍晚模板ID
    private String eveningId;

    // 晚上模板ID
    private String nightId;
}
