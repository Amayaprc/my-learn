package com.prc.springbootmongodb.controller;

import com.prc.springbootmongodb.pojo.User;
import com.prc.springbootmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping("/insert")
    public void insert(User user){
        userService.insert(user);
    }

    @RequestMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @RequestMapping("/findByName")
    public List<User> findByName(String name){
        return userService.findByName(name);
    }

    @RequestMapping("/findById")
    public User findById(Integer id){
        return mongoTemplate.findById(id,User.class);
    }
}
