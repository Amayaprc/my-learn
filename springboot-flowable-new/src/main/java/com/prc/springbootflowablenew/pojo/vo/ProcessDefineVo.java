package com.prc.springbootflowablenew.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProcessDefineVo implements Serializable {

    //主键id
    private String id;

    //流程定义key
    private String modelKey;

    //流程定义名称
    private String name;

    //流程定义版本
    private Integer version;

    //流程定义的Namespace
    private String category;

    //流程部署id
    private String deploymentId;

    //流程bpmn文件名称
    private String resourceName;

    //流程图片名称
    private String dgrmResourceName;

    //流程是否挂起
    private Integer suspensionState;

    //租户id
    private String tenantId;
}
