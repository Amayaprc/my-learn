package com.prc.springbootsecurityjwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello!";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin!";
    }
}
