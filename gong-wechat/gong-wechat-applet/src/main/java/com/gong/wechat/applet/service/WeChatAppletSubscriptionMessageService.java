package com.gong.wechat.applet.service;


import com.gong.wechat.applet.model.WeChatAppletMessageRequest;
import com.gong.wechat.applet.model.WeChatAppletMessageResponse;
import com.gong.wechat.applet.model.WeChatAppletMessageTemplate;

import java.util.List;
import java.util.Map;

public interface WeChatAppletSubscriptionMessageService {

    /**
     * 主动指定用户发送消息
     *
     * @return 推送结果响应
     */
    WeChatAppletMessageResponse send(WeChatAppletMessageRequest<Map<String, WeChatAppletMessageTemplate>> request);

}
