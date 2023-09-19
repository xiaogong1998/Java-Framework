package com.gong.wechat.applet.service;

import com.gong.wechat.applet.entity.LoginRequest;
import com.gong.wechat.applet.entity.LoginResponse;

/**
 * TODO 登录接口
 *
 * @author xiaogong
 * @since 2023/9/19 8:58
 */
public interface LoginService {

    /**
     * 小程序登录
     *
     * @param jsCode 登录时获取的 code，可通过wx.login获取
     * @return 登录响应
     */
    LoginResponse login(String jsCode);

    /**
     * 小程序登录
     *
     * @param request 登录请求实体
     * @return 登录响应
     */
    LoginResponse login(LoginRequest request);
}
