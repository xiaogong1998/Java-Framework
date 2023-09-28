package com.gong.baidu.translate.serviceImpl;

import com.gong.baidu.translate.enums.LanguageEnum;
import com.gong.baidu.translate.model.CommonTextRequest;
import com.gong.baidu.translate.model.CommonTextResponse;
import com.gong.baidu.translate.service.BaiduTranslateService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO
 *
 * @author xiaogong
 * @since 2023/9/28 15:23
 */
@SpringBootTest
class BaiduTranslateServiceImplTest {

    @Resource
    private BaiduTranslateService baiduTranslateService;

    @Test
    void commonTextTranslate() {
        CommonTextRequest request = new CommonTextRequest();
        request.setQ("《叶圣陶散文》为“名家经典珍藏”丛书之一，收录了叶圣陶先生的散文精品数十篇。这些作品内容丰富，题材各异，构思精巧，文笔精巧、语言幽默、内蕴深厚、风格恬淡，充分显示了叶圣陶先生的文学功底及丰富的人生阅历，从一个侧面反映了作者的思想感情及创作风格，非常值得一读。叶圣陶是20世纪中国一位杰出的作家、教育家和出版家，又是中国现代儿童文学创作的先行者。作为散文家，他早期和周作人、朱自清共同成为文学研究会散文创作的中坚，后来又成为开明派散文的代表，其散文被一九三五年出版的《中国新文学大系》选录的篇数仅次于周作人、鲁迅和朱自清。");
        request.setTo(LanguageEnum.English);
        CommonTextResponse commonTextResponse = baiduTranslateService.commonTextTranslate(request);
        System.out.println(commonTextResponse);
    }
}