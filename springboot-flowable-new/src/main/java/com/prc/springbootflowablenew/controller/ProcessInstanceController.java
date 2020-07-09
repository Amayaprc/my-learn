package com.prc.springbootflowablenew.controller;

import com.prc.springbootflowablenew.pojo.dto.ProcessInstanceDto;
import com.prc.springbootflowablenew.result.Result;
import com.prc.springbootflowablenew.service.ProcessInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 但惜流年
 *
 * flowable流程实例管理接口
 */
@RestController
@RequestMapping("process/instance")
public class ProcessInstanceController {

    @Autowired
    private ProcessInstanceService processInstanceService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 流程实例列表查询
     * @param pageNum 页数
     * @param pageSize 每页数量
     * @param processInstanceDto 流程实例列表查询参数封装
     */
    @GetMapping(value = "list")
    public Result instanceList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                             ProcessInstanceDto processInstanceDto) {
        return processInstanceService.instanceList(pageNum,pageSize,processInstanceDto);
    }
}
