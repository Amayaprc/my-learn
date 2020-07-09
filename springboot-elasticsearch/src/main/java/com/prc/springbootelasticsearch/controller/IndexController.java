package com.prc.springbootelasticsearch.controller;

import com.prc.springbootelasticsearch.utils.FileUtils;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("index")
public class IndexController {

    @Resource
    private JestClient jestClient;

    @PostMapping("createIndex")
    public String createIndex(@RequestParam(value = "indexName") String indexName) throws Exception {
        CreateIndex createIndex = new CreateIndex.Builder(indexName).build();
        JestResult result = jestClient.execute(createIndex);
        if (result.isSucceeded()) {
            return "索引添加成功!";
        }
        return "索引添加失败!";
    }

    @DeleteMapping("deleteIndex")
    public String deleteIndex(@RequestParam(value = "indexName") String indexName) throws IOException {
        DeleteIndex deleteIndex = new DeleteIndex.Builder(indexName).build();
        JestResult result = jestClient.execute(deleteIndex);
        if (result.isSucceeded()) {
            return "索引删除成功!";
        }
        return "索引删除失败!";
    }
}
