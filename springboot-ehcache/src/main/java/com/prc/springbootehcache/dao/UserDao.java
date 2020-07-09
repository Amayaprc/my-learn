package com.prc.springbootehcache.dao;

import com.prc.springbootehcache.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {

    User findUserById(Integer id);
}
