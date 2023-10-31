package com.gong.wechat.subscription.service;

import com.gong.wechat.subscription.model.WeChatSubscriptionUserTokenResponse;

/**
 * 微信AccessToken
 *
 * @author xiliu
 */
public interface WeChatSubscriptionUserAccessToken {

    /**
     * 获取Token
     *
     * @return
     */
    WeChatSubscriptionUserTokenResponse getAccessToken(String code);

    /**
     * 刷新Token
     */
    void refreshAccessToken(String code);


    /**
     * Token 是否过期
     *
     * @return
     */
    boolean isExpired();

    /**
     * 设置token
     *
     * @param accessToken
     * @param expiresIn
     */
    void set(String accessToken, int expiresIn, WeChatSubscriptionUserTokenResponse response);


}
