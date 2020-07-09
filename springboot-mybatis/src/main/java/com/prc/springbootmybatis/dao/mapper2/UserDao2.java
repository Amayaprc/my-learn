package com.prc.springbootmybatis.dao.mapper2;

import com.prc.springbootmybatis.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao2 {

    Integer addUser(User user);

    Integer updateUserName(@Param("id") String id,@Param("userName") String userName);

    Integer deleteUser(@Param("id") String id);

    List<User> selectUser(@Param("id") String id);

    List<User> selectUserAll2();
}
