package com.prc.springbootflowablenew.dao;

import com.prc.springbootflowablenew.pojo.dto.ProcessDefineDto;
import com.prc.springbootflowablenew.pojo.vo.ProcessDefineVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessDefineDao {

    /**
     * 流程定义列表
     * @param processDefineDto 流程定义列表查询参数封装
     * @return
     */
    List<ProcessDefineVo> getProcessDefineList(ProcessDefineDto processDefineDto);

    /**
     * 通过流程定义id获取流程定义的信息
     * @param processDefineId 流程定义id
     * @return
     */
    ProcessDefineVo getProcessDefineById(@Param("processDefineId") String processDefineId);
}
