package com.gong.tianapi.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TianApiCaiHongPiResponse {

    private Long code;

    private String msg;

    private TianApiCaiHongPiEntity result;
}
