package com.prc.springbootlambda.controller;

import com.google.gson.JsonObject;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Cat;
import io.searchbox.core.Search;
import io.searchbox.core.SearchScroll;
import io.searchbox.indices.mapping.GetMapping;
import net.hzbox.elasticsearch.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestController {

    @Autowired
    private JestClient jestClient;

    @RequestMapping("test01")
    public String test01() throws IOException {
        Cat cat = new Cat.IndicesBuilder().build();
        JestResult result = jestClient.execute(cat);
        return result.toString();
    }

    @RequestMapping("test02")
    public String test02() throws IOException {
        GetMapping.Builder builder = new GetMapping.Builder();
        builder.addIndex("form-pro").addType("_doc").build();
        JestResult result = jestClient.execute(builder.build());
        return result.getSourceAsObject(JsonObject.class).toString();
    }

    @RequestMapping("test03")
    public String test03() throws IOException {
        String json = null;
        Search search = new Search.Builder(json).addIndex("form-pro").addType("_doc").build();
        JestResult result = jestClient.execute(search);
        return result.toString();
    }
}
