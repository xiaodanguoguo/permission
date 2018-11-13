package com.ego.services.base.facade.conf;

import com.ebase.core.page.record.ReflectUtil;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Properties;

/**
 * 功能：Mybatis 拦截器
 * 说明：拦截SQL执行前的语句 根据自定义注解对方法进行公司查询
 *
 * 开发：Bruce.Liu Create by 2018-03-12 20:15
 */

@Intercepts(
        {
                @Signature(
                        type = StatementHandler.class,
                        method = "query",
                        args = {
                                Connection.class ,
                                Integer.class
                        }
                )
        }
)
public class CompanySqlInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try{
            if(invocation.getTarget() instanceof RoutingStatementHandler){
                RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
                //通过反射拿到statement对象
                StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
                BoundSql boundSql = delegate.getBoundSql();

                String sql = boundSql.getSql();
                //再通过反射把新sql设置进去
                ReflectUtil.setFieldValue(boundSql, "sql", sql);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target,this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }

}