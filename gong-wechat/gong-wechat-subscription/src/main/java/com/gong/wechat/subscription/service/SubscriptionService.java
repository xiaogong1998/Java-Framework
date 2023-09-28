package com.gong.wechat.subscription.service;


import com.gong.wechat.subscription.model.MessageTemplate;

import java.util.List;
import java.util.Map;

public interface SubscriptionService {

    /**
     * 服务初始化
     */
    void init();

    /**
     * 发送信息
     *
     * @param message 消息内容
     */
    void sendText(String toUser, String templateId, Map<String, MessageTemplate> message);

    /**
     * 发送信息
     *
     * @param toUsers 用户列表
     * @param message 消息内容
     */
    void sendText(List<String> toUsers, String templateId, Map<String, MessageTemplate> message);
}
