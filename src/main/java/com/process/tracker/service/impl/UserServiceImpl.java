package com.process.tracker.service.impl;

import com.process.tracker.Utils.JpaUtils;
import com.process.tracker.entity.task.Status;
import com.process.tracker.entity.task.Task;
import com.process.tracker.entity.user.User;
import com.process.tracker.repository.UserRepository;
import com.process.tracker.service.UserService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUserName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User save(String name,String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        return userRepository.save(user);
    }

    @Override
    public List<Task> findUserNameByStatus(String name,Status status) {
        User user = userRepository.findByName(name);
        List<Task> taskList = user.getTaskList();
        List<Task> statusList = new LinkedList<>();
        taskList.forEach(task ->{
                    if((task.getStatus()== status)){
                        statusList.add(task);
                    }
                }
        );
        return statusList;
    }

}
