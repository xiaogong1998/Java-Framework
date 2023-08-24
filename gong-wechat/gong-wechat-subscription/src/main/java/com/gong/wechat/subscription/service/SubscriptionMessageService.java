package com.gong.wechat.subscription.service;


import com.gong.wechat.subscription.entity.MessageTemplate;
import com.gong.wechat.subscription.entity.MessageResponse;

import java.util.Map;

public interface SubscriptionMessageService {

    /**
     * 主动指定用户发送消息
     *
     * @return 推送结果响应
     */
    MessageResponse send(String toUser, String templateId, Map<String, MessageTemplate> message);

}
