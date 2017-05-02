package com.process.tracker.service.impl;

import com.process.tracker.Utils.JpaUtils;
import com.process.tracker.entity.task.Status;
import com.process.tracker.entity.task.Task;
import com.process.tracker.entity.user.User;
import com.process.tracker.repository.TaskRepository;
import com.process.tracker.service.TaskService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void deleteTask(long tasks) {
        taskRepository.delete(tasks);
    }

    @Override
    public Task saveTask(String description,Status status,User user) {
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(status);
        task.setPrimaryUser(user);
        task.setName(RandomStringUtils.random(5, JpaUtils.CHARACTERS));
        return taskRepository.save(task);
    }

    @Override
    public boolean getTask(long task_id) {
        return !taskRepository.findById(task_id).isEmpty();
    }

    @Override
    public Task findTaskByName(String name) {
        return taskRepository.findByName(name);
    }

    @Override
    public boolean findByIdTask(long task_id) {
        return taskRepository.findOne(task_id) != null && !taskRepository.findById(task_id).isEmpty();
    }

}
