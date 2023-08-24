package com.gong.tianapi.serviceImpl;

import com.gong.tianapi.service.TianApiCaiHongPiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TODO
 *
 * @author xiaogong
 * @since 2023/8/24 14:39
 */
@SpringBootTest
class TianApiCaiHongPiServiceImplTest {

    @Autowired
    private TianApiCaiHongPiService tianApiCaiHongPiService;

    @Test
    void getTianApiInfo() {
        System.out.println(tianApiCaiHongPiService.getTianApiInfo());
    }
}