package com.ego.services.base.facade.dao.jurisdiction;

import com.ego.services.base.facade.model.jurisdiction.AcctOrgSys;

import java.util.List;

public interface AcctOrgSysMapper {
    int deleteByPrimaryKey(Long relaId);

    int deleteAcctSysOrg(String orgId);

    int deleteAcctOrg(Long sysId);

    int insert(AcctOrgSys record);

    int insertSelective(AcctOrgSys record);

    AcctOrgSys selectByPrimaryKey(Long relaId);

    int updateByPrimaryKeySelective(AcctOrgSys record);

    int updateByPrimaryKey(AcctOrgSys record);

    List<AcctOrgSys> select(AcctOrgSys record);
}