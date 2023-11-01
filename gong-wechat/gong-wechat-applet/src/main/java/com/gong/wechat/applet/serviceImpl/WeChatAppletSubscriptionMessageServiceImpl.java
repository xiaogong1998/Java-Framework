package com.gong.wechat.applet.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.gong.core.utils.HttpClientUtil;
import com.gong.wechat.applet.enums.WeChatAppletMessageErrEnum;
import com.gong.wechat.applet.model.WeChatAppletMessageRequest;
import com.gong.wechat.applet.model.WeChatAppletMessageResponse;
import com.gong.wechat.applet.model.WeChatAppletMessageTemplate;
import com.gong.wechat.applet.properties.WeChatAppletProperties;
import com.gong.wechat.applet.service.WeChatAppletSubscriptionAccessToken;
import com.gong.wechat.applet.service.WeChatAppletSubscriptionMessageService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class WeChatAppletSubscriptionMessageServiceImpl implements WeChatAppletSubscriptionMessageService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final WeChatAppletSubscriptionAccessToken weChatAppletSubscriptionAccessToken;

    private final WeChatAppletProperties properties;

    private final HttpClientUtil httpClient = new HttpClientUtil();

    private int retry = 0;

    public WeChatAppletSubscriptionMessageServiceImpl(WeChatAppletSubscriptionAccessToken weChatAppletSubscriptionAccessToken, WeChatAppletProperties properties) {
        super();
        this.weChatAppletSubscriptionAccessToken = weChatAppletSubscriptionAccessToken;
        this.properties = properties;
    }


    /**
     * 主动指定用户发送消息
     *
     * @param toUser   用户
     * @param message  内容
     * @return 推送结果响应
     */
    public WeChatAppletMessageResponse send(String toUser, String templateId, Map<String, WeChatAppletMessageTemplate> message) {
        if (StringUtils.isBlank(toUser)) {
            log.info("to user is null.");
        }
        WeChatAppletMessageRequest<Map<String, WeChatAppletMessageTemplate>> request = new WeChatAppletMessageRequest<>();
        request.setToUser(toUser);
        request.setTemplateId(templateId);
        request.setData(message);
        return this.send(request);
    }

    /**
     * 主动发送消息(串行)
     *
     * @param request 发送的数据
     * @return 推送响应
     */
    public synchronized WeChatAppletMessageResponse send(WeChatAppletMessageRequest<Map<String, WeChatAppletMessageTemplate>> request) {
        StringBuilder url = new StringBuilder();
        url.append(properties.getApiUrl()).append("/cgi-bin/message/subscribe/send");
        url.append("?access_token=").append(weChatAppletSubscriptionAccessToken.getAccessToken());
        String body = JSONObject.toJSONString(request);
        WeChatAppletMessageResponse response = httpClient.postJson(url.toString(), body).map(r -> {
            log.info("push response : {}", r);
            return JSONObject.parseObject(r, WeChatAppletMessageResponse.class);
        }).orElse(null);

        // access_token已过期, 重新获取Token
        if (response == null || !WeChatAppletMessageErrEnum.RequestSucceeded.code().equals(response.getErrCode())) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 重试次数
            int maxRetry = 5;
            if (retry < maxRetry) {
                log.info("小程序消息订阅 Token 过期, 重新刷新Token");
                retry += 1;
                weChatAppletSubscriptionAccessToken.refreshAccessToken();
                return send(request);
            } else {
                retry = 0;
            }
        }
        return response;
    }
}
