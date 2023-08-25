package com.gong.scheduled.service;

import com.gong.scheduled.entity.ScheduledTask;
import org.springframework.scheduling.config.FixedDelayTask;
import org.springframework.scheduling.config.FixedRateTask;
import org.springframework.scheduling.config.TriggerTask;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

public interface SchedulingService {

    /**
     * 添加任务
     *
     * @param taskId
     * @param task
     */
    void addTriggerTask(String taskId, TriggerTask task);

    /**
     * 添加任务
     *
     * @param taskId
     * @param task
     */
    void addFixedRateTask(String taskId, FixedRateTask task);

    /**
     * 添加任务
     *
     * @param taskId
     * @param task
     */
    void addFixedDelayTask(String taskId, FixedDelayTask task);

    /**
     * 取消任务
     *
     * @param taskId
     */
    void cancelTask(String taskId);

    /**
     * 重置任务
     *
     * @param taskId
     * @param task
     */
    void resetTask(String taskId, TriggerTask task);

    /**
     * 重置任务
     *
     * @param taskId
     * @param task
     */
    void resetTask(String taskId, FixedRateTask task);

    /**
     * 重置任务
     *
     * @param taskId
     * @param task
     */
    void resetTask(String taskId, FixedDelayTask task);

    /**
     * 是否存在任务
     *
     * @param taskId
     * @return
     */
    boolean hasTask(String taskId);

    /**
     * 获取当前所以任务
     *
     * @return
     */
    Map<String, ScheduledTask> getTasks();
}
