package com.prc.springbootflowablenew.service;

import com.prc.springbootflowablenew.pojo.dto.ProcessDefineDto;
import com.prc.springbootflowablenew.pojo.vo.ProcessDefineVo;
import com.prc.springbootflowablenew.result.Result;

public interface ProcessDefineService {

    /**
     * 流程定义列表
     * @param pageNum 页数
     * @param pageSize 每页数量
     * @param processDefineDto 流程定义列表查询参数封装
     * @return
     */
    Result defineList(Integer pageNum, Integer pageSize, ProcessDefineDto processDefineDto);

    /**
     * 激活或者挂起流程定义
     * @param processDefineId 流程定义id
     * @param suspensionState 流程定义状态(1激活,2挂起)
     * @return
     */
    Result activeOrSuspendProcessDefinition(String processDefineId, Integer suspensionState);

    /**
     * 通过流程定义id获取流程定义的信息
     * @param processDefineId 流程定义id
     * @return
     */
    ProcessDefineVo getProcessDefineById(String processDefineId);
}
