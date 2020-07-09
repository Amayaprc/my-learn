package com.prc.springbootflowablenew.dao;

import com.prc.springbootflowablenew.pojo.dto.ProcessInstanceDto;
import com.prc.springbootflowablenew.pojo.vo.ProcessInstanceVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessInstanceDao {

    /**
     * 流程定义列表
     * @param processInstanceDto 流程实例列表查询参数封装
     * @return
     */
    List<ProcessInstanceVo> getProcessInstanceList(ProcessInstanceDto processInstanceDto);
}
