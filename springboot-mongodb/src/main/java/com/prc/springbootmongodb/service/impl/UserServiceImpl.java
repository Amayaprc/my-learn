package com.prc.springbootmongodb.service.impl;

import com.prc.springbootmongodb.dao.UserDao;
import com.prc.springbootmongodb.pojo.User;
import com.prc.springbootmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> findByName(String name) {
        return userDao.findAllByNameContaining(name);
    }
}
