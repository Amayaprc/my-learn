package com.prc.springbootsecuritydb.service.impl;

import com.prc.springbootsecuritydb.dao.RoleDao;
import com.prc.springbootsecuritydb.pojo.Role;
import com.prc.springbootsecuritydb.service.RoleService;
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
