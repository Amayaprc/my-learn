package com.prc.springbootmybatis.dao.mapper1;

import com.prc.springbootmybatis.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao1 {

    List<User> selectUserAll1();
}
