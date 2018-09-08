package com.ego.services.base.facade.dao.Jurisdiction;

import com.ego.services.base.facade.model.Jurisdiction.AcctOperPrivRela;
import com.ego.services.base.facade.model.Jurisdiction.FunctionManage;

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
}