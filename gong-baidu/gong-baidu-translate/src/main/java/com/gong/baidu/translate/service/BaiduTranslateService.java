package com.gong.baidu.translate.service;

import com.gong.baidu.translate.model.CommonTextRequest;
import com.gong.baidu.translate.model.CommonTextResponse;

/**
 * TODO 登录接口
 *
 * @author xiaogong
 * @since 2023/9/19 8:58
 */
public interface BaiduTranslateService {

    CommonTextResponse commonTextTranslate(CommonTextRequest request);

}
