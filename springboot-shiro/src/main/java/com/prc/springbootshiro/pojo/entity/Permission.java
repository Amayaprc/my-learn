package com.prc.springbootshiro.pojo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Permission implements Serializable {

    private String perid;

    private String pername;

    private String percode;
}
