package com.prc.springbootmybatisgeneator.pojo.dto;

import java.io.Serializable;
import java.util.Date;

public class Device implements Serializable {
    private Integer deviceid;

    private String deviceName;

    private String deviceType;

    private Double value;

    private Date createTime;

    private Boolean isenable;

    private static final long serialVersionUID = 1L;

    public Device(Integer deviceid, String deviceName, String deviceType, Double value, Date createTime, Boolean isenable) {
        this.deviceid = deviceid;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.value = value;
        this.createTime = createTime;
        this.isenable = isenable;
    }

    public Device() {
        super();
    }

    public Integer getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Integer deviceid) {
        this.deviceid = deviceid;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getIsenable() {
        return isenable;
    }

    public void setIsenable(Boolean isenable) {
        this.isenable = isenable;
    }
}