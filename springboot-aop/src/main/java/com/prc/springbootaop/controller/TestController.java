package com.prc.springbootaop.controller;

import com.prc.springbootaop.annotation.SystemControllerLog;
import com.prc.springbootaop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    UserService userService;

    @RequestMapping("before")
    @SystemControllerLog
    public String before(){
        System.out.println("before-controller");
        return "before";
    }

    @RequestMapping("after")
    public String after(){
        System.out.println("after-controller");
        return userService.after();
    }

    @RequestMapping("afterReturn")
    public String afterReturn(){
        System.out.println("afterReturn-controller");
        return userService.afterReturn();
    }

    @RequestMapping("afterThrowing")
    @SystemControllerLog
    public String AfterThrowing() throws NullPointerException {
        System.out.println("afterThrowing-controller");
        throw new NullPointerException();
    }

    @RequestMapping("around")
    @SystemControllerLog
    public String around(String id) {
        System.out.println("around-controller");
        return "around";
    }
}
