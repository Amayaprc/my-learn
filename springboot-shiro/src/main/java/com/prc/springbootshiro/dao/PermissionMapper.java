package com.prc.springbootshiro.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {

    List<String> findPermission(@Param("userid") String userid);
}
