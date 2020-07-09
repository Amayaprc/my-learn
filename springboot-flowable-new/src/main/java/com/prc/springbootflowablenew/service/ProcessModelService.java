package com.prc.springbootflowablenew.service;

import com.prc.springbootflowablenew.pojo.dto.ModelDto;
import com.prc.springbootflowablenew.result.Result;
import org.springframework.web.multipart.MultipartFile;

public interface ProcessModelService {

    /**
     * 流程模型列表
     * @param pageNum 页数
     * @param pageSize 每页数量
     * @return
     */
    Result modelList(Integer pageNum, Integer pageSize);

    /**
     * 添加流程模型
     * @param model 添加流程模型参数封装
     * @return
     */
    Result addModel(ModelDto model);

    /**
     * 导入流程模型
     * @param file 文件
     * @return
     */
    Result importModel(MultipartFile file);

    /**
     * 部署流程模型
     * @param modelId 流程模型id
     * @return
     */
    Result deploy(String modelId);
}
