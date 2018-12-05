package com.ego.services.juri.facade.service.jurisdiction;



import com.ego.services.juri.api.vo.jurisdiction.AcctRoleGroupRoleVO;

/**
 * @Auther: zhaoyuhang
 */
public interface AcctRoleGroupRoleService {

    Integer delAcctRoleGroupRole(AcctRoleGroupRoleVO jsonRequest);

    Integer addAcctRoleGroupRole(AcctRoleGroupRoleVO jsonRequest);
}
