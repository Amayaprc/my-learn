package com.prc.springbootmybatisgeneator.controller;

import com.prc.springbootmybatisgeneator.pojo.dto.Device;
import com.prc.springbootmybatisgeneator.pojo.vo.DeviceVo;
import com.prc.springbootmybatisgeneator.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("device")
@AllArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping("list")
    public List<DeviceVo> listDeviceByType(String type) {
        List<Device> devices = deviceService.listDeviceByType(type);
        List<DeviceVo> deviceVos = new ArrayList<>();
        devices.forEach(device -> deviceVos.add(new DeviceVo(device)));
        return deviceVos;
    }

    @GetMapping("{deviceid}")
    public DeviceVo getDeviceById(@PathVariable("deviceid") Integer deviceid) {
        Device device = deviceService.getDeviceById(deviceid);
        return new DeviceVo(device);
    }

    @PostMapping
    public DeviceVo insert(@RequestBody DeviceVo deviceVo) {
        Device device = deviceVo.initDevice(deviceVo);
        deviceService.insert(device);
        return new DeviceVo(device);
    }
}
