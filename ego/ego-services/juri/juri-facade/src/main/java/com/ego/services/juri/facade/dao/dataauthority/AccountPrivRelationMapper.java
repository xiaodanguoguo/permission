package com.ego.services.juri.facade.dao.dataauthority;

import com.ego.services.juri.facade.model.dataauthority.AccountPrivRelation;
import com.ego.services.juri.facade.model.dataauthority.AccountPrivRelation;

import java.util.List;

public interface AccountPrivRelationMapper {
    int deleteByPrimaryKey(Long id);

    int deletePowerId(Long powerExpressionId);

    int insert(AccountPrivRelation record);

    int insertSelective(AccountPrivRelation record);

    AccountPrivRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountPrivRelation record);

    int updateByPrimaryKey(AccountPrivRelation record);

    List<AccountPrivRelation> select(AccountPrivRelation record);
}