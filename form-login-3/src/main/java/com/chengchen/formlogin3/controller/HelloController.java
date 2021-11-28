package com.chengchen.formlogin3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "公众号：CoolCity ";
    }

    @RequestMapping("/success")
    public String loginSuccess() {
        return "login success";
    }

    @RequestMapping("/fail")
    public String loginFail() {
        return "login fail";
    }
}
