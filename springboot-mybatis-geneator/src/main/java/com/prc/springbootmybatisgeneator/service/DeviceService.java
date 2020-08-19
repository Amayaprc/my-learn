package com.prc.springbootmybatisgeneator.service;

import com.prc.springbootmybatisgeneator.pojo.dto.Device;

import java.util.List;

public interface DeviceService {

    List<Device> listDeviceByType(String type);
}
