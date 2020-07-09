package com.prc.springbootaop.service.impl;

import com.prc.springbootaop.annotation.SystemServiceLog;
import com.prc.springbootaop.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    @SystemServiceLog
    public String after() {
        System.out.println("after-service");
        return "after";
    }

    @Override
    @SystemServiceLog
    public String afterReturn() {
        System.out.println("afterReturn-service");
        return "afterReturn";
    }
}
