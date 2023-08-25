package com.gong.scheduled.schedule;

import com.gong.scheduled.service.SchedulingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO
 *
 * @author xiaogong
 * @since 2023/8/25 9:51
 */
@SpringBootTest
class ScheduledTaskTest {

    @Autowired
    private SchedulingService schedulingService;

    @Test
    void getTasks() {
        schedulingService.getTasks();
    }
}