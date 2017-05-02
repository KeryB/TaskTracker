package com.process.tracker.entity.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.process.tracker.Utils.JpaUtils;
import com.process.tracker.entity.task.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = JpaUtils.USER_TABLE_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;

    private String password;

    @Column(name = "name",unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "primaryUser")
    @JsonManagedReference
    private List<Task>  taskList;

}
