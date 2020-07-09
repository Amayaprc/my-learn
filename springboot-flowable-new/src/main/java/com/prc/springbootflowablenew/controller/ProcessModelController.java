package com.prc.springbootflowablenew.controller;

import com.prc.springbootflowablenew.pojo.dto.ModelDto;
import com.prc.springbootflowablenew.result.Result;
import com.prc.springbootflowablenew.service.FlowProcessDiagramGenerator;
import com.prc.springbootflowablenew.service.ProcessModelService;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.ui.modeler.domain.Model;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @author 但惜流年
 *
 * flowable流程模型管理接口
 */
@RestController
@RequestMapping("process/model")
public class ProcessModelController {

    @Autowired
    private ProcessModelService processModelService;

    @Autowired
    private FlowProcessDiagramGenerator flowProcessDiagramGenerator;

    @Resource
    private ModelService modelService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 流程模型列表查询
     * @param pageNum 页数
     * @param pageSize 每页数量
     */
    @GetMapping(value = "list")
    public Result modelList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return processModelService.modelList(pageNum,pageSize);
    }

    /**
     * 添加流程模型
     * @param model 添加流程模型参数封装
     * @return
     */
    @PostMapping(value = "add")
    public Result addModel(@RequestBody ModelDto model) {
        return processModelService.addModel(model);
    }

    /**
     * 导入流程模型
     * @param file 文件
     * @return
     */
    @PostMapping(value = "import")
    public Result importModel(@RequestParam("file") MultipartFile file) {
        return processModelService.importModel(file);
    }

    /**
     * 部署流程模型
     * @param modelId 流程模型id
     * @return
     */
    @PostMapping(value = "deploy")
    public Result deploy(String modelId) {
        return processModelService.deploy(modelId);
    }

    /**
     * 加载流程模型xml
     * @param modelId 流程模型id
     * @return
     */
    @GetMapping(value = "/loadXmlByModelId/{modelId}")
    public void loadXmlByModelId(@PathVariable String modelId, HttpServletResponse response) {
        try {
            Model model = modelService.getModel(modelId);
            byte[] b = modelService.getBpmnXML(model);
            response.setHeader("Content-type", "text/xml;charset=UTF-8");
            response.getOutputStream().write(b);
        } catch (Exception e) {
            logger.error("流程模板XML导出异常:" + e.getMessage());
        }
    }

    /**
     * 加载流程模型图片
     * @param modelId 流程模型id
     * @param response
     */
    @GetMapping(value = "/loadPngByModelId/{modelId}")
    public void loadPngByModelId(@PathVariable String modelId, HttpServletResponse response) {
        Model model = modelService.getModel(modelId);
        BpmnModel bpmnModel = modelService.getBpmnModel(model, new HashMap<>(), new HashMap<>());
        InputStream is = flowProcessDiagramGenerator.generateDiagram(bpmnModel);
        try {
            response.setHeader("Content-Type", "image/png");
            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
        } catch (Exception e) {
            logger.error("流程模板图片导出异常:" + e.getMessage());
        }
    }
}
