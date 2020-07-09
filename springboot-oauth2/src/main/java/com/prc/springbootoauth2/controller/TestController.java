package com.prc.springbootoauth2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello!";
    }

    @RequestMapping("/admin/hello")
    public String admin(){
        return "hello!admin!";
    }

    @RequestMapping("/user/hello")
    public String user(){
        return "hello!user!";
    }
}
