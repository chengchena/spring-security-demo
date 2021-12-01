package com.chengchen.verifycode.dao;

import com.chengchen.verifycode.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
