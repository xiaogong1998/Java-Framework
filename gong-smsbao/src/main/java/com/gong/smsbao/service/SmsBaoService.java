package com.gong.smsbao.service;

import com.gong.smsbao.model.SmsBaoResponse;

public interface SmsBaoService {

    SmsBaoResponse send(String goodsId, String mobile, String content);
}
