package com.prc.springbootsecuritydynamic.service;

import com.prc.springbootsecuritydynamic.pojo.User;

public interface UserService {

    User findUserByName(String username);
}
