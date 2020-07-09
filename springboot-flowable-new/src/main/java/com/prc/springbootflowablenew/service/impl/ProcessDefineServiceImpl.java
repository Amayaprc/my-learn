package com.prc.springbootflowablenew.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.prc.springbootflowablenew.dao.ProcessDefineDao;
import com.prc.springbootflowablenew.enums.CodeEnums;
import com.prc.springbootflowablenew.pojo.dto.ProcessDefineDto;
import com.prc.springbootflowablenew.pojo.vo.ProcessDefineVo;
import com.prc.springbootflowablenew.result.PageDataResult;
import com.prc.springbootflowablenew.result.Result;
import com.prc.springbootflowablenew.service.ProcessDefineService;
import org.flowable.engine.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessDefineServiceImpl implements ProcessDefineService {

    @Autowired
    private ProcessDefineDao processDefineDao;

    @Autowired
    private RepositoryService repositoryService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Result defineList(Integer pageNum, Integer pageSize, ProcessDefineDto processDefineDto) {
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(pageNum, pageSize);
        List<ProcessDefineVo> datas = processDefineDao.getProcessDefineList(processDefineDto);
        if(datas.size() != 0){
            PageInfo<ProcessDefineVo> pageInfo = new PageInfo<>(datas);
            pdr.setList(datas);
            pdr.setTotals((int) pageInfo.getTotal());
        }
        return Result.success(CodeEnums.SUCCESS,"操作成功!", pdr);
    }

    @Override
    public Result activeOrSuspendProcessDefinition(String processDefineId, Integer suspensionState) {
        String msg = null;
        if (1 == suspensionState){
            repositoryService.suspendProcessDefinitionById(processDefineId, true, null);
            msg = "挂起成功";
        }else {
            repositoryService.activateProcessDefinitionById(processDefineId, true, null);
            msg = "激活成功";
        }
        return Result.success(CodeEnums.SUCCESS,msg);
    }

    @Override
    public ProcessDefineVo getProcessDefineById(String processDefineId) {
        return processDefineDao.getProcessDefineById(processDefineId);
    }
}
