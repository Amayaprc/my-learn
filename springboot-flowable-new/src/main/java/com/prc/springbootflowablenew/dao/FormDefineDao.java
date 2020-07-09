package com.prc.springbootflowablenew.dao;

import com.prc.springbootflowablenew.pojo.dto.FormDefineDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FormDefineDao {

    /**
     * 新增表单定义
     * @param formDefineDto 新增修改表单定义参数封装类
     * @return
     */
    Integer createFormDefine(FormDefineDto formDefineDto);

    /**
     * 新增表单控件
     * @param formdefId 表单定义id
     * @param formItem 表单控件json
     * @return
     */
    Integer createFormItem(@Param("formdefId") Integer formdefId,@Param("formdefItem") String formItem);
}
