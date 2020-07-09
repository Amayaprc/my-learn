package com.prc.springbootfreemarker.controller;

import com.prc.springbootfreemarker.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @RequestMapping("/index")
    public String index(Model model){
        User user1 = new User("张三",18,1);
        User user2 = new User("李四",15,2);
        User user3 = new User("王五",30,3);
        User user4 = new User("赵六",25,1);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        model.addAttribute("users",users);
        return "index";
    }
}
