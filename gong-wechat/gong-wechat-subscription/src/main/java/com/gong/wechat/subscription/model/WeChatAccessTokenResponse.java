package com.gong.wechat.subscription.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeChatAccessTokenResponse extends WeChatSubscriptionBaseResponse {

    @JSONField(name = "access_token")
    private String accessToken;

    @JSONField(name = "expires_in")
    private int expiresIn;

}
