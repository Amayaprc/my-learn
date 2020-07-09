package com.prc.springbootshiro.pojo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private String userid;

    private String username;

    private String password;

    private String sex;

    private String address;
}
