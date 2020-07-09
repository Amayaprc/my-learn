package com.prc.springbootsecuritydb.service;

import com.prc.springbootsecuritydb.pojo.User;

public interface UserService {

    User findUserByName(String username);
}
