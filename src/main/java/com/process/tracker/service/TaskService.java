package com.process.tracker.service;


import com.process.tracker.entity.task.Status;
import com.process.tracker.entity.task.Task;
import com.process.tracker.entity.user.User;

import java.util.List;

public interface TaskService {
    void deleteTask(long tasks);
    Task saveTask(String description, Status status,User user);
    boolean getTask(long task_id);

    Task findTaskByName(String name);

    boolean findByIdTask(long task_id);
}
