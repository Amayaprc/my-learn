package com.prc.springbootflowablenew.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProcessDefineDto implements Serializable {

    //流程定义名称
    private String name;

    //流程定义key
    private String key;

}
