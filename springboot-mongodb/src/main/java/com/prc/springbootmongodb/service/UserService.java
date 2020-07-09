package com.prc.springbootmongodb.service;

import com.prc.springbootmongodb.pojo.User;

import java.util.List;

public interface UserService {

    void insert(User user);

    List<User> findAll();

    List<User> findByName(String name);
}
