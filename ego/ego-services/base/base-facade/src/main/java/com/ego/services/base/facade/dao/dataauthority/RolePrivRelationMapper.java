package com.ego.services.base.facade.dao.dataauthority;

import com.ego.services.base.facade.model.dataauthority.RolePrivRelation;
import java.util.List;

public interface RolePrivRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RolePrivRelation record);

    int insertSelective(RolePrivRelation record);

    RolePrivRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RolePrivRelation record);

    int updateByPrimaryKey(RolePrivRelation record);

    List<RolePrivRelation> select(RolePrivRelation record);
}