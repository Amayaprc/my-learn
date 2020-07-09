package com.prc.springbootsecuritydynamic.service.impl;

import com.prc.springbootsecuritydynamic.dao.UserDao;
import com.prc.springbootsecuritydynamic.pojo.User;
import com.prc.springbootsecuritydynamic.service.UserService;
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
