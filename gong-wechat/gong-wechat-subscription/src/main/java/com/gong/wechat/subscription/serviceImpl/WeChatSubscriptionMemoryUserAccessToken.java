package com.gong.wechat.subscription.serviceImpl;

import com.gong.core.exception.ServiceException;
import com.gong.wechat.subscription.model.WeChatSubscriptionUserTokenResponse;
import com.gong.wechat.subscription.properties.WeChatSubscriptionProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WeChatSubscriptionMemoryUserAccessToken extends AbstractWeChatSubscriptionUserAccessToken {

    public WeChatSubscriptionMemoryUserAccessToken(WeChatSubscriptionProperties properties) {
        super(properties);
    }


    // Token
    private String accessToken;

    // 过期时间
    private Date expireTime;

    // 全部信息
    private WeChatSubscriptionUserTokenResponse response;

    @Override
    public WeChatSubscriptionUserTokenResponse getAccessToken(String code) {
        if (StringUtils.isBlank(accessToken) || isExpired()) {
            refreshAccessToken(code);
        }
        return response;
    }

    @Override
    public boolean isExpired() {
        return expireTime.after(new Date());
    }

    @Override
    public void set(String accessToken, int expiresIn, WeChatSubscriptionUserTokenResponse response) {
        Date expireTime = DateUtils.addSeconds(new Date(), expiresIn - 120);
        this.accessToken = accessToken;
        this.expireTime = expireTime;
        this.response = response;
    }
}
