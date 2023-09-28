package com.gong.wechat.subscription.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.gong.core.utils.HttpClientUtil;
import com.gong.core.utils.RandomColorUtils;
import com.gong.wechat.subscription.model.MessageRequest;
import com.gong.wechat.subscription.model.MessageResponse;
import com.gong.wechat.subscription.model.MessageTemplate;
import com.gong.wechat.subscription.enums.MessageErrEnum;
import com.gong.wechat.subscription.properties.SubscriptionProperties;
import com.gong.wechat.subscription.service.SubscriptionAccessToken;
import com.gong.wechat.subscription.service.SubscriptionMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class SubscriptionMessageServiceImpl implements SubscriptionMessageService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final SubscriptionAccessToken subscriptionAccessToken;

    private final SubscriptionProperties properties;

    private final HttpClientUtil httpClient = new HttpClientUtil();

    private int retry = 0;

    public SubscriptionMessageServiceImpl(SubscriptionAccessToken subscriptionAccessToken, SubscriptionProperties properties) {
        super();
        this.subscriptionAccessToken = subscriptionAccessToken;
        this.properties = properties;
    }


    /**
     * 主动指定用户发送消息
     *
     * @param toUser  用户
     * @param message 内容
     * @return 推送响应
     */
    public MessageResponse send(String toUser, String templateId, Map<String, MessageTemplate> message) {
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
    public MessageResponse send(String toUser, String topColor, String templateId, Map<String, MessageTemplate> message) {
        MessageRequest<Map<String, MessageTemplate>> request = new MessageRequest<>();
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
    private synchronized MessageResponse send(MessageRequest<Map<String, MessageTemplate>> request) {
        StringBuilder url = new StringBuilder();
        url.append(properties.getApiUrl()).append("/cgi-bin/message/template/send");
        url.append("?access_token=").append(subscriptionAccessToken.getAccessToken());
        String body = JSONObject.toJSONString(request);
        MessageResponse response = httpClient.postJson(url.toString(), body).map(r -> {
            log.info("push response : {}", r);
            return JSONObject.parseObject(r, MessageResponse.class);
        }).orElse(null);

        // access_token已过期, 重新获取Token
        if (response == null || !MessageErrEnum.RequestSucceeded.code().equals(response.getErrCode())) {
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
                subscriptionAccessToken.refreshAccessToken();
                return send(request);
            } else {
                retry = 0;
            }
        }
        return response;
    }
}
