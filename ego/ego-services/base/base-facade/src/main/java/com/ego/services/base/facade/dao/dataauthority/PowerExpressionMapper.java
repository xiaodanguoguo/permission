package com.ego.services.base.facade.dao.dataauthority;

import com.ego.services.base.facade.model.dataauthority.PowerExpression;
import java.util.List;

public interface PowerExpressionMapper {
    int deleteByPrimaryKey(Long powerExpressionId);

    int insert(PowerExpression record);

    int insertSelective(PowerExpression record);

    PowerExpression selectByPrimaryKey(Long powerExpressionId);

    PowerExpression selectqueryPower(Long powerExpressionId);

    PowerExpression selectAcctConfig(Long acctId);

    int updateByPrimaryKeySelective(PowerExpression record);

    int updateByPrimaryKeyWithBLOBs(PowerExpression record);

    int updateByPrimaryKey(PowerExpression record);

    List<PowerExpression> select(PowerExpression record);

    Integer selectCount(PowerExpression record);
}