package com.prc.springbootfreemarker.pojo;

import lombok.Data;

@Data
public class User {

    private String username;

    private Integer age;

    private Integer sex;

    public User(String username, Integer age, Integer sex) {
        this.username = username;
        this.age = age;
        this.sex = sex;
    }
}
