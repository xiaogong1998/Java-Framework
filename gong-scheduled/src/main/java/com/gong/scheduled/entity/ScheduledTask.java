package com.gong.scheduled.entity;

import org.springframework.scheduling.config.Task;

import java.util.concurrent.ScheduledFuture;

public class ScheduledTask {

    private final Task task;

    public volatile ScheduledFuture<?> future;

    public ScheduledTask(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return this.task;
    }

    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(false);
        }
    }

    @Override
    public String toString() {
        return this.task.toString();
    }
}
