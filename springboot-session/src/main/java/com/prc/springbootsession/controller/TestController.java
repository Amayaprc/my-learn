package com.prc.springbootsession.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class TestController {

    @Value("${server.port}")
    Integer port;

    @RequestMapping("/set")
    public String set(HttpSession session){
        session.setAttribute("username","流光");
        return "当前项目端口：" + port + " 当前sessionId :" + session.getId() + "在Session中存入成功！";
    }

    @RequestMapping("/get")
    public String get(HttpSession session){
        return "当前项目端口：" + port + " 当前sessionId :" + session.getId() + "  获取的姓名:" + session.getAttribute("username");
    }
}
