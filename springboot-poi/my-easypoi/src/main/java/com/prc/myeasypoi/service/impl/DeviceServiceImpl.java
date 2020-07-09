package com.prc.myeasypoi.service.impl;

import com.prc.myeasypoi.dao.DeviceDao;
import com.prc.myeasypoi.pojo.Device;
import com.prc.myeasypoi.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceDao deviceDao;

    @Override
    public List<Device> findById(String[] ids) {
        return deviceDao.findById(ids);
    }

    @Override
    public void insertList(List<Device> list) {
        for (Device device : list) {
            if ("是".equals(device.getIsenable())){
                device.setIsenable("1");
            }else {
                device.setIsenable("0");
            }
            deviceDao.insertDevice(device);;
        }
    }
}
