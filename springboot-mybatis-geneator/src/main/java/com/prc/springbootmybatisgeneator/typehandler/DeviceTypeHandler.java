package com.prc.springbootmybatisgeneator.typehandler;

import com.google.gson.reflect.TypeToken;
import com.prc.springbootmybatisgeneator.pojo.vo.DeviceVo;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.lang.reflect.Type;
import java.util.List;

@MappedJdbcTypes(JdbcType.VARCHAR)
public class DeviceTypeHandler extends AbstractGsonTypeHandler<List<DeviceVo.Subset>> {

    @Override
    protected Type getType() {
        return new TypeToken<List<DeviceVo.Subset>>() {
        }.getType();
    }
}
