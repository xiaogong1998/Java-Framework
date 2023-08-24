package com.gong.scheduled.serviceImpl;

import com.gong.scheduled.entity.ScheduledTask;
import com.gong.scheduled.service.SchedulingService;
import org.springframework.scheduling.SchedulingException;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.FixedDelayTask;
import org.springframework.scheduling.config.FixedRateTask;
import org.springframework.scheduling.config.TriggerTask;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SchedulingServiceImpl implements SchedulingService {

    private TaskScheduler taskScheduler;

    private final Map<String, ScheduledTask> tasks = new ConcurrentHashMap<>();

    public SchedulingServiceImpl(TaskScheduler taskScheduler) {
        super();
        this.taskScheduler = taskScheduler;
    }

    /**
     * 添加任务
     *
     * @param taskId
     * @param task
     */
    public void addTriggerTask(String taskId, TriggerTask task) {
        if (this.tasks.containsKey(taskId)) {
            throw new SchedulingException("the taskId[" + taskId + "] was added.");
        }

        ScheduledTask scheduledTask = new ScheduledTask(task);

        scheduledTask.future = taskScheduler.schedule(task.getRunnable(), task.getTrigger());

        this.tasks.put(taskId, scheduledTask);
    }

    /**
     * 添加任务
     *
     * @param taskId
     * @param task
     */
    public void addFixedRateTask(String taskId, FixedRateTask task) {
        if (this.tasks.containsKey(taskId)) {
            throw new SchedulingException("the taskId[" + taskId + "] was added.");
        }

        ScheduledTask scheduledTask = new ScheduledTask(task);

        if (task.getInitialDelay() > 0) {
            Date startTime = new Date(System.currentTimeMillis() + task.getInitialDelay());
            scheduledTask.future = this.taskScheduler.scheduleAtFixedRate(task.getRunnable(), startTime, task.getInterval());
        } else {
            scheduledTask.future = this.taskScheduler.scheduleAtFixedRate(task.getRunnable(), task.getInterval());
        }
        this.tasks.put(taskId, scheduledTask);
    }

    /**
     * 添加任务
     *
     * @param taskId
     * @param task
     */
    public void addFixedDelayTask(String taskId, FixedDelayTask task) {
        if (this.tasks.containsKey(taskId)) {
            throw new SchedulingException("the taskId[" + taskId + "] was added.");
        }

        ScheduledTask scheduledTask = new ScheduledTask(task);

        if (task.getInitialDelay() > 0) {
            Date startTime = new Date(System.currentTimeMillis() + task.getInitialDelay());
            scheduledTask.future = this.taskScheduler.scheduleWithFixedDelay(task.getRunnable(), startTime, task.getInterval());
        } else {
            scheduledTask.future = this.taskScheduler.scheduleWithFixedDelay(task.getRunnable(), task.getInterval());
        }
        this.tasks.put(taskId, scheduledTask);
    }

    /**
     * 取消任务
     *
     * @param taskId
     */
    public void cancelTask(String taskId) {
        ScheduledTask scheduledTask = this.tasks.get(taskId);
        if (scheduledTask != null) {
            scheduledTask.cancel();
        }
        this.tasks.remove(taskId);
    }

    /**
     * 重置任务
     *
     * @param taskId
     * @param task
     */
    public void resetTask(String taskId, TriggerTask task) {
        cancelTask(taskId);
        addTriggerTask(taskId, task);
    }

    /**
     * 重置任务
     *
     * @param taskId
     * @param task
     */
    public void resetTask(String taskId, FixedRateTask task) {
        cancelTask(taskId);
        addFixedRateTask(taskId, task);
    }

    /**
     * 重置任务
     *
     * @param taskId
     * @param task
     */
    public void resetTask(String taskId, FixedDelayTask task) {
        cancelTask(taskId);
        addFixedDelayTask(taskId, task);
    }

    /**
     * 是否存在任务
     *
     * @param taskId
     * @return
     */
    public boolean hasTask(String taskId) {
        return this.tasks.containsKey(taskId);
    }
}
