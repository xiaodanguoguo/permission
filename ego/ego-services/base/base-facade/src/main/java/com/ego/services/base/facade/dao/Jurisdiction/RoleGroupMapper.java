package com.ego.services.base.facade.dao.Jurisdiction;


import com.ego.services.base.facade.model.Jurisdiction.RoleGroup;

import java.util.List;

public interface RoleGroupMapper {
    int deleteByPrimaryKey(Long roleGroupId);

    int insert(RoleGroup record);

    int insertSelective(RoleGroup record);

    RoleGroup selectByPrimaryKey(Long roleGroupId);

    int updateByPrimaryKeySelective(RoleGroup record);

    int updateIsStatus(RoleGroup record);

    int updateByPrimaryKey(RoleGroup record);

    List<RoleGroup> find(RoleGroup record);

    List<RoleGroup> verificationDeleteRoleGroup(RoleGroup record);

    List<RoleGroup> findAll(RoleGroup record);

    List<RoleGroup> roleGroupTree(RoleGroup record);

    List<RoleGroup> findRoleGroupTitle(RoleGroup record);

    List<RoleGroup> findARoleGroupTree(RoleGroup record);

    List<RoleGroup> findRoleInfoId(RoleGroup record);

    List<RoleGroup> findAcctId(RoleGroup record);
}