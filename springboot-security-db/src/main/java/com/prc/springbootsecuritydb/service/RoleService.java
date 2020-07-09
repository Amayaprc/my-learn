package com.prc.springbootsecuritydb.service;

import com.prc.springbootsecuritydb.pojo.Role;

import java.util.List;

public interface RoleService {

    List<Role> findRoleById(Integer id);
}
