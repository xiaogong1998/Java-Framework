package com.gong.scheduled.schedule;

import com.gong.scheduled.service.SchedulingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.FixedRateTask;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ScheduledTask {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SchedulingService schedulingService;


    @Scheduled(cron = "0 */10 * * * ?")
    @PostConstruct
    public void push() {
        String str = "自动任务已启动";
        // schedulingService.resetTask("微信订阅号推送1", new FixedRateTask(() -> subscriptionService.sendText(buildData()), 30 * 1000, 10000));
        // schedulingService.resetTask("微信订阅号推送2", new FixedDelayTask(() -> subscriptionService.sendText(buildData()), 30 * 1000, 10000));
        // schedulingService.resetTask("微信订阅号推送3", new CronTask(() -> subscriptionService.sendText(buildData()), scheduledProperties.getCore()));
        schedulingService.resetTask("test", new FixedRateTask(() -> System.out.printf("%s:%d\n", str, System.currentTimeMillis()), 1000, 1000));
    }
}
