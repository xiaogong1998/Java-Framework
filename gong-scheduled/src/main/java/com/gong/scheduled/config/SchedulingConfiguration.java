package com.gong.scheduled.config;

import com.gong.scheduled.service.SchedulingService;
import com.gong.scheduled.serviceImpl.SchedulingServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
@EnableAsync
public class SchedulingConfiguration {

	/**
	 * 定时任务使用的线程池
	 * 
	 * @return
	 */
	@Bean(destroyMethod = "shutdown", name = "taskScheduler")
	@ConditionalOnMissingBean(ThreadPoolTaskScheduler.class)
	public ThreadPoolTaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(10);
		scheduler.setThreadNamePrefix("task-");
		scheduler.setAwaitTerminationSeconds(600);
		scheduler.setWaitForTasksToCompleteOnShutdown(true);
		return scheduler;
	}

	@Bean
	@ConditionalOnMissingBean(SchedulingService.class)
	public SchedulingService scheduledTaskService() {
		return new SchedulingServiceImpl(taskScheduler());
	}

}
