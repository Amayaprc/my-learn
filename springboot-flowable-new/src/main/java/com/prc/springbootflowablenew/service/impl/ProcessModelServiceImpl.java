package com.prc.springbootflowablenew.service.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.prc.springbootflowablenew.enums.CodeEnums;
import com.prc.springbootflowablenew.pojo.dto.ModelDto;
import com.prc.springbootflowablenew.result.PageDataResult;
import com.prc.springbootflowablenew.result.Result;
import com.prc.springbootflowablenew.service.ProcessModelService;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.BpmnAutoLayout;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.editor.language.json.converter.BpmnJsonConverter;
import org.flowable.editor.language.json.converter.util.CollectionUtils;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.idm.api.User;
import org.flowable.ui.common.security.SecurityUtils;
import org.flowable.ui.common.util.XmlUtil;
import org.flowable.ui.modeler.domain.AbstractModel;
import org.flowable.ui.modeler.domain.Model;
import org.flowable.ui.modeler.repository.ModelRepository;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.flowable.validation.ProcessValidator;
import org.flowable.validation.ProcessValidatorFactory;
import org.flowable.validation.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Service
public class ProcessModelServiceImpl implements ProcessModelService {

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RepositoryService repositoryService;

    @Resource
    private ModelService modelService;

    @Resource
    private ModelRepository modelRepository;

    protected BpmnXMLConverter bpmnXmlConverter = new BpmnXMLConverter();
    protected BpmnJsonConverter bpmnJsonConverter = new BpmnJsonConverter();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public Result modelList(Integer pageNum, Integer pageSize) {
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(pageNum, pageSize);
        List<AbstractModel> datas = modelService.getModelsByModelType(AbstractModel.MODEL_TYPE_BPMN);
        if(datas.size() != 0){
            PageInfo<AbstractModel> pageInfo = new PageInfo<>(datas);
            pdr.setList(datas);
            pdr.setTotals((int) pageInfo.getTotal());
            datas.forEach(abstractModel -> {
                User user = identityService.createUserQuery().userId(abstractModel.getCreatedBy()).singleResult();
                abstractModel.setCreatedBy(user.getFirstName());
            });
        }
        return Result.success(CodeEnums.SUCCESS,"操作成功!", pdr);
    }

    @Override
    public Result addModel(ModelDto model) {
        InputStream inputStream = new ByteArrayInputStream(model.getXml().getBytes());
        return this.createModel(inputStream);
    }

    @Override
    public Result importModel(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName != null && (fileName.endsWith(".bpmn") || fileName.endsWith(".bpmn20.xml"))) {
            try {
                InputStream inputStream = file.getInputStream();
                return this.createModel(inputStream);
            } catch (IOException e) {
                return Result.fail(CodeEnums.EXCEPTION,"抛异常了!",e.getMessage());
            }
        } else {
            return Result.fail(CodeEnums.FAIL,"Invalid file name, only .bpmn and .bpmn20.xml files are supported not " + fileName);
        }
    }

    @Override
    public Result deploy(String modelId) {
        Result result = new Result();
        if (StringUtils.isBlank(modelId)) {
            return Result.fail(CodeEnums.FAIL,"流程模板id不能为空!");
        }
        try {
            Model model = modelService.getModel(modelId.trim());
            //到时候需要添加分类
            String categoryCode = "1000";
            BpmnModel bpmnModel = modelService.getBpmnModel(model);
            //添加隔离信息
            String tenantId = "flow";
            //必须指定文件后缀名否则部署不成功
            Deployment deploy = repositoryService.createDeployment()
                    .name(model.getName())
                    .key(model.getKey())
                    .category(categoryCode)
                    .tenantId(tenantId)
                    .addBpmnModel(model.getKey() + ".bpmn", bpmnModel)
                    .deploy();
            result.setCode(CodeEnums.SUCCESS.getCode());
            result.setMsg("部署流程成功！");
            result.setData(deploy.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(CodeEnums.EXCEPTION,"流程模板部署异常!", e.getMessage());
        }
        return result;
    }

    private Result createModel(InputStream inputStream) {
        try {
            XMLInputFactory xif = XmlUtil.createSafeXmlInputFactory();
            InputStreamReader xmlIn = new InputStreamReader(inputStream, "UTF-8");
            XMLStreamReader xtr = xif.createXMLStreamReader(xmlIn);
            BpmnModel bpmnModel = bpmnXmlConverter.convertToBpmnModel(xtr);
            //模板验证
            ProcessValidator validator = new ProcessValidatorFactory().createDefaultProcessValidator();
            List<ValidationError> errors = validator.validate(bpmnModel);
            if (CollectionUtils.isNotEmpty(errors)) {
                StringBuffer es = new StringBuffer();
                errors.forEach(ve -> es.append(ve.toString()).append("/n"));
                return Result.fail(CodeEnums.FAIL,"操作失败!",es.toString());
            }
            String fileName = bpmnModel.getMainProcess().getName();
            if (CollectionUtils.isEmpty(bpmnModel.getProcesses())) {
                return Result.fail(CodeEnums.FAIL,"操作失败!","No process found in definition " + fileName);
            }
            if (bpmnModel.getLocationMap().size() == 0) {
                BpmnAutoLayout bpmnLayout = new BpmnAutoLayout(bpmnModel);
                bpmnLayout.execute();
            }
            ObjectNode modelNode = bpmnJsonConverter.convertToJson(bpmnModel);
            org.flowable.bpmn.model.Process process = bpmnModel.getMainProcess();
            String name = process.getId();
            if (StringUtils.isNotEmpty(process.getName())) {
                name = process.getName();
            }
            String description = process.getDocumentation();
            User createdBy = SecurityUtils.getCurrentUserObject();
            //查询是否已经存在流程模板
            Model newModel = new Model();
            List<Model> models = modelRepository.findByKeyAndType(process.getId(), AbstractModel.MODEL_TYPE_BPMN);
            if (CollectionUtils.isNotEmpty(models)) {
                Model updateModel = models.get(0);
                newModel.setId(updateModel.getId());
            }
            newModel.setName(name);
            newModel.setKey(process.getId());
            newModel.setModelType(AbstractModel.MODEL_TYPE_BPMN);
            newModel.setCreated(Calendar.getInstance().getTime());
            if (!Objects.isNull(createdBy)){
                newModel.setCreatedBy(createdBy.getId());
            }
            newModel.setDescription(description);
            newModel.setModelEditorJson(modelNode.toString());
            newModel.setLastUpdated(Calendar.getInstance().getTime());
            newModel.setLastUpdatedBy(createdBy.getId());
            modelService.createModel(newModel, SecurityUtils.getCurrentUserObject());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(CodeEnums.EXCEPTION,"抛异常了!",e.getMessage());
        }
        return Result.success(CodeEnums.SUCCESS,"流程模板添加成功!");
    }
}
