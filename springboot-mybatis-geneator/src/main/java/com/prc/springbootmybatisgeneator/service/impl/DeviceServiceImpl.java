package com.prc.springbootmybatisgeneator.service.impl;

import com.prc.springbootmybatisgeneator.dao.DeviceMapper;
import com.prc.springbootmybatisgeneator.pojo.dto.Device;
import com.prc.springbootmybatisgeneator.pojo.dto.DeviceCriteria;
import com.prc.springbootmybatisgeneator.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceMapper deviceMapper;

    @Override
    public List<Device> listDeviceByType(String type) {
        DeviceCriteria deviceCriteria = new DeviceCriteria();
        DeviceCriteria.Criteria criteria = deviceCriteria.createCriteria();
        criteria.andDeviceTypeEqualTo(type);
        return deviceMapper.selectByExample(deviceCriteria);
    }

    @Override
    public void insert(Device device) {
        deviceMapper.insertSelective(device);
    }

    @Override
    public Device getDeviceById(Integer deviceid) {
        DeviceCriteria criteria = new DeviceCriteria();
        criteria.createCriteria()
                .andDeviceidEqualTo(deviceid);
        List<Device> devices = deviceMapper.selectByExample(criteria);
        return devices.isEmpty() ? null : devices.get(0);
    }
}
