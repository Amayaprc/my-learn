package com.prc.springbootflowablenew.controller;

import com.prc.springbootflowablenew.enums.CodeEnums;
import com.prc.springbootflowablenew.pojo.dto.FormDefineDto;
import com.prc.springbootflowablenew.result.Result;
import com.prc.springbootflowablenew.service.FormDefineService;
import org.flowable.idm.api.User;
import org.flowable.ui.common.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 但惜流年
 *
 * 表单定义管理管理
 */
@RestController
@RequestMapping("form/define")
public class FormDefineController {

    @Autowired
    private FormDefineService formDefineService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 新增表单定义
     * @param formDefineDto 新增修改表单定义参数封装类
     * @return
     */
    @PostMapping(value = "create")
    public Result createFormDefine(FormDefineDto formDefineDto) {
        Result result = null;
        try {
            result = formDefineService.createFormDefine(formDefineDto);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(CodeEnums.EXCEPTION,"新增表单异常!", e.getMessage());
        }
        return result;
    }
}
