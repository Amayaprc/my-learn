package com.prc.springbootmybatisgeneator.dao;

import com.prc.springbootmybatisgeneator.pojo.dto.Device;
import com.prc.springbootmybatisgeneator.pojo.dto.DeviceCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeviceMapper {
    long countByExample(DeviceCriteria example);

    int deleteByExample(DeviceCriteria example);

    int deleteByPrimaryKey(Integer deviceid);

    int insert(Device record);

    int insertSelective(Device record);

    List<Device> selectByExample(DeviceCriteria example);

    Device selectByPrimaryKey(Integer deviceid);

    int updateByExampleSelective(@Param("record") Device record, @Param("example") DeviceCriteria example);

    int updateByExample(@Param("record") Device record, @Param("example") DeviceCriteria example);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);
}