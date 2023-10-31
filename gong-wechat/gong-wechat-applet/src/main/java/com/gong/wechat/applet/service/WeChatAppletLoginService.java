package com.gong.wechat.applet.service;

import com.gong.wechat.applet.model.WeChatLoginRequest;
import com.gong.wechat.applet.model.WeChatLoginResponse;

/**
 * TODO 登录接口
 *
 * @author xiaogong
 * @since 2023/9/19 8:58
 */
public interface WeChatAppletLoginService {

    /**
     * 小程序登录
     *
     * @param jsCode 登录时获取的 code，可通过wx.login获取
     * @return 登录响应
     */
    WeChatLoginResponse login(String jsCode);

    /**
     * 小程序登录
     *
     * @param request 登录请求实体
     * @return 登录响应
     */
    WeChatLoginResponse login(WeChatLoginRequest request);
}
