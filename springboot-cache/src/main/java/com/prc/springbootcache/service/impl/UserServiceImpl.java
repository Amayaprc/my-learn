package com.prc.springbootcache.service.impl;

import com.prc.springbootcache.dao.UserDao;
import com.prc.springbootcache.pojo.User;
import com.prc.springbootcache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    @Cacheable(cacheNames = "test")
    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    @CacheEvict(cacheNames = "test")
    public void deleteUserById(Integer id) {
        userDao.deleteById(id);
    }

    @Override
    @CachePut(cacheNames = "test",key = "#user.id")
    public User updateUserById(User user) {
        userDao.saveAndFlush(user);
        return user;
    }
}
