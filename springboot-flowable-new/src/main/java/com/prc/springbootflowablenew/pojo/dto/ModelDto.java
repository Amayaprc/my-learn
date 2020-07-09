package com.prc.springbootflowablenew.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ModelDto implements Serializable {
    //流程id
    private String processId;

    //流程名称
    private String processName;

    //分类Id
    private String categoryId;

    //流程的xml
    private String xml;
}
