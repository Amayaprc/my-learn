package com.prc.springbootshiro.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    List<String> findRole(@Param("userid") String userid);
}
