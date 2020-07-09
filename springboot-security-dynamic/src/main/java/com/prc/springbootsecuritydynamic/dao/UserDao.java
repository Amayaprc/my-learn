package com.prc.springbootsecuritydynamic.dao;

import com.prc.springbootsecuritydynamic.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User findUserByName(@Param("username") String username);
}
