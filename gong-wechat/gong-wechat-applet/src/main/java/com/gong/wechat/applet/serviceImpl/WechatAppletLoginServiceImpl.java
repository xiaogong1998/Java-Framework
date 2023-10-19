package com.gong.wechat.applet.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.gong.core.utils.HttpClientUtil;
import com.gong.wechat.applet.model.LoginRequest;
import com.gong.wechat.applet.model.LoginResponse;
import com.gong.wechat.applet.properties.WechatAppletProperties;
import com.gong.wechat.applet.service.WechatAppletLoginService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * TODO 登录实现
 *
 * @author xiaogong
 * @since 2023/9/19 8:58
 */
@Service
public class WechatAppletLoginServiceImpl implements WechatAppletLoginService {

    private static final Logger log = LoggerFactory.getLogger(WechatAppletLoginServiceImpl.class);

    private final HttpClientUtil httpClient = new HttpClientUtil();

    private int retry = 0;

    @Resource
    private WechatAppletProperties properties;

    @Override
    public LoginResponse login(String jsCode) {
        LoginRequest request = new LoginRequest();
        request.setJsCode(jsCode);
        return login(request);
    }

    @Override
    public synchronized LoginResponse login(LoginRequest request) {
        StringBuilder url = new StringBuilder();
        url.append(properties.getApiUrl());
        url.append("?appid=").append(properties.getAppid());
        url.append("&secret=").append(properties.getSecret());
        url.append("&js_code=").append(request.getJsCode());
        url.append("&grant_type").append(request.getGrantType());
        LoginResponse response = httpClient.get(url.toString()).map(r -> {
            log.info("wechat applet response : {}", r);
            return JSONObject.parseObject(r, LoginResponse.class);
        }).orElse(null);
        if (response == null || StringUtils.isBlank(response.getOpenId())) {
            log.info("微信小程序登录失败，1秒后重试");
            if (null != response) {
                log.info("错误码：{}，错误原因：{}", response.getErrCode(), response.getErrMsg());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 重试次数
            int maxRetry = 5;
            if (retry < maxRetry) {
                retry += 1;
                return login(request);
            } else {
                retry = 0;
            }
        }
        return response;
    }
}
