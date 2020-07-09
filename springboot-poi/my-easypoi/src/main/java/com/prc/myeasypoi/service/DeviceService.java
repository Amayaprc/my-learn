package com.prc.myeasypoi.service;

import com.prc.myeasypoi.pojo.Device;

import java.util.List;

public interface DeviceService {

    List<Device> findById(String[] ids);

    void insertList(List<Device> list);
}
