package com.prc.springbootflowablenew.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProcessInstanceDto implements Serializable {

    //表单名称
    private String formName;

    //发起人名称
    private String userName;

    //流程实例状态(0未结束,1已结束)
    private Integer status;
}
