package com.prc.springbootmybatis.service.impl;

import com.prc.springbootmybatis.dao.mapper1.UserDao1;
import com.prc.springbootmybatis.dao.mapper2.UserDao2;
import com.prc.springbootmybatis.pojo.User;
import com.prc.springbootmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSeviceImpl implements UserService {

    @Autowired
    private UserDao1 userDao1;

    @Autowired
    private UserDao2 userDao2;

    @Override
    public Integer addUser(User user) {
        return userDao2.addUser(user);
    }

    @Override
    public Integer updateUserName(String id, String userName) {
        return userDao2.updateUserName(id,userName);
    }

    @Override
    public Integer deleteUser(String id) {
        return userDao2.deleteUser(id);
    }

    @Override
    public List<User> selectUser(String id) {
        return userDao2.selectUser(id);
    }

    @Override
    public List<User> selectUserAll1() {
        return userDao1.selectUserAll1();
    }

    @Override
    public List<User> selectUserAll2() {
        return userDao2.selectUserAll2();
    }
}
