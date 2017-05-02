package com.process.tracker.repository;


import com.process.tracker.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long>{
    User findByName(String name);
}
