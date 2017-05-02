package com.process.tracker.repository;


import com.process.tracker.entity.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long>{
    Task findByName(String name);

    @Query("select t from Task t where t.task_id =:task_id")
    List<Task> findById(@Param("task_id") long task_id);
}
