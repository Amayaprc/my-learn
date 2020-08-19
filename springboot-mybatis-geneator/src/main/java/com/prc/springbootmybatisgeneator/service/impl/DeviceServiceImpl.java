package com.prc.springbootmybatisgeneator.service.impl;

import com.prc.springbootmybatisgeneator.dao.DeviceMapper;
import com.prc.springbootmybatisgeneator.pojo.dto.Device;
import com.prc.springbootmybatisgeneator.pojo.dto.DeviceCriteria;
import com.prc.springbootmybatisgeneator.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public List<Device> listDeviceByType(String type) {
        DeviceCriteria deviceCriteria = new DeviceCriteria();
        DeviceCriteria.Criteria criteria = deviceCriteria.createCriteria();
        criteria.andDeviceTypeEqualTo(type);
        return deviceMapper.selectByExample(deviceCriteria);
    }
}
