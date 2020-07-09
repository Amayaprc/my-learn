package com.prc.mypoi.controller;

import com.prc.mypoi.service.DeviceService;
import com.prc.mypoi.utils.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
public class ExcelController {

    private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);

    @Autowired
    DeviceService deviceService;

    /**
     * 导出Excel
     */
    @GetMapping("/exportExcel")
    public void exportExcel(@RequestParam(value = "ids", defaultValue = "", required = false) String[] ids, HttpServletResponse resp) throws IOException {
        logger.info("---------------导出列表到Excel--------------------");
        resp.setContentType("application/msexcel;charset=UTF-8");
        resp.addHeader("Content-Disposition", "attachment;filename=members.xls");
        OutputStream out = resp.getOutputStream();
        deviceService.exportExcel(ids, out);
    }

    /**
     * 导入Excel
     */
    @PostMapping("/importExcel")
    public void importExcel(@RequestParam("file") MultipartFile file) throws IOException {
        logger.info("---------------导入Excel到列表--------------------");
        if(!file.isEmpty()){
            InputStream in = file.getInputStream();
            String fileName = file.getOriginalFilename();
            deviceService.importExcel(fileName,in);
        }
    }
}
