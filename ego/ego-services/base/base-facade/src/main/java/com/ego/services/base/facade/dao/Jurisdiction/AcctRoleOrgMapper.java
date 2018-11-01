package com.ego.services.base.facade.dao.jurisdiction;

import com.ego.services.base.facade.model.jurisdiction.AcctRoleOrg;

import java.util.List;

public interface AcctRoleOrgMapper {
    int deleteByPrimaryKey(Long relaId);

    int deleteAcctSysOrg(String orgId);

    int deleteAcctOrg(AcctRoleOrg acctRoleOrg);

    int insert(AcctRoleOrg record);

    int insertSelective(AcctRoleOrg record);

    AcctRoleOrg selectByPrimaryKey(Long relaId);

    int updateByPrimaryKeySelective(AcctRoleOrg record);

    int updateByPrimaryKey(AcctRoleOrg record);

    List<AcctRoleOrg> select(AcctRoleOrg record);
}