package com.ego.services.juri.facade.dao.dataauthority;

import com.ego.services.juri.facade.model.dataauthority.PrivConditionConfig;
import com.ego.services.juri.facade.model.dataauthority.PrivConditionConfig;

import java.util.List;

public interface PrivConditionConfigMapper {
    int deleteByPrimaryKey(Long id);

    int deletePowerId(Long powerExpressionId);

    int insert(PrivConditionConfig record);

    int insertSelective(PrivConditionConfig record);

    PrivConditionConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PrivConditionConfig record);

    int updateByPrimaryKey(PrivConditionConfig record);

    List<PrivConditionConfig> select(PrivConditionConfig record);
}