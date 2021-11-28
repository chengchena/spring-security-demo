package com.chengchen.formlogin4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
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
