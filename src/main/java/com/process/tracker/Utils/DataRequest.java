package com.process.tracker.Utils;

import com.process.tracker.entity.task.Status;
import lombok.Data;

@Data
public class DataRequest {

    private String name;
    private String password;
    private String description;
    private Status status;
    private long task_id;
}
