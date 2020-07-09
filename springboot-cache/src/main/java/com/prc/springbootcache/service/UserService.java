package com.prc.springbootcache.service;

import com.prc.springbootcache.pojo.User;

public interface UserService {

    User findUserById(Integer id);

    void deleteUserById(Integer id);

    User updateUserById(User user);
}
