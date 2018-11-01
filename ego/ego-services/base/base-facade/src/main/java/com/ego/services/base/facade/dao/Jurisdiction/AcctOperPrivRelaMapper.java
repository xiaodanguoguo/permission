package com.ego.services.base.facade.dao.jurisdiction;

import com.ego.services.base.facade.model.jurisdiction.AcctOperPrivRela;
import com.ego.services.base.facade.model.jurisdiction.FunctionManage;
import com.ego.services.base.facade.model.jurisdiction.RoleInfo;

public interface AcctOperPrivRelaMapper {
    int deleteByPrimaryKey(Long relaId);

    int deleteRoleId(Long roleId);

    int deleteFunctionId(Long functionId);

    int deleteFunctionIdAll(FunctionManage functionManage);

    int insert(AcctOperPrivRela record);

    int insertSelective(AcctOperPrivRela record);

    AcctOperPrivRela selectByPrimaryKey(Long relaId);

    int updateByPrimaryKeySelective(AcctOperPrivRela record);

    int updateByPrimaryKey(AcctOperPrivRela record);

    int insertCopy(RoleInfo record);
}