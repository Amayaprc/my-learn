package com.prc.springbootcache.dao;

import com.prc.springbootcache.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {

    User findUserById(Integer id);
}
