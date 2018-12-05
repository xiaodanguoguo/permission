package com.ego.services.juri.facade.dao.jurisdiction;

import com.ego.services.juri.facade.model.jurisdiction.AcctFunctionSys;
import com.ego.services.juri.facade.model.jurisdiction.AcctFunctionSys;

import java.util.List;

public interface AcctFunctionSysMapper {
    int deleteByPrimaryKey(Long relaId);

    int insert(AcctFunctionSys record);

    int insertSelective(AcctFunctionSys record);

    AcctFunctionSys selectByPrimaryKey(Long relaId);

    int updateByPrimaryKeySelective(AcctFunctionSys record);

    int updateByPrimaryKey(AcctFunctionSys record);

    List<AcctFunctionSys> select(AcctFunctionSys record);
}