package com.prc.springbootlambda.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    private Integer id;

    private String name;

    private String pinyin;

    private Integer age;

    private String sex;

    private String address;
}
