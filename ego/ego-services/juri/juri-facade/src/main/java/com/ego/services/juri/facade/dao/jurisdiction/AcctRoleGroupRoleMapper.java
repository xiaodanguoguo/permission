package com.ego.services.juri.facade.dao.jurisdiction;


import com.ego.services.juri.facade.model.jurisdiction.AcctRoleGroupRole;
import com.ego.services.juri.facade.model.jurisdiction.AcctRoleGroupRole;

public interface AcctRoleGroupRoleMapper {
    int deleteByPrimaryKey(Long relaId);

    int insert(AcctRoleGroupRole record);

    int insertSelective(AcctRoleGroupRole record);

    AcctRoleGroupRole selectByPrimaryKey(Long relaId);

    int updateByPrimaryKeySelective(AcctRoleGroupRole record);

    int updateByPrimaryKey(AcctRoleGroupRole record);

    int deleteRoleGroupId(Long roleGroupId);

    int deleteRoleId(Long roleId);
}