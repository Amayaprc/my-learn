package com.prc.springbootsecuritydb.service.impl;

import com.prc.springbootsecuritydb.dao.UserDao;
import com.prc.springbootsecuritydb.pojo.User;
import com.prc.springbootsecuritydb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User findUserByName(String username) {
        return userDao.findUserByName(username);
    }
}
