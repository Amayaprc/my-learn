package com.prc.springbootshiro.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /*@GetMapping("/dog")
    public String dog(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("dog")){
            return "dog√";
        }
        else {
            return  "dog×";
        }
    }

    @GetMapping("/cat")
    public String cat(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("cat")){
            return "cat√";
        }
        else {
            return  "cat×";
        }
    }

    @GetMapping("/people")
    public String people(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("people")){
            return "people√";
        }
        else {
            return  "people×";
        }
    }*/

    @GetMapping("/cat")
    public String cat(){
        return "cat";
    }
    @GetMapping("/dog")
    public String dog(){
        return "dog";
    }
    @GetMapping("/people")
    public String people(){
        return "people";
    }


    @GetMapping("/add")
    public String add(){
        return "add";
    }

    @GetMapping("/delete")
    public String delete(){
        return "delete";
    }

    @GetMapping("/update")
    public String update(){
        return "update";
    }

    @GetMapping("/query")
    public String query(){
        return "query";
    }

    @GetMapping("export")
    public String export(){
        return "export";
    }
}
