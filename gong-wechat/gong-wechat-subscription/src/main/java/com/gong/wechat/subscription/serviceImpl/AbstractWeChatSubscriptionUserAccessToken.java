package com.gong.wechat.subscription.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.gong.core.utils.HttpClientUtil;
import com.gong.wechat.subscription.model.WeChatAccessTokenResponse;
import com.gong.wechat.subscription.model.WeChatSubscriptionUserTokenResponse;
import com.gong.wechat.subscription.properties.WeChatSubscriptionProperties;
import com.gong.wechat.subscription.service.WeChatSubscriptionUserAccessToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * TODO
 *
 * @author xiaogong
 * @since 2023/10/27 17:37
 */
public abstract class AbstractWeChatSubscriptionUserAccessToken implements WeChatSubscriptionUserAccessToken {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final WeChatSubscriptionProperties properties;

    private final HttpClientUtil httpClient = new HttpClientUtil();

    public AbstractWeChatSubscriptionUserAccessToken(WeChatSubscriptionProperties properties) {
        super();
        this.properties = properties;
    }

    /**
     * 刷新accessToken
     */
    @Override
    public synchronized void refreshAccessToken(String code) {
        log.debug("start refresh accessToken.");
        WeChatSubscriptionUserTokenResponse response = accessToken(code);
        if (response != null && (StringUtils.isBlank(response.getErrCode()) || StringUtils.equalsIgnoreCase(response.getErrCode(), "0"))) {
            set(response.getAccessToken(), response.getExpiresIn(), response);
            log.debug("AccessToken refresh succeeded");
        } else {
            log.debug("AccessToken refresh failed");
        }
    }

    /**
     * 获取access_token
     *
     * @return WeChatAccessTokenResponse
     */
    private WeChatSubscriptionUserTokenResponse accessToken(String code) {
        StringBuilder url = new StringBuilder();
        url.append(properties.getApiUrl()).append("/sns/oauth2/access_token");
        url.append("?appid=").append(properties.getAppId());
        url.append("&secret=").append(properties.getAppSecret());
        url.append("&code=").append(code);
        url.append("&grant_type=").append("authorization_code");
        log.debug("get userInfo request : {}", url.toString());
        return httpClient.get(url.toString()).map(r -> {
            log.debug("get userInfo response : {}", r);
            return JSONObject.parseObject(r, WeChatSubscriptionUserTokenResponse.class);
        }).orElse(null);
    }
}
