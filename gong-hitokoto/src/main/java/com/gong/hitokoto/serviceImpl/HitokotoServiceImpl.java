package com.gong.hitokoto.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.gong.core.utils.HttpClientUtil;
import com.gong.hitokoto.entity.HirohitoResponse;
import com.gong.hitokoto.properties.HitokotoProperties;
import com.gong.hitokoto.service.HitokotoService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HitokotoServiceImpl implements HitokotoService {

    private static final Logger log = LoggerFactory.getLogger(HitokotoServiceImpl.class);

    private final HitokotoProperties hitokotoProperties;

    private final HttpClientUtil httpClient = new HttpClientUtil();

    private int retry = 0;

    public HitokotoServiceImpl(HitokotoProperties hitokotoProperties) {
        this.hitokotoProperties = hitokotoProperties;
    }

    @Override
    public synchronized HirohitoResponse getHitokotoInfo() {
        StringBuilder url = new StringBuilder();
        url.append(hitokotoProperties.getApiUrl()).append("?");
        if (ObjectUtils.isNotEmpty(hitokotoProperties.getC())) {
            url.append("&c=").append(hitokotoProperties.getC().getValue());
        }
        if (StringUtils.isNotBlank(hitokotoProperties.getEncode())) {
            url.append("&encode=").append(hitokotoProperties.getEncode());
        }
        if (StringUtils.isNotBlank(hitokotoProperties.getCharset())) {
            url.append("&charset=").append(hitokotoProperties.getCharset());
        }
        if (ObjectUtils.isNotEmpty(hitokotoProperties.getMinLength())) {
            url.append("&min_length=").append(hitokotoProperties.getMinLength());
        }
        if (ObjectUtils.isNotEmpty(hitokotoProperties.getMaxLength())) {
            url.append("&max_length=").append(hitokotoProperties.getMaxLength());
        }
        log.info("hirohito request url : {}", url);
        HirohitoResponse response = httpClient.get(url.toString()).map(r -> {
            log.info("hirohito response : {}", r);
            return JSONObject.parseObject(r, HirohitoResponse.class);
        }).orElse(null);
        if (response == null) {
            log.info("一言语录获取失败，5秒后重试");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 重试次数
            int maxRetry = 5;
            if (retry < maxRetry) {
                retry += 1;
                return getHitokotoInfo();
            } else {
                retry = 0;
            }
        }
        return response;
    }
}
