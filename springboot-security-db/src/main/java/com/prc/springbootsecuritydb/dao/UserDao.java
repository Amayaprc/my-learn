package com.prc.springbootsecuritydb.dao;

import com.prc.springbootsecuritydb.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User findUserByName(@Param("username") String username);
}
