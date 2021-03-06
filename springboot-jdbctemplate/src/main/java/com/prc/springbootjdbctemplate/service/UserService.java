package com.prc.springbootjdbctemplate.service;

import com.prc.springbootjdbctemplate.pojo.User;

import java.util.List;

public interface UserService {

    Integer addUser(User user);

    Integer updateUserName(String id,String userName);

    Integer deleteUser(String id);

    List<User> selectUser(String id);

    List<User> selectUserAll1();

    List<User> selectUserAll2();
}
