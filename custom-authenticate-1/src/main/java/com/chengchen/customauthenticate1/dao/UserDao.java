package com.chengchen.customauthenticate1.dao;

import com.chengchen.customauthenticate1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
