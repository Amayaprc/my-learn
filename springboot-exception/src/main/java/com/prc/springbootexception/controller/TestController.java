package com.prc.springbootexception.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;


@RestController
public class TestController {

    @RequestMapping("/test")
    public void test() throws NullPointerException {
        throw new NullPointerException();
    }

    @RequestMapping("/data")
    public void data(Model model) {
        Map<String,Object> map = model.asMap();
        Set<String> keySet = map.keySet();
        for (String key : keySet){
            System.out.println(key + ',' + map.get(key));
        }
    }

    @RequestMapping("/param")
    public void param() {
        Integer i = 1/0;
    }
}
