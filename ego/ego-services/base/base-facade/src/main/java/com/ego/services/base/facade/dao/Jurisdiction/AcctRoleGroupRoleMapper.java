package com.ego.services.base.facade.dao.Jurisdiction;


import com.ego.services.base.facade.model.Jurisdiction.AcctRoleGroupRole;

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