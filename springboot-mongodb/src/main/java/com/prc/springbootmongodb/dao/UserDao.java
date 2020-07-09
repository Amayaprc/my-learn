package com.prc.springbootmongodb.dao;

import com.prc.springbootmongodb.pojo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserDao extends MongoRepository<User,Integer> {

    List<User> findAllByNameContaining(String name);
}
