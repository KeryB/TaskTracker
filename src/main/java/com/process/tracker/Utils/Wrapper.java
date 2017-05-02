package com.process.tracker.Utils;


import com.process.tracker.entity.task.Status;
import com.process.tracker.entity.task.Task;
import com.process.tracker.entity.user.User;

import java.util.Iterator;
import java.util.List;

public class Wrapper {

    public static UIResponse wrap(User usver, Task task) {
        return new UIResponse(task,usver);
    }

}
