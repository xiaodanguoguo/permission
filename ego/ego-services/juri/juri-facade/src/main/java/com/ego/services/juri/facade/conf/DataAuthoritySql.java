package com.ego.services.juri.facade.conf;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.Configuration;

import java.util.List;

public class DataAuthoritySql extends BoundSql {

    public DataAuthoritySql(Configuration configuration, String sql, List<ParameterMapping> parameterMappings, Object parameterObject) {
        super(configuration, sql, parameterMappings, parameterObject);
    }
}
