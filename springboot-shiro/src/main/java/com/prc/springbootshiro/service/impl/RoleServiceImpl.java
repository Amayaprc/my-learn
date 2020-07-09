package com.prc.springbootshiro.service.impl;

import com.prc.springbootshiro.dao.RoleMapper;
import com.prc.springbootshiro.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<String> findRole(String userid) {
        return roleMapper.findRole(userid);
    }
}
