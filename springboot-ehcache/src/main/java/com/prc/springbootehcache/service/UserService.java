package com.prc.springbootehcache.service;

import com.prc.springbootehcache.pojo.User;

public interface UserService {

    User findUserById(Integer id);

    void deleteUserById(Integer id);

    User updateUserById(User user);
}
