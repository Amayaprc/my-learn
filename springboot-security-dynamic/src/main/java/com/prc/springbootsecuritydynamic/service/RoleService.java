package com.prc.springbootsecuritydynamic.service;

import com.prc.springbootsecuritydynamic.pojo.Role;

import java.util.List;

public interface RoleService {

    List<Role> findRoleById(Integer id);
}
