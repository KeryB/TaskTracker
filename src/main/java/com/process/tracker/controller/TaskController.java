package com.process.tracker.controller;

import com.process.tracker.Utils.DataRequest;
import com.process.tracker.Utils.MessageError;
import com.process.tracker.Utils.UIResponse;
import com.process.tracker.Utils.Wrapper;
import com.process.tracker.entity.task.Task;
import com.process.tracker.entity.user.User;
import com.process.tracker.service.TaskService;
import com.process.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @RequestMapping(value = "/getTask",method = RequestMethod.POST)
    public ResponseEntity getTask(@RequestBody DataRequest dataRequest){
        User user = userService.findByUserName(dataRequest.getName());
        if(user==null)
            return ResponseEntity.status(HttpStatus.OK).body(MessageError.USER_NOT_FOUND);
        if(!user.getPassword().equals(dataRequest.getPassword())){
            return ResponseEntity.status(HttpStatus.OK).body(MessageError.ERROR_PASSWORD);
        }
        if(user.getTaskList().isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(MessageError.EMPTY_LIST);
        return ResponseEntity.ok(userService.findUserNameByStatus(
                dataRequest.getName(),
                dataRequest.getStatus()
        ));
    }
    @RequestMapping(value = "/saveTask")
    public ResponseEntity saveTask(@RequestBody DataRequest dataRequest){
        User user = userService.findByUserName(dataRequest.getName());
        if(user==null){
            User usver = userService.save(dataRequest.getName(),dataRequest.getPassword());
            Task task=taskService.saveTask(dataRequest.getDescription(),dataRequest.getStatus(),usver);
            return ResponseEntity.ok(Wrapper.wrap(usver,task));
        }
        if(user.getPassword().equals(dataRequest.getPassword())){
            return ResponseEntity.ok(Wrapper.wrap(user,
                    taskService.saveTask(dataRequest.getDescription(),dataRequest.getStatus(),user)));
        }
        return ResponseEntity.status(HttpStatus.OK).body(MessageError.ERROR_PASSWORD);
    }
    @RequestMapping(value = "/deleteTask")
    public ResponseEntity deleteTask(@RequestBody DataRequest dataRequest){
        User user = userService.findByUserName(dataRequest.getName());
        if(user==null) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageError.USER_NOT_FOUND);
        }
        if(user.getPassword().equals(dataRequest.getPassword())){
            if(taskService.findByIdTask(dataRequest.getTask_id())) {
                taskService.deleteTask(dataRequest.getTask_id());
                return ResponseEntity.ok(true);
            }
            else
                return ResponseEntity.status(HttpStatus.OK).body(MessageError.NOT_ELEMENT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(MessageError.ERROR_ID);
    }
}
