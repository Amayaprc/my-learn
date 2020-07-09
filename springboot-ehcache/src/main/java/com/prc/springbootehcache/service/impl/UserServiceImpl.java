package com.prc.springbootehcache.service.impl;

import com.prc.springbootehcache.dao.UserDao;
import com.prc.springbootehcache.pojo.User;
import com.prc.springbootehcache.service.UserService;
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
    @Cacheable(cacheNames = "myCache")
    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    @CacheEvict(cacheNames = "myCache")
    public void deleteUserById(Integer id) {
        userDao.deleteById(id);
    }

    @Override
    @CachePut(cacheNames = "myCache",key = "#user.id")
    public User updateUserById(User user) {
        userDao.saveAndFlush(user);
        return user;
    }
}
