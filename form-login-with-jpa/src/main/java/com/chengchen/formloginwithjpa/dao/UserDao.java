package com.chengchen.formloginwithjpa.dao;

import com.chengchen.formloginwithjpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public interface UserDao extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
