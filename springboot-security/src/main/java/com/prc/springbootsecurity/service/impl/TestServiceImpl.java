package com.prc.springbootsecurity.service.impl;

import com.prc.springbootsecurity.service.TestService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {


    @Override
    @PreAuthorize("hasRole('admin')")
    public String admin() {
        return "test,admin";
    }

    @Override
    @Secured("ROLE_user")
    public String user() {
        return "test,user";
    }

    @Override
    @PreAuthorize("hasAnyRole('admin','user')")
    public String all() {
        return "test,all";
    }
}
