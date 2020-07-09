package com.prc.springbootmybatis.controller;

import com.prc.springbootmybatis.pojo.User;
import com.prc.springbootmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("add")
    public Integer addUser(User user){
        return userService.addUser(user);
    }

    @RequestMapping("update")
    public Integer updateUserName(String id,String userName){
        return userService.updateUserName(id,userName);
    }

    @RequestMapping("delete")
    public Integer deleteUser(String id){
        return userService.deleteUser(id);
    }

    @RequestMapping("selectOne")
    public List<User> selectUser(String id){
        return userService.selectUser(id);
    }

    @RequestMapping("selectAll1")
    public List<User> selectUserAll1(){
        return userService.selectUserAll1();
    }

    @RequestMapping("selectAll2")
    public List<User> selectUserAll2(){
        return userService.selectUserAll2();
    }
}
