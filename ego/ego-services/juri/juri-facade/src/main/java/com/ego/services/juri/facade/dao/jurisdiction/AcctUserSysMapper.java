package com.ego.services.juri.facade.dao.jurisdiction;

import com.ego.services.juri.facade.model.jurisdiction.AcctUserSys;
import com.ego.services.juri.facade.model.jurisdiction.AcctUserSys;

import java.util.List;

public interface AcctUserSysMapper {
    int deleteByPrimaryKey(Long relaId);

    int insert(AcctUserSys record);

    int insertSelective(AcctUserSys record);

    AcctUserSys selectByPrimaryKey(Long relaId);

    int updateByPrimaryKeySelective(AcctUserSys record);

    int updateByPrimaryKey(AcctUserSys record);

    List<AcctUserSys> select(AcctUserSys record);
}