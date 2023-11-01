package com.gong.wechat.applet.service;


import com.gong.wechat.applet.model.WeChatAppletMessageTemplate;

import java.util.List;
import java.util.Map;

public interface WeChatAppletSubscriptionService {

    /**
     * 服务初始化
     */
    void init();

    /**
     * 发送信息
     *
     * @param message 消息内容
     */
    void sendText(String toUser, String templateId, Map<String, WeChatAppletMessageTemplate> message);

    /**
     * 发送信息
     *
     * @param message 消息内容
     */
    void sendText(String toUser, String templateId, Map<String, WeChatAppletMessageTemplate> message, String lang);

    /**
     * 发送信息
     *
     * @param message 消息内容
     */
    void sendText(String toUser, String templateId, Map<String, WeChatAppletMessageTemplate> message, String miniprogramState, String lang);

    /**
     * 发送信息
     *
     * @param toUsers 用户列表
     * @param message 消息内容
     */
    void sendText(List<String> toUsers, String templateId, String page, Map<String, WeChatAppletMessageTemplate> message, String miniprogramState, String lang);
}
