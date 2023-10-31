package com.gong.wechat.subscription.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.gong.core.utils.HttpClientUtil;
import com.gong.core.utils.RandomColorUtils;
import com.gong.wechat.subscription.model.WeChatMessageRequest;
import com.gong.wechat.subscription.model.WeChatMessageResponse;
import com.gong.wechat.subscription.model.WeChatMessageTemplate;
import com.gong.wechat.subscription.enums.WeChatMessageErrEnum;
import com.gong.wechat.subscription.properties.WeChatSubscriptionProperties;
import com.gong.wechat.subscription.service.WeChatSubscriptionAccessToken;
import com.gong.wechat.subscription.service.WeChatSubscriptionMessageService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class WeChatSubscriptionMessageServiceImpl implements WeChatSubscriptionMessageService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final WeChatSubscriptionAccessToken weChatSubscriptionAccessToken;

    private final WeChatSubscriptionProperties properties;

    private final HttpClientUtil httpClient = new HttpClientUtil();

    private int retry = 0;

    public WeChatSubscriptionMessageServiceImpl(WeChatSubscriptionAccessToken weChatSubscriptionAccessToken, WeChatSubscriptionProperties properties) {
        super();
        this.weChatSubscriptionAccessToken = weChatSubscriptionAccessToken;
        this.properties = properties;
    }


    /**
     * 主动指定用户发送消息
     *
     * @param toUser  用户
     * @param message 内容
     * @return 推送响应
     */
    public WeChatMessageResponse send(String toUser, String templateId, Map<String, WeChatMessageTemplate> message) {
        String randomColor = RandomColorUtils.getRandomColor();
        return this.send(toUser, randomColor, templateId, message);
    }

    /**
     * 主动指定用户发送消息
     *
     * @param toUser   用户
     * @param topColor 顶部颜色
     * @param message  内容
     * @return 推送结果响应
     */
    public WeChatMessageResponse send(String toUser, String topColor, String templateId, Map<String, WeChatMessageTemplate> message) {
        if (StringUtils.isBlank(toUser)) {
            log.info("to user is null.");
        }
        WeChatMessageRequest<Map<String, WeChatMessageTemplate>> request = new WeChatMessageRequest<>();
        request.setToUser(toUser);
        request.setTemplateId(templateId);
        request.setTopColor(topColor);
        request.setData(message);
        return this.send(request);
    }

    /**
     * 主动发送消息(串行)
     *
     * @param request 发送的数据
     * @return 推送响应
     */
    private synchronized WeChatMessageResponse send(WeChatMessageRequest<Map<String, WeChatMessageTemplate>> request) {
        StringBuilder url = new StringBuilder();
        url.append(properties.getApiUrl()).append("/cgi-bin/message/template/send");
        url.append("?access_token=").append(weChatSubscriptionAccessToken.getAccessToken());
        String body = JSONObject.toJSONString(request);
        WeChatMessageResponse response = httpClient.postJson(url.toString(), body).map(r -> {
            log.info("push response : {}", r);
            return JSONObject.parseObject(r, WeChatMessageResponse.class);
        }).orElse(null);

        // access_token已过期, 重新获取Token
        if (response == null || !WeChatMessageErrEnum.RequestSucceeded.code().equals(response.getErrCode())) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 重试次数
            int maxRetry = 5;
            if (retry < maxRetry) {
                log.info("订阅号 Token 过期, 重新刷新Token");
                retry += 1;
                weChatSubscriptionAccessToken.refreshAccessToken();
                return send(request);
            } else {
                retry = 0;
            }
        }
        return response;
    }
}
