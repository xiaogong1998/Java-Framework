package com.gong.wechat.subscription.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.gong.core.utils.HttpClientUtil;
import com.gong.wechat.subscription.entity.AccessTokenResponse;
import com.gong.wechat.subscription.properties.SubscriptionProperties;
import com.gong.wechat.subscription.service.SubscriptionAccessToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractSubscriptionAccessToken implements SubscriptionAccessToken {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final SubscriptionProperties properties;

    private final HttpClientUtil httpClient = new HttpClientUtil();

    public AbstractSubscriptionAccessToken(SubscriptionProperties properties) {
        super();
        this.properties = properties;
    }

    /**
     * 刷新accessToken
     */
    @Override
    public synchronized void refreshAccessToken() {
        log.debug("start refresh accessToken.");
        AccessTokenResponse response = accessToken();
        if (response != null && (StringUtils.isBlank(response.getErrCode()) || StringUtils.equalsIgnoreCase(response.getErrCode(), "0"))) {
            set(response.getAccessToken(), response.getExpiresIn());
            log.debug("AccessToken refresh succeeded");
        } else {
            log.debug("AccessToken refresh failed");
        }
    }

    /**
     * 获取access_token
     *
     * @return AccessTokenResponse
     */
    private AccessTokenResponse accessToken() {
        StringBuilder url = new StringBuilder();
        url.append(properties.getApiUrl()).append("/cgi-bin/token");
        url.append("?grant_type=client_credential");
        url.append("&appid=").append(properties.getAppId());
        url.append("&secret=").append(properties.getAppSecret());
        return httpClient.get(url.toString()).map(r -> {
            log.debug("accessToken response : {}", r);
            return JSONObject.parseObject(r, AccessTokenResponse.class);
        }).orElse(null);
    }
}
