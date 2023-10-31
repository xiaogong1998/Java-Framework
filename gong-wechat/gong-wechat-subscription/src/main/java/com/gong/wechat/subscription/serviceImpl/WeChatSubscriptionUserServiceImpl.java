package com.gong.wechat.subscription.serviceImpl;

import com.gong.wechat.subscription.model.WeChatSubscriptionUserTokenResponse;
import com.gong.wechat.subscription.service.WeChatSubscriptionUserAccessToken;
import com.gong.wechat.subscription.service.WeChatSubscriptionUserService;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author xiaogong
 * @since 2023/10/27 18:03
 */
@Service
public class WeChatSubscriptionUserServiceImpl implements WeChatSubscriptionUserService {

    private final WeChatSubscriptionUserAccessToken weChatSubscriptionUserAccessToken;

    public WeChatSubscriptionUserServiceImpl(WeChatSubscriptionUserAccessToken weChatSubscriptionUserAccessToken) {
        this.weChatSubscriptionUserAccessToken = weChatSubscriptionUserAccessToken;
    }

    @Override
    public WeChatSubscriptionUserTokenResponse getAccessToken(String code) {
        return weChatSubscriptionUserAccessToken.getAccessToken(code);
    }
}
