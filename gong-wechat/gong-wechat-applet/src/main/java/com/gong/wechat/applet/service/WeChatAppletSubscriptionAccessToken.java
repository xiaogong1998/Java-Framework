package com.gong.wechat.applet.service;

/**
 * 微信AccessToken
 * 
 * @author xiliu
 *
 */
public interface WeChatAppletSubscriptionAccessToken {

    /**
     * 获取Token
     * 
     * @return
     */
    String getAccessToken();

    /**
     * 刷新Token
     */
    void refreshAccessToken();

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
    void set(String accessToken, int expiresIn);

}