package com.prc.springbootcache.controller;

import com.prc.springbootcache.pojo.User;
import com.prc.springbootcache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/findUserById")
    public User findUserById(Integer id){
        return userService.findUserById(id);
    }

    @RequestMapping("/deleteUserById")
    public void deleteUserById(Integer id){
        userService.deleteUserById(id);
    }

    @RequestMapping("/updateUserById")
    public void updateUserById(User user){
        userService.updateUserById(user);
    }
}
