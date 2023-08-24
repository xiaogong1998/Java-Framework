package com.gong.hitokoto.serviceImpl;

import com.gong.hitokoto.service.HitokotoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO
 *
 * @author xiaogong
 * @since 2023/8/24 11:14
 */
@SpringBootTest
class HitokotoServiceImplTest {

    @Autowired
    private HitokotoService hitokotoService;

    @Test
    void getHitokotoInfo() {
        System.out.println(hitokotoService.getHitokotoInfo());
    }
}