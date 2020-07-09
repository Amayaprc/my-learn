package com.prc.springbootelasticsearch.controller;

import com.prc.springbootelasticsearch.utils.FileUtils;
import io.searchbox.action.Action;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.ElasticsearchVersion;
import io.searchbox.indices.mapping.PutMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("mappings")
public class MappingsController {

    @Resource
    private JestClient jestClient;

    @PostMapping("cteateMappings")
    public String createIndex(@RequestParam(value = "jsonFile") MultipartFile jsonFile,
                              @RequestParam(value = "indexName") String indexName,
                              @RequestParam(value = "type") String type) throws Exception {
        File file = FileUtils.multipartFileToFile(jsonFile);
        String mappings = org.apache.commons.io.FileUtils.readFileToString(file);
        PutMapping putMapping = new PutMapping.Builder(indexName, type, mappings).build();
        JestResult result = jestClient.execute(putMapping);
        if (result.isSucceeded()) {
            return "索引" + indexName + "下的mappings创建成功!";
        }
        return "索引" + indexName + "下的mappings创建失败!";
    }

    @GetMapping("getMappings")
    public String getMappings(@RequestParam(value = "indexName") String indexName) throws IOException {
        Action getMapping = new io.searchbox.indices.mapping.GetMapping.Builder().addIndex(indexName).build();
        JestResult result = jestClient.execute(getMapping);
        if (result.isSucceeded()) {
            return "索引" + indexName + "下的mappings规则为:" + result.getJsonString();
        }
        return "未查询到索引" + indexName + "下的mappings规则!";
    }
}
