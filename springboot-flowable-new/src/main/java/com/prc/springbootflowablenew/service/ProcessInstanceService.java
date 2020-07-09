package com.prc.springbootflowablenew.service;

import com.prc.springbootflowablenew.pojo.dto.ProcessDefineDto;
import com.prc.springbootflowablenew.pojo.dto.ProcessInstanceDto;
import com.prc.springbootflowablenew.result.Result;

public interface ProcessInstanceService {

    /**
     * 流程实例列表
     * @param pageNum 页数
     * @param pageSize 每页数量
     * @param processInstanceDto 流程实例列表查询参数封装
     * @return
     */
    Result instanceList(Integer pageNum, Integer pageSize, ProcessInstanceDto processInstanceDto);
}
