package com.prc.springbootshiro.service;


import com.prc.springbootshiro.pojo.entity.User;

import java.util.List;

public interface UserService {

    List<User> testQuery(String userid);

    User findByUserNam(String userName);
}
