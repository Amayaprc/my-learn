package com.prc.springbootsecuritydynamic.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Menu implements Serializable {

    private Integer id;

    private String pattern;

    private List<Role> roles;
}
