package com.prc.mypoi.service.impl;

import com.prc.mypoi.controller.ExcelController;
import com.prc.mypoi.dao.DeviceDao;
import com.prc.mypoi.pojo.Device;
import com.prc.mypoi.service.DeviceService;
import com.prc.mypoi.utils.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    private static final Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

    @Autowired
    DeviceDao deviceDao;

    @Override
    public void exportExcel(String[] ids, OutputStream out) {
        String keyValue="设备id:deviceId,设备名称:deviceName,设备类型:deviceType,设备数据:value,创建时间:createTime,是否启用:isenable";
        List<Device> list = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            Device device = deviceDao.findOne(ids[i]);
            list.add(device);
        }
        try {
            ExcelUtil.exportExcel("设备数据导出", out, ExcelUtil.getMap(keyValue), list, "com.prc.mypoi.pojo.Device", null, null, null);
            logger.info("导出成功!");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("导出失败!");
        }
    }

    @Override
    public void importExcel(String fileName, InputStream in) {
        logger.info("导入"+fileName+"开始");
        String keyValue="设备id:deviceId,设备名称:deviceName,设备类型:deviceType,设备数据:value,创建时间:createTime,是否启用:isenable";
        List<Device> list = null;
        try {
            //readExcel参数列表
            //fileName Excel文件名
            //inputStream 输入流
            //map 表头和属性的Map集合,其中Map中Key为Excel列的名称，Value为反射类的属性
            //classPath 需要映射的model的路径
            //rowNumIndex 表头所在行数
            list = ExcelUtil.readExcel(fileName,in,ExcelUtil.getMap(keyValue),
                    "com.prc.mypoi.pojo.Device",1);
            logger.info(" 导入成功!");
        } catch (Exception e) {
            logger.error("导入失败!");
        }
        for (Device device : list) {
            logger.info("deviceName:"+device.getDeviceName());
            logger.info("deviceType:"+device.getDeviceType());
        }
        logger.info(" 导入结束!");
    }
}
