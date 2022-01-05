package com.chengchen.customauthenticate2.controller;

import com.chengchen.customauthenticate2.config.MyWebAuthenticationDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyWebAuthenticationDetails details = (MyWebAuthenticationDetails) authentication.getDetails();
        System.out.println(details);

        return "公众号：CoolCity ";
    }

    @GetMapping("/admin/access")
    public String adminAccess() {
        return "admin access success.";
    }

    @GetMapping("/user/access")
    public String userAccess() {
        return "user access success.";
    }
}
