package com.prc.springbootflowablenew.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.prc.springbootflowablenew.dao.ProcessInstanceDao;
import com.prc.springbootflowablenew.enums.CodeEnums;
import com.prc.springbootflowablenew.pojo.dto.ProcessInstanceDto;
import com.prc.springbootflowablenew.pojo.vo.ProcessInstanceVo;
import com.prc.springbootflowablenew.result.PageDataResult;
import com.prc.springbootflowablenew.result.Result;
import com.prc.springbootflowablenew.service.ProcessInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessInstanceServiceImpl implements ProcessInstanceService {

    @Autowired
    private ProcessInstanceDao processInstanceDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Result instanceList(Integer pageNum, Integer pageSize, ProcessInstanceDto processInstanceDto) {
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(pageNum, pageSize);
        List<ProcessInstanceVo> datas = processInstanceDao.getProcessInstanceList(processInstanceDto);
        if(datas.size() != 0){
            PageInfo<ProcessInstanceVo> pageInfo = new PageInfo<>(datas);
            pdr.setList(datas);
            pdr.setTotals((int) pageInfo.getTotal());
        }
        return Result.success(CodeEnums.SUCCESS,"操作成功!", pdr);
    }
}
