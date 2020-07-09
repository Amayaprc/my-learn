package com.prc.springbootshiro.controller;

import com.prc.springbootshiro.pojo.entity.User;
import com.prc.springbootshiro.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("test")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping("test")
    public String test() {
        return "test/test";
    }

    @RequestMapping("testQuery")
    @ResponseBody
    public List<User> testQuery(String userid){
        logger.info("userid:" + userid);
        return userService.testQuery(userid);
    }
}
