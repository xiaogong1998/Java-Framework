package com.gong.baidu.translate.controller;

import com.alibaba.fastjson.JSONObject;
import com.gong.baidu.translate.enums.LanguageEnum;
import com.gong.baidu.translate.model.CommonTextRequest;
import com.gong.baidu.translate.service.BaiduTranslateService;
import com.gong.baidu.translate.vo.TranslationVO;
import com.gong.core.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * TODO 百度翻译
 *
 * @author xiaogong
 * @since 2023/10/7 10:21
 */
@RestController
@RequestMapping("/gong/translate")
public class BaiduTranslateController {

    @Autowired
    private BaiduTranslateService baiduTranslateService;

    @PostMapping("/commonText/content")
    public Result<String> commonTextTranslation(@RequestBody TranslationVO translationVO) {
        String language = translationVO.getLanguage();
        String content = translationVO.getContent();
        CommonTextRequest request = new CommonTextRequest();
        request.setTo(LanguageEnum.findByValue(language));
        request.setQ(content);
        String s = baiduTranslateService.commonTextTranslateResult(request);
        return Result.OK(s);
    }

    @PostMapping("/commonText/contextList")
    public Result<List<JSONObject>> commonTextTranslationList(@RequestBody TranslationVO translationVO) {
        String language = translationVO.getLanguage();
        List<JSONObject> lists = translationVO.getList();
        List<String> translationFields = translationVO.getTranslationFields();
        for (JSONObject list : lists) {
            for (String translationField : translationFields) {
                if (list.containsKey(translationField)) {
                    String value = list.getString(translationField);
                    CommonTextRequest request = new CommonTextRequest();
                    request.setTo(LanguageEnum.findByValue(language));
                    request.setQ(value);
                    String s = baiduTranslateService.commonTextTranslateResult(request);
                    list.put(translationField + "_" + language, s);
                }
            }
        }
        return Result.OK(lists);
    }

}
