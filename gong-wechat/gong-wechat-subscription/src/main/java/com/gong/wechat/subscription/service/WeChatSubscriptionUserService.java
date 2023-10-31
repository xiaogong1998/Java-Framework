package com.gong.wechat.subscription.service;

import com.gong.wechat.subscription.model.WeChatSubscriptionUserTokenResponse;

/**
 * TODO
 *
 * @author xiaogong
 * @since 2023/10/27 18:02
 */
public interface WeChatSubscriptionUserService {

    WeChatSubscriptionUserTokenResponse getAccessToken(String code);
}
