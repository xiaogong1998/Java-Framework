package com.gong.smsbao.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.gong.core.utils.HttpClientUtil;
import com.gong.smsbao.enums.ResponseCodeEnum;
import com.gong.smsbao.model.SmsBaoResponse;
import com.gong.smsbao.properties.SmsBaoProperties;
import com.gong.smsbao.service.SmsBaoService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class SmsBaoServiceImpl implements SmsBaoService {

    private static final Logger log = LoggerFactory.getLogger(SmsBaoServiceImpl.class);

    private final SmsBaoProperties properties;

    private final HttpClientUtil httpClient = new HttpClientUtil();

    private int retry = 0;

    public SmsBaoServiceImpl(SmsBaoProperties properties) {
        this.properties = properties;
    }

    @Override
    public synchronized SmsBaoResponse send(String goodsId, String mobile, String content) {
        StringBuilder url = new StringBuilder();
        url.append(properties.getApiUrl()).append("?");
        if (ObjectUtils.isNotEmpty(properties.getUsername())) {
            url.append("&u=").append(properties.getUsername());
        }
        if (StringUtils.isNotBlank(properties.getApiKey())) {
            url.append("&p=").append(md5(properties.getApiKey()));
        }
        if (StringUtils.isNotBlank(goodsId)) {
            url.append("&g=").append(goodsId);
        }
        if (ObjectUtils.isNotEmpty(mobile)) {
            url.append("&m=").append(mobile);
        }
        if (ObjectUtils.isNotEmpty(content)) {
            url.append("&c=").append(encodeUrlString(content));
        }
        log.info("smsbao request url : {}", url);
        String code = httpClient.get(url.toString()).map(r -> {
            log.info("smsbao response : {}", r);
            return JSONObject.parseObject(r, String.class);
        }).orElse(null);
        if (StringUtils.equals(code, "0")) {
            log.info("短信宝消息发送失败，5秒后重试");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 重试次数
            int maxRetry = 5;
            if (retry < maxRetry) {
                retry += 1;
                return send(goodsId, mobile, content);
            } else {
                retry = 0;
            }
        }
        SmsBaoResponse smsBaoResponse = new SmsBaoResponse();
        smsBaoResponse.setCode(code);
        String message = Optional.ofNullable(code).map(ResponseCodeEnum::findByValue).map(ResponseCodeEnum::getDesc).orElse("");
        smsBaoResponse.setMessage(message);
        return smsBaoResponse;
    }

    private static String md5(String plainText) {
        StringBuilder buf = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();
            int i;
            for (byte value : b) {
                i = value;
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    private static String encodeUrlString(String str) {
        String encodeStr;
        if (str == null)
            return null;
        try {
            encodeStr = java.net.URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return encodeStr;
    }
}
