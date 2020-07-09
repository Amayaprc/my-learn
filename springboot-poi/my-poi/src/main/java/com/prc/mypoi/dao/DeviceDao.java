package com.prc.mypoi.dao;

import com.prc.mypoi.pojo.Device;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceDao {

    Device findOne(@Param("deviceid") String deviceid);
}
