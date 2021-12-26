package com.example.manage.Scheduler;

import com.example.manage.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskCron  {

    @Autowired
    private TaskService taskService;

    @Scheduled(cron = "0 * * * * *", zone = "Asia/Tokyo")
    public void scheduleTask(){
        taskService.deleteOnlyScheduledTasks();
    }
}