package com.prc.springbootsecuritydb.dao;

import com.prc.springbootsecuritydb.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {

    List<Role> findRoleById(@Param("id") Integer id);
}
