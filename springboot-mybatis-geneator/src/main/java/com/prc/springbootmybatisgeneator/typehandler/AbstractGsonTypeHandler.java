package com.prc.springbootmybatisgeneator.typehandler;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public abstract class AbstractGsonTypeHandler<T> extends BaseTypeHandler<T> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            String jsonStr = getGson().toJson(parameter, getType());
            ps.setString(i, jsonStr);
        } else {
            ps.setString(i, null);
        }
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String jsonStr = rs.getString(columnName);
        return fromString(jsonStr);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String jsonStr = rs.getString(columnIndex);
        return fromString(jsonStr);
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String jsonStr = cs.getString(columnIndex);
        return fromString(jsonStr);
    }

    private T fromString(String source) {
        try {
            return getGson().fromJson(source, getType());
        } catch (Exception e) {
            log.error("gson_from_json error, source:{}, type:{}", source, this.getType(), e);
            return null;
        }
    }

    private Gson getGson() {
        return new Gson();
    }

    protected abstract Type getType();
}
