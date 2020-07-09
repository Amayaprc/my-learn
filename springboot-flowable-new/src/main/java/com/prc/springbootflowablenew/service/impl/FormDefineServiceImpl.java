package com.prc.springbootflowablenew.service.impl;

import com.prc.springbootflowablenew.dao.FormDefineDao;
import com.prc.springbootflowablenew.enums.CodeEnums;
import com.prc.springbootflowablenew.pojo.dto.FormDefineDto;
import com.prc.springbootflowablenew.result.Result;
import com.prc.springbootflowablenew.service.FormDefineService;
import org.flowable.idm.api.User;
import org.flowable.ui.common.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FormDefineServiceImpl implements FormDefineService {

    @Autowired
    private FormDefineDao formDefineDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result createFormDefine(FormDefineDto formDefineDto) throws Exception {
        User user = SecurityUtils.getCurrentUserObject();
        formDefineDto.setCreateId(user.getId());
        formDefineDto.setCreateName(user.getFirstName());
        formDefineDto.setUpdateId(user.getId());
        formDefineDto.setUpdateName(user.getFirstName());
        Integer res1 = formDefineDao.createFormDefine(formDefineDto);
        Integer res2 = formDefineDao.createFormItem(formDefineDto.getFormdefId(),formDefineDto.getFormdefItem());
        Integer res = res1 * res2;
        if (res == 0){
            throw new Exception();
        }
        return Result.success(CodeEnums.SUCCESS,"表单新增成功!");
    }
}
