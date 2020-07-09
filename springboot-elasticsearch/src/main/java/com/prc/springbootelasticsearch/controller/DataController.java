package com.prc.springbootelasticsearch.controller;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.client.JestResultHandler;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("data")
public class DataController {

    @Resource
    private JestClient jestClient;

    @PostMapping("insertOrUpdateData")
    public String insertOrUpdateDataByAsyn(String indexName, String typeName, List<Object> dataList, String id, JestResultHandler<JestResult> jestResultHandler) {
        Bulk.Builder bulk = new Bulk.Builder();
        for (Object data : dataList) {
            Index index = new Index.Builder(data).
                    index(indexName).
                    type(typeName).
                    id(id).
                    build();
            bulk.addAction(index);
        }
        jestClient.executeAsync(bulk.build(), jestResultHandler);
        return "数据写入" + indexName + "索引成功!";
    }
}
