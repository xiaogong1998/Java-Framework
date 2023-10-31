package com.gong.wechat.subscription.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.gong.core.exception.ServiceException;
import com.gong.wechat.subscription.model.WeChatMessageTemplate;
import com.gong.wechat.subscription.model.WeChatMessageResponse;
import com.gong.wechat.subscription.enums.WeChatMessageErrEnum;
import com.gong.wechat.subscription.properties.WeChatSubscriptionProperties;
import com.gong.wechat.subscription.service.WeChatSubscriptionService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class WeChatSubscriptionServiceImpl implements WeChatSubscriptionService {

    private static final Logger log = LoggerFactory.getLogger(WeChatSubscriptionServiceImpl.class);

    private WeChatSubscriptionMessageServiceImpl messageService;

    private final WeChatSubscriptionProperties properties;

    public WeChatSubscriptionServiceImpl(WeChatSubscriptionProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void init() {
        WeChatSubscriptionMemoryAccessToken subscriptionMemoryAccessToken = new WeChatSubscriptionMemoryAccessToken(properties);
        this.messageService = new WeChatSubscriptionMessageServiceImpl(subscriptionMemoryAccessToken, properties);
    }

    /**
     * 发送信息
     *
     * @param message 消息内容
     */
    public void sendText(String toUser, String templateId, Map<String, WeChatMessageTemplate> message) {
        sendText(Collections.singletonList(toUser), templateId, message);
    }

    /**
     * 发送信息
     *
     * @param toUsers 用户列表
     * @param message 消息内容
     */
    public void sendText(List<String> toUsers, String templateId, Map<String, WeChatMessageTemplate> message) {
        if (CollectionUtils.isEmpty(toUsers)) {
            log.error("toUsers is empty.");
            return;
        }
        toUsers.forEach(toUser -> {
            WeChatMessageResponse result = messageService.send(toUser, templateId, message);
            String jsonResult = JSONObject.toJSONString(result);
            log.info("push user: {}, response: {}", toUser, jsonResult);
            if (!WeChatMessageErrEnum.RequestSucceeded.code().equals(result.getErrCode())) {
                String error = Optional.ofNullable(result.getErrCode())
                        .map(WeChatMessageErrEnum::findByValue)
                        .map(WeChatMessageErrEnum::msg)
                        .map(msg -> msg + ": " + toUser)
                        .orElse(String.format("push error: {user: %s, result: %s}", toUser, jsonResult));
                throw new ServiceException(error);
            }
        });
    }
}
