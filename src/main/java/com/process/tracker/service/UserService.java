package com.process.tracker.service;

import com.process.tracker.entity.task.Status;
import com.process.tracker.entity.task.Task;
import com.process.tracker.entity.user.User;

import java.util.List;

public interface UserService {
    User findByUserName(String name);

    User save(String name,String password);

    List<Task> findUserNameByStatus(String name, Status status);
}
