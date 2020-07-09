package com.prc.springbootsecurity.controller;

import com.prc.springbootsecurity.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/admin")
    public String admin(){
        return testService.admin();
    }

    @RequestMapping("/user")
    public String user(){
        return testService.user();
    }

    @RequestMapping("/all")
    public String all(){
        return testService.all();
    }
}
