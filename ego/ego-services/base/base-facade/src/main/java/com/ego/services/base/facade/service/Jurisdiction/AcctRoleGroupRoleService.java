package com.ego.services.base.facade.service.Jurisdiction;



import com.ego.services.base.api.vo.Jurisdiction.AcctRoleGroupRoleVO;

/**
 * @Auther: zhaoyuhang
 */
public interface AcctRoleGroupRoleService {

    Integer delAcctRoleGroupRole(AcctRoleGroupRoleVO jsonRequest);

    Integer addAcctRoleGroupRole(AcctRoleGroupRoleVO jsonRequest);
}
