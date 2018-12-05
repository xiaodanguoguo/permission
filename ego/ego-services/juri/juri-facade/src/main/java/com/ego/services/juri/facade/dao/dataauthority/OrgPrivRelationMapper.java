package com.ego.services.juri.facade.dao.dataauthority;

import com.ego.services.juri.facade.model.dataauthority.OrgPrivRelation;
import com.ego.services.juri.facade.model.dataauthority.OrgPrivRelation;

import java.util.List;

public interface OrgPrivRelationMapper {
    int deleteByPrimaryKey(Long id);

    int deletePowerId(Long powerExpressionId);

    int insert(OrgPrivRelation record);

    int insertSelective(OrgPrivRelation record);

    OrgPrivRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrgPrivRelation record);

    int updateByPrimaryKey(OrgPrivRelation record);

    List<OrgPrivRelation> select(OrgPrivRelation record);
}