package com.prc.springbootshiro.pojo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {

    private String roleid;

    private String rolename;
}
