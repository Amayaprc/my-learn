package com.prc.springbootshiro.service.impl;

import com.prc.springbootshiro.dao.PermissionMapper;
import com.prc.springbootshiro.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<String> findPermission(String userid) {
        return permissionMapper.findPermission(userid);
    }
}
