package com.prc.myeasyexcel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ExcelController {

    /**
     * 导出Excel
     */
    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) {
    }

    /**
     * 导入Excel
     */
    @PostMapping("/importExcel")
    public String importExcel(@RequestParam("file") MultipartFile file) {
        return null;
    }
}
