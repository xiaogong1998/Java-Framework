package com.gong.wechat.applet.serviceImpl;

import com.gong.wechat.applet.properties.WeChatAppletProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

public class WeChatAppletSubscriptionMemoryAccessToken extends AbstractWeChatAppletSubscriptionAccessToken {

    public WeChatAppletSubscriptionMemoryAccessToken(WeChatAppletProperties properties) {
        super(properties);
    }

    // Token
    private String accessToken;

    // 过期时间
    private Date expireTime;

    @Override
    public String getAccessToken() {
        if (StringUtils.isBlank(accessToken) || isExpired()) {
            refreshAccessToken();
        }
        return accessToken;
    }

    @Override
    public boolean isExpired() {
        return expireTime.after(new Date());
    }

    @Override
    public void set(String accessToken, int expiresIn) {
        Date expireTime = DateUtils.addSeconds(new Date(), expiresIn - 120);
        this.accessToken = accessToken;
        this.expireTime = expireTime;
    }
}
