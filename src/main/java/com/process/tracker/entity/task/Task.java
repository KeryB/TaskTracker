package com.process.tracker.entity.task;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.process.tracker.Utils.JpaUtils;
import com.process.tracker.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = JpaUtils.TASK_TABLE_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long task_id;
    @Column(name = "name",unique = true)
    private String name;
    private String description;
    private Status status;

    @ManyToOne
    @JoinColumn(name = JpaUtils.USER_PRIMARY_KEY)
    @JsonBackReference
    private User primaryUser;

}
