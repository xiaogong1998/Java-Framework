package com.gong.baidu.translate.service;

import com.gong.baidu.translate.model.CommonTextRequest;
import com.gong.baidu.translate.model.CommonTextResponse;

/**
 * TODO 百度翻译
 *
 * @author xiaogong
 * @since 2023/9/19 8:58
 */
public interface BaiduTranslateService {

    String commonTextTranslateResult(CommonTextRequest request);

    CommonTextResponse commonTextTranslate(CommonTextRequest request);

}
