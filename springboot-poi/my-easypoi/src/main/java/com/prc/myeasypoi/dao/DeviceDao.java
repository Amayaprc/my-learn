package com.prc.myeasypoi.dao;

import com.prc.myeasypoi.pojo.Device;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceDao {

    List<Device> findById(@Param("ids") String[] ids);

    Integer insertDevice(Device device);
}
