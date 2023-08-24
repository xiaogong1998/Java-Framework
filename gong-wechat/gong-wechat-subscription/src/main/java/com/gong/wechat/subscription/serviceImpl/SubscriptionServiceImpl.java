package com.gong.wechat.subscription.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.gong.core.exception.ServiceException;
import com.gong.wechat.subscription.entity.MessageTemplate;
import com.gong.wechat.subscription.entity.MessageResponse;
import com.gong.wechat.subscription.enums.MessageErrEnum;
import com.gong.wechat.subscription.properties.SubscriptionProperties;
import com.gong.wechat.subscription.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    private SubscriptionMessageServiceImpl messageService;

    private final SubscriptionProperties properties;

    public SubscriptionServiceImpl(SubscriptionProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void init() {
        SubscriptionMemoryAccessToken subscriptionMemoryAccessToken = new SubscriptionMemoryAccessToken(properties);
        this.messageService = new SubscriptionMessageServiceImpl(subscriptionMemoryAccessToken, properties);
    }

    /**
     * 发送信息
     *
     * @param message 消息内容
     */
    public void sendText(String toUser, String templateId, Map<String, MessageTemplate> message) {
        sendText(Collections.singletonList(toUser), templateId, message);
    }

    /**
     * 发送信息
     *
     * @param toUsers 用户列表
     * @param message 消息内容
     */
    public void sendText(List<String> toUsers, String templateId, Map<String, MessageTemplate> message) {
        toUsers.forEach(toUser -> {
            MessageResponse result = messageService.send(toUser, templateId, message);
            String jsonResult = JSONObject.toJSONString(result);
            log.info("push user: {}, response: {}", toUser, jsonResult);
            if (!MessageErrEnum.RequestSucceeded.code().equals(result.getErrCode())) {
                String error = Optional.ofNullable(result.getErrCode())
                        .map(MessageErrEnum::findByValue)
                        .map(MessageErrEnum::msg)
                        .map(msg -> msg + ": " + toUser)
                        .orElse(String.format("push error: {user: %s, result: %s}", toUser, jsonResult));
                throw new ServiceException(error);
            }
        });
    }
}
