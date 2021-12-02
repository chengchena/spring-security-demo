package com.chengchen.loginfromjson.dao;

import com.chengchen.loginfromjson.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
