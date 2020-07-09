package com.prc.springbootsecuritydynamic.service.impl;

import com.prc.springbootsecuritydynamic.dao.RoleDao;
import com.prc.springbootsecuritydynamic.pojo.Role;
import com.prc.springbootsecuritydynamic.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public List<Role> findRoleById(Integer id) {
        return roleDao.findRoleById(id);
    }
}
