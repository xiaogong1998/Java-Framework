package com.gong.wechat.applet.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.gong.core.utils.HttpClientUtil;
import com.gong.wechat.applet.model.WeChatAppletAccessTokenResponse;
import com.gong.wechat.applet.properties.WeChatAppletProperties;
import com.gong.wechat.applet.service.WeChatAppletSubscriptionAccessToken;
import com.gong.wechat.subscription.model.WeChatAccessTokenResponse;
import com.gong.wechat.subscription.properties.WeChatSubscriptionProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractWeChatAppletSubscriptionAccessToken implements WeChatAppletSubscriptionAccessToken {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final WeChatAppletProperties properties;

    private final HttpClientUtil httpClient = new HttpClientUtil();

    public AbstractWeChatAppletSubscriptionAccessToken(WeChatAppletProperties properties) {
        super();
        this.properties = properties;
    }

    /**
     * 刷新accessToken
     */
    @Override
    public synchronized void refreshAccessToken() {
        log.debug("start refresh accessToken.");
        WeChatAppletAccessTokenResponse response = accessToken();
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
     * @return WeChatAccessTokenResponse
     */
    private WeChatAppletAccessTokenResponse accessToken() {
        StringBuilder url = new StringBuilder();
        url.append(properties.getApiUrl()).append("/cgi-bin/token");
        url.append("?grant_type=client_credential");
        url.append("&appid=").append(properties.getAppid());
        url.append("&secret=").append(properties.getSecret());
        return httpClient.get(url.toString()).map(r -> {
            log.debug("accessToken response : {}", r);
            return JSONObject.parseObject(r, WeChatAppletAccessTokenResponse.class);
        }).orElse(null);
    }
}
