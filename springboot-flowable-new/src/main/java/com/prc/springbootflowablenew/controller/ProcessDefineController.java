package com.prc.springbootflowablenew.controller;

import com.prc.springbootflowablenew.enums.CodeEnums;
import com.prc.springbootflowablenew.pojo.dto.ProcessDefineDto;
import com.prc.springbootflowablenew.pojo.vo.ProcessDefineVo;
import com.prc.springbootflowablenew.result.Result;
import com.prc.springbootflowablenew.service.ProcessDefineService;
import org.flowable.common.engine.impl.util.IoUtil;
import org.flowable.engine.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author 但惜流年
 *
 * flowable流程定义管理接口
 */
@RestController
@RequestMapping("process/define")
public class ProcessDefineController {

    @Autowired
    private ProcessDefineService processDefineService;

    @Autowired
    private RepositoryService repositoryService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 流程定义列表查询
     * @param pageNum 页数
     * @param pageSize 每页数量
     * @param processDefineDto 流程定义列表查询参数封装
     */
    @GetMapping(value = "list")
    public Result defineList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                             ProcessDefineDto processDefineDto) {
        return processDefineService.defineList(pageNum,pageSize,processDefineDto);
    }

    /**
     * 流程定义删除
     * @param deploymentId 部署id
     */
    @DeleteMapping(value = "delete")
    public Result deleteDefineAndDeployment(@RequestParam(value = "deploymentId", required = false) String deploymentId) {
        try {
            repositoryService.deleteDeployment(deploymentId, true);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(CodeEnums.EXCEPTION,"流程定义及部署删除异常!", e.getMessage());
        }
        return Result.success(CodeEnums.SUCCESS,"流程定义及部署删除成功!");
    }

    /**
     * 激活或者挂起流程定义
     * @param processDefineId 流程定义id
     * @param suspensionState 流程定义状态(1激活,2挂起)
     * @return
     */
    @PutMapping(value = "activeOrSuspend")
    public Result activeOrHangup(@RequestParam(value = "processDefineId", required = false) String processDefineId,
                                 @RequestParam(value = "suspensionState", required = false) Integer suspensionState) {
        return processDefineService.activeOrSuspendProcessDefinition(processDefineId,suspensionState);
    }

    /**
     * 通过流程定义id和所需类型加载流程xml或图片
     * @param processDefineId 流程定义id
     * @param type 加载类型(xml,img)
     * @param response response
     */
    @GetMapping(value = "/processFile/{type}/{processDefineId}")
    public void processFile(@PathVariable String processDefineId, @PathVariable String type, HttpServletResponse response) {
        try {
            byte[] b = null;
            ProcessDefineVo processDefine = processDefineService.getProcessDefineById(processDefineId);
            if (!Objects.isNull(processDefine)) {
                if ("xml".equals(type)) {
                    //加载流程xml
                    response.setHeader("Content-type", "text/xml;charset=UTF-8");
                    InputStream inputStream = repositoryService.getResourceAsStream(processDefine.getDeploymentId(), processDefine.getResourceName());
                    b = IoUtil.readInputStream(inputStream, "image inputStream name");
                } else {
                    //加载流程图片
                    response.setHeader("Content-Type", "image/png");
                    InputStream inputStream = repositoryService.getResourceAsStream(processDefine.getDeploymentId(), processDefine.getDgrmResourceName());
                    b = IoUtil.readInputStream(inputStream, "image inputStream name");
                }
                response.getOutputStream().write(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
