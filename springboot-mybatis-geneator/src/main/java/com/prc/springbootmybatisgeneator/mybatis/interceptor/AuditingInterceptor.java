package com.prc.springbootmybatisgeneator.mybatis.interceptor;

import com.prc.springbootmybatisgeneator.mybatis.annotation.CreatedDate;
import com.prc.springbootmybatisgeneator.mybatis.annotation.LastModifiedDate;
import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class AuditingInterceptor implements Interceptor {
    static final Logger logger = LoggerFactory.getLogger(AuditingInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        if (parameter instanceof ParamMap) {
            // 多参数对象处理
            // 本处仅处理第一个参数 List 对象中的字段  by Jack
            @SuppressWarnings("unchecked")
            ParamMap<Object> paramMap = (ParamMap<Object>) parameter;
            if (paramMap.containsKey("param1")) {
                Object param1 = paramMap.get("param1");
                if (param1 instanceof Iterable) {
                    @SuppressWarnings("unchecked")
                    Iterable<Object> iterable = (Iterable<Object>) param1;
                    iterable.forEach(object -> processFieldsDate(object, sqlCommandType));
                }
            }
        } else {
            processFieldsDate(parameter, sqlCommandType);
        }

        return invocation.proceed();
    }

    private void processFieldsDate(Object object, SqlCommandType sqlCommandType) {
        Field[] fields = object.getClass().getDeclaredFields();
        Date currentDate = new Date();
        try {
            if (SqlCommandType.UPDATE == sqlCommandType) {
                for (Field field : fields) {
                    if (field.getAnnotation(LastModifiedDate.class) != null) {
                        field.setAccessible(true);
                        field.set(object, currentDate);
                        field.setAccessible(false);
                    }
                }
            } else if (SqlCommandType.INSERT == sqlCommandType) {
                for (Field field : fields) {
                    if (Objects.nonNull(field.getAnnotation(CreatedDate.class))) {
                        field.setAccessible(true);
                        field.set(object, currentDate);
                        field.setAccessible(false);
                    }
                    if (Objects.nonNull(field.getAnnotation(LastModifiedDate.class))) {
                        field.setAccessible(true);
                        field.set(object, currentDate);
                        field.setAccessible(false);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("processFieldsDate error: {}", e.getMessage());
        }
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
