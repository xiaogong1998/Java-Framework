package com.gong.wechat.subscription.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO
 *
 * @author xiaogong
 * @since 2023/10/27 17:31
 */
@Getter
@Setter
public class WeChatSubscriptionUserTokenResponse extends WeChatSubscriptionBaseResponse {

    @JSONField(name = "access_token")
    private String accessToken;

    @JSONField(name = "expires_in")
    private int expiresIn;

    @JSONField(name = "refresh_token")
    private String refreshToken;

    @JSONField(name = "openid")
    private String openid;

    @JSONField(name = "scope")
    private String scope;

    @JSONField(name = "is_snapshotuser")
    private String isSnapshotuser;

    @JSONField(name = "unionid")
    private String unionid;

}
