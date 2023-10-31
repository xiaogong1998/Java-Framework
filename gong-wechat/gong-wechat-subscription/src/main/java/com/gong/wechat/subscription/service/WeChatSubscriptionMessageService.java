package com.gong.wechat.subscription.service;


import com.gong.wechat.subscription.model.WeChatMessageTemplate;
import com.gong.wechat.subscription.model.WeChatMessageResponse;

import java.util.Map;

public interface WeChatSubscriptionMessageService {

    /**
     * 主动指定用户发送消息
     *
     * @return 推送结果响应
     */
    WeChatMessageResponse send(String toUser, String templateId, Map<String, WeChatMessageTemplate> message);

}
