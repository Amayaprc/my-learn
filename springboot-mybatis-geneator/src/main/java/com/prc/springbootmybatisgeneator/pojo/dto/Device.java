package com.prc.springbootmybatisgeneator.pojo.dto;

import com.prc.springbootmybatisgeneator.mybatis.annotation.CreatedDate;
import com.prc.springbootmybatisgeneator.pojo.vo.DeviceVo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class Device implements Serializable {
    private Integer deviceid;

    private String deviceName;

    private String deviceType;

    private Double value;

    @CreatedDate
    private Date createTime;

    private Boolean isenable;

    private List<DeviceVo.Subset> subsets;

    public Device() {
    }

    public Device(Integer deviceid, String deviceName, String deviceType, Double value, Date createTime, Boolean isenable, List<DeviceVo.Subset> subsets) {
        this.deviceid = deviceid;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.value = value;
        this.createTime = createTime;
        this.isenable = isenable;
        this.subsets = subsets;
    }

    private static final long serialVersionUID = 1L;
}