package com.tarzan.common.mybatis.plugins;


import com.tarzan.common.mybatis.annotation.Desensitization;
import com.tarzan.common.mybatis.enums.DesensitizationStrategy;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * 脱敏插件：
 *
 *
 *  myabtis自定义插件只需要实现Interceptor接口即可，
 *  并且注解@Intercepts以及@Signature配置需要拦截的对象，
 *  其中type是需要拦截的对象Class，method是对象里面的方法，args是方法参数类型。
 *
 * @author 58491
 * @description: 拦截所有数据库SQL执行时长超过1秒的方法、并记录SQL
 * @date 2021/6/8
 */
@Component
@Intercepts(@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = Statement.class))
public class DesensitizationPlugin implements Interceptor {
    /**
     * 执行阈值
     */
    private long time;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取数据库返回结果
        List<Object> records = (List<Object>) invocation.proceed();
        // 对数据进行脱敏
        records.forEach(this::desensitization);
        return records;
    }

    private void desensitization(Object source) {
        Class<?> sourceClass = source.getClass();
        MetaObject metaObject = SystemMetaObject.forObject(source);
        // 获取类中所有的属性
        Stream.of(sourceClass.getDeclaredFields())
                .filter(item -> item.isAnnotationPresent(Desensitization.class))
                .forEach(field -> doDesensitization(metaObject, field));
    }

    /**
     *  真正处理脱敏方法
     */
    private void doDesensitization(MetaObject metaObject, Field field) {
        // 获取属性名
        String name = field.getName();
        // 获取属性值
        Object value = metaObject.getValue(name);
        // 处理spring类型，并且不为空的
        if (String.class == metaObject.getGetterType(name) && Objects.nonNull(value)) {
            // 获取属性上面脱敏注解
            Desensitization desensitization = field.getAnnotation(Desensitization.class);
            // 获取设置的策略
            DesensitizationStrategy strategy = desensitization.strategy();
            // 进行脱敏处理
            Object o = strategy.getDesensitizer().apply((String) value);
            // 将值重新设置回去
            metaObject.setValue(name, o);
        }
    }


    /**
     *  获取拦截的对象，底层也是通过代理实现的，实际上是拿到一个目标代理对象
     */
    @Override
    public Object plugin(Object target) {
        return null;
    }

    /**
     * 获取设置的阈值等参数
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        this.time = Long.parseLong(properties.getProperty("time"));
    }
}
