package com.prc.springbootshiro.dao;

import com.prc.springbootshiro.pojo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<User> testQuery(@Param("userid") String userid);

    User findByUserName(@Param("userName") String userName);
}
