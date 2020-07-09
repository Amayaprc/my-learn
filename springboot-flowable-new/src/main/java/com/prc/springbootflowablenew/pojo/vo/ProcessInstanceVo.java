package com.prc.springbootflowablenew.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProcessInstanceVo implements Serializable {

    //流程实例id
    private String processInstanceId;

    //流程定义id
    private String processDefineId;

    //状态 1激活 2挂起
    //private Integer suspensionState;

    //表单名称
    private String formName;

    //表单主键
    private String businessKey;

    //开始时间
    private String startTime;

    //结束时间
    private String endTime;

    //下个节点审批人
    //private String approver;

    //发起人
    private String starter;

    //发起人id
    private String starterId;

    //租户id
    private String tenantId;
}
