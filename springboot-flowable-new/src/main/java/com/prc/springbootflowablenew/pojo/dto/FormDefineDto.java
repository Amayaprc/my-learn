package com.prc.springbootflowablenew.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FormDefineDto implements Serializable {

    //表单定义id
    private Integer formdefId;

    //表单名称
    private String formdefName;

    //表单备注
    private String formdefRemark;

    //创建者id
    private String createId;

    //创建者名称
    private String createName;

    //创建时间
    private String createTime;

    //表单版本
    private Integer version;

    //修改者id
    private String updateId;

    //修改者名称
    private String updateName;

    //修改时间
    private String updateTime;

    //是否删除
    private String deleteFlag;

    //表单组件
    private String formdefItem;
}
