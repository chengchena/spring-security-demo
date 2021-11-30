package com.chengchen.formloginwithjpa;

import com.chengchen.formloginwithjpa.dao.UserDao;
import com.chengchen.formloginwithjpa.model.Role;
import com.chengchen.formloginwithjpa.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FormLoginWithJpaApplicationTests {
    @Autowired
    UserDao userDao;

    @Test
    void contextLoads() {
        User u1 = new User();
        u1.setAccountNonExpired(true);
        u1.setAccountNonLocked(true);
        u1.setCredentialsNonExpired(true);
        u1.setEnabled(true);
        u1.setUsername("admin");
        u1.setPassword("123456789");
        Role r1 = new Role();
        r1.setRoleName("ROLE_admin");
        r1.setRoleNameZh("管理员");
        List<Role> roles1 = new ArrayList<>();
        roles1.add(r1);
        u1.setRoles(roles1);
        userDao.save(u1);

        User u2 = new User();
        u2.setAccountNonExpired(true);
        u2.setAccountNonLocked(true);
        u2.setCredentialsNonExpired(true);
        u2.setEnabled(true);
        u2.setUsername("chencheng");
        u2.setPassword("123456789");
        Role r2 = new Role();
        r2.setRoleName("ROLE_user");
        r2.setRoleNameZh("普通用户");
        List<Role> roles2 = new ArrayList<>();
        roles2.add(r2);
        u2.setRoles(roles2);
        userDao.save(u2);
    }

}
