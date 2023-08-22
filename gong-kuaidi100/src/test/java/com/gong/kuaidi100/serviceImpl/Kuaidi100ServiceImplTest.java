package com.gong.kuaidi100.serviceImpl;

import com.gong.kuaidi100.service.Kuaidi100Service;
import com.kuaidi100.sdk.contant.CompanyConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO
 *
 * @author xiaogong
 * @since 2023/8/22 17:04
 */
@SpringBootTest
class Kuaidi100ServiceImplTest {

    @Autowired
    private Kuaidi100Service kuaidi100Service;

    @Test
    void queryTrack() throws Exception {
        String queryTrack = kuaidi100Service.queryTrack(CompanyConstant.JD, "JD0112189109946");
        System.out.println(queryTrack);
    }
}