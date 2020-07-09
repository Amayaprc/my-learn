package com.prc.springbootshiro.service.impl;

import com.prc.springbootshiro.dao.UserMapper;
import com.prc.springbootshiro.pojo.entity.User;
import com.prc.springbootshiro.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> testQuery(String userid) {
        return userMapper.testQuery(userid);
    }

    @Override
    public User findByUserNam(String userName) {
        return userMapper.findByUserName(userName);
    }


}
