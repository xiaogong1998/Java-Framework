package com.gong.tianapi.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.gong.core.utils.HttpClientUtil;
import com.gong.tianapi.entity.TianApiCaiHongPiResponse;
import com.gong.tianapi.properties.TianApiProperties;
import com.gong.tianapi.service.TianApiCaiHongPiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TianApiCaiHongPiServiceImpl implements TianApiCaiHongPiService {

    private static final Logger log = LoggerFactory.getLogger(TianApiCaiHongPiServiceImpl.class);

    private final TianApiProperties tianApiProperties;

    private final HttpClientUtil httpClient = new HttpClientUtil();

    private int retry = 0;

    public TianApiCaiHongPiServiceImpl(TianApiProperties tianApiProperties) {
        this.tianApiProperties = tianApiProperties;
    }

    @Override
    public synchronized TianApiCaiHongPiResponse getTianApiInfo() {
        StringBuilder url = new StringBuilder();
        url.append(tianApiProperties.getApiUrl());
        url.append("/caihongpi/index");
        url.append("?key=").append(tianApiProperties.getKey());
        TianApiCaiHongPiResponse response = httpClient.get(url.toString()).map(r -> {
            log.info("tianapi response : {}", r);
            return JSONObject.parseObject(r, TianApiCaiHongPiResponse.class);
        }).orElse(null);
        if (response == null) {
            log.info("彩虹屁语录获取失败，5秒后重试");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 重试次数
            int maxRetry = 5;
            if (retry < maxRetry) {
                retry += 1;
                return getTianApiInfo();
            } else {
                retry = 0;
            }
        }
        return response;
    }
}
