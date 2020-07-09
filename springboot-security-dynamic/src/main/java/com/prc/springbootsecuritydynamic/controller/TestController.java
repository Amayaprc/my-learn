package com.prc.springbootsecuritydynamic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "hello!test!";
    }

    @RequestMapping("/dba/test")
    public String dba(){
        return "hello!dba!";
    }

    @RequestMapping("/admin/test")
    public String admin(){
        return "hello!admin!";
    }

    @RequestMapping("/user/test")
    public String user(){
        return "hello!user!";
    }
}
