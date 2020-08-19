package com.prc.springbootmybatisgeneator.controller;

import com.prc.springbootmybatisgeneator.pojo.dto.Device;
import com.prc.springbootmybatisgeneator.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("list")
    public List<Device> listDeviceByType(String type) {
        return deviceService.listDeviceByType(type);
    }
}
