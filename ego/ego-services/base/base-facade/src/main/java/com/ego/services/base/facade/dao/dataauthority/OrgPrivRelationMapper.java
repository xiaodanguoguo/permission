package com.ego.services.base.facade.dao.dataauthority;

import com.ego.services.base.facade.model.dataauthority.OrgPrivRelation;
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