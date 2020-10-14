package com.prc.springbootmybatisgeneator.pojo.vo;

import com.prc.springbootmybatisgeneator.pojo.dto.Device;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DeviceVo {

    private Integer deviceid;

    private String deviceName;

    private String deviceType;

    private Double value;

    private List<Subset> subsets;

    @Setter
    @Getter
    public static class Subset {
        private Integer id;

        private String name;

        private String type;

        private Double value;
    }

    public DeviceVo() {
    }

    public DeviceVo(Device device) {
        this.deviceid = device.getDeviceid();
        this.deviceName = device.getDeviceName();
        this.deviceType = device.getDeviceType();
        this.value = device.getValue();
        this.subsets = device.getSubsets();
    }

    public Device initDevice(DeviceVo deviceVo) {
        Device device = new Device();
        device.setDeviceName(deviceVo.getDeviceName());
        device.setDeviceType(deviceVo.getDeviceType());
        device.setValue(deviceVo.getValue());
        device.setSubsets(deviceVo.getSubsets());
        return device;
    }
}
