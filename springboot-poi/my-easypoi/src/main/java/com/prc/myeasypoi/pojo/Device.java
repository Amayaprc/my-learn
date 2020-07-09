package com.prc.myeasypoi.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

@Data
public class Device implements Serializable {

    @Excel(name = "设备id", width = 15)
    private String deviceId;

    @Excel(name = "设备名称", orderNum = "0", width = 30)
    private String deviceName;

    @Excel(name = "设备类型", orderNum = "0", width = 30)
    private String deviceType;

    @Excel(name = "设备数值", orderNum = "0", width = 30)
    private String value;

    @Excel(name = "创建时间", exportFormat = "yyyy-MM-dd HH:mm:ss", importFormat = "yyyy-MM-dd HH:mm:ss", orderNum = "2", width = 30)
    private String createTime;

    @Excel(name = "是否启用", orderNum = "0", width = 30)
    private String isenable;
}
