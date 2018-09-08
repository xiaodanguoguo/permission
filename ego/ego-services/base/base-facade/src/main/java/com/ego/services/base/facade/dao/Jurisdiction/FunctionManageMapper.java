package com.ego.services.base.facade.dao.Jurisdiction;

import com.ego.services.base.facade.model.Jurisdiction.AcctInfo;
import com.ego.services.base.facade.model.Jurisdiction.FunctionManage;

import java.util.List;

public interface FunctionManageMapper {
    int deleteByPrimaryKey(Long functionId);

    int insert(FunctionManage record);

    int insertSelective(FunctionManage record);

    FunctionManage selectByPrimaryKey(Long functionId);

    int updateByPrimaryKeySelective(FunctionManage record);

    int updateByPrimaryKey(FunctionManage record);

    List<FunctionManage> findRole(FunctionManage functionManage);

    List<FunctionManage> findRoleThree(FunctionManage functionManage);

    List<FunctionManage> find(FunctionManage functionManage);

    List<FunctionManage> findThree(FunctionManage functionManage);

    List<FunctionManage> findPath(FunctionManage functionManage);

    List<FunctionManage> verificationDeleteFunction(FunctionManage functionManage);

    FunctionManage findParentApplicationId(FunctionManage functionManage);

    int updateFunctionIdAll(FunctionManage functionManage);

    List<FunctionManage> verificationFunIsTtitle(FunctionManage functionManage);

	List<FunctionManage> selsctUserFunctionAll(AcctInfo acctInfo);

}