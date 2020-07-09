package com.prc.springbootredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/set")
    public void set(){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("name","流光");
    }

    @RequestMapping("/get")
    public String get(){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        return ops.get("name");
    }
}
