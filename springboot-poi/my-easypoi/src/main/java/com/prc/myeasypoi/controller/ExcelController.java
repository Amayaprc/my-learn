package com.prc.myeasypoi.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.prc.myeasypoi.pojo.Device;
import com.prc.myeasypoi.service.DeviceService;
import com.prc.myeasypoi.utils.ExcelUtil;
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
import java.util.List;

@RestController
public class ExcelController {

    @Autowired
    DeviceService deviceService;

    private static final Logger log = LoggerFactory.getLogger(ExcelController.class);

    /**
     * 导出Excel
     */
    @GetMapping("/exportExcel")
    public void exportExcel(@RequestParam(value = "ids", defaultValue = "", required = false) String[] ids,HttpServletResponse response) {
        List<Device> personList = deviceService.findById(ids);
        // 导出操作
        ExcelUtil.exportExcel(personList, "easypoi导出功能", "设备数据", Device.class, "设备列表.xls", response);

    }

    /**
     * 导入Excel
     */
    @PostMapping("/importExcel")
    public String importExcel(@RequestParam("file") MultipartFile file) {
        ImportParams importParams = new ImportParams();
        // 数据处理
        importParams.setHeadRows(1);
        importParams.setTitleRows(1);
        // 需要验证
        //importParams.setNeedVerfiy(false);
        try {
            ExcelImportResult<Device> result = ExcelImportUtil.importExcelMore(file.getInputStream(), Device.class, importParams);
            List<Device> deviceList = result.getList();
            deviceService.insertList(deviceList);
            log.info("从Excel导入数据一共 {} 行 ", deviceList.size());
            return "导入成功";
        } catch (IOException e) {
            log.error("导入失败：{}", e.getMessage());
            return "导入失败";
        } catch (Exception e1) {
            log.error("导入失败：{}", e1.getMessage());
            return "导入失败";
        }
    }
}
