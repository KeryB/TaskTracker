package com.process.tracker.Utils;


import com.process.tracker.entity.task.Task;
import com.process.tracker.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UIResponse {
    private  Task task;
    private  User user;
}
