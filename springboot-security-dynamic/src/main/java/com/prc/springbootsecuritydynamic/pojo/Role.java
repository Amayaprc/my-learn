package com.prc.springbootsecuritydynamic.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {

    private Integer id;

    private String name;

    private String nameZh;
}
