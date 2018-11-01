package com.ego.services.base.facade.service.jurisdiction;



import com.ego.services.base.api.vo.jurisdiction.AcctRoleGroupRoleVO;

/**
 * @Auther: zhaoyuhang
 */
public interface AcctRoleGroupRoleService {

    Integer delAcctRoleGroupRole(AcctRoleGroupRoleVO jsonRequest);

    Integer addAcctRoleGroupRole(AcctRoleGroupRoleVO jsonRequest);
}
