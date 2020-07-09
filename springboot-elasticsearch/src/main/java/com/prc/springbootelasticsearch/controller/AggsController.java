package com.prc.springbootelasticsearch.controller;

import io.searchbox.client.JestClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("aggs")
public class AggsController {

    @Resource
    private JestClient jestClient;


}
