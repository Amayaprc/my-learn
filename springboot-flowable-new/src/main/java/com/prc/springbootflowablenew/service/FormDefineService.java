package com.prc.springbootflowablenew.service;

import com.prc.springbootflowablenew.pojo.dto.FormDefineDto;
import com.prc.springbootflowablenew.result.Result;

public interface FormDefineService {

    /**
     * 新增表单定义
     * @param formDefineDto 新增修改表单定义参数封装类
     * @return
     */
    Result createFormDefine(FormDefineDto formDefineDto) throws Exception;
}
