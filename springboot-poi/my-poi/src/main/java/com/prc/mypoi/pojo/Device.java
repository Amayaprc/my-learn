package com.prc.mypoi.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class Device implements Serializable {

    private String deviceId;

    private String deviceName;

    private String deviceType;

    private String value;

    private String createTime;

    private String isenable;
}
