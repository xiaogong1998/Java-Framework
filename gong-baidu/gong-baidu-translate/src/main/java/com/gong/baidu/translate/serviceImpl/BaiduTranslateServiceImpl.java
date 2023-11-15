package com.gong.baidu.translate.serviceImpl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.gong.baidu.translate.enums.TranslationTypeEnum;
import com.gong.baidu.translate.model.CommonTextRequest;
import com.gong.baidu.translate.model.CommonTextResponse;
import com.gong.baidu.translate.model.TransResultModel;
import com.gong.baidu.translate.properties.BaiduTranslateProperties;
import com.gong.baidu.translate.service.BaiduTranslateService;
import com.gong.core.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.Charset;

/**
 * TODO 百度翻译
 *
 * @author xiaogong
 * @since 2023/9/19 8:58
 */
@Service
public class BaiduTranslateServiceImpl implements BaiduTranslateService {

    private static final Logger log = LoggerFactory.getLogger(BaiduTranslateServiceImpl.class);

    private final HttpClientUtil httpClient = new HttpClientUtil();

    private int retry = 0;

    @Resource
    private BaiduTranslateProperties properties;

    public String commonTextTranslateResult(CommonTextRequest request){
        CommonTextResponse commonTextResponse = this.commonTextTranslate(request);
        if(null == commonTextResponse){
            return null;
        }
        TransResultModel transResultModel = commonTextResponse.getTransResult().stream().findFirst().orElse(null);
        if(null == transResultModel){
            return null;
        }
        return transResultModel.getDst();
    }

    @Override
    public CommonTextResponse commonTextTranslate(CommonTextRequest request) {
        String salt = RandomUtil.randomNumbers(10);
        String sign = properties.getAppid() + request.getQ() + salt + properties.getSecret();

        StringBuilder url = new StringBuilder();
        url.append(TranslationTypeEnum.CommonTextTranslation.getValue());
        url.append("?q=").append(request.getQ());
        url.append("&from=").append(request.getFrom().getValue());
        url.append("&to=").append(request.getTo().getValue());
        url.append("&appid=").append(properties.getAppid());
        url.append("&salt=").append(salt);
        url.append("&sign=").append(SecureUtil.md5(sign));
        log.info("baidu common text translate request url : {}", url);
        CommonTextResponse response = httpClient.get(url.toString()).map(r -> {
            log.info("baidu common text translate response : {}", r);
            return JSONObject.parseObject(r, CommonTextResponse.class);
        }).orElse(null);
        if (response == null || (null != response.getErrorCode() && 52000 != response.getErrorCode())) {
            log.info("通用文本翻译失败，1秒后重试");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 重试次数
            int maxRetry = 1;
            if (retry < maxRetry) {
                retry += 1;
                return commonTextTranslate(request);
            } else {
                retry = 0;
            }
        }
        return response;
    }
}
