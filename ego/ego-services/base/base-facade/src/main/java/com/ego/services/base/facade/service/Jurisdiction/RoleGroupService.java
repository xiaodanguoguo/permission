package com.ego.services.base.facade.service.Jurisdiction;



import com.ebase.core.page.PageDTO;
import com.ego.services.base.api.vo.Jurisdiction.RoleGroupVO;

import java.util.List;

/**
 * @Auther: zhaoyuhang
 */
public interface RoleGroupService {

    PageDTO<RoleGroupVO> roleGroupList(RoleGroupVO jsonRequest);

    List<RoleGroupVO> findAll(RoleGroupVO jsonRequest);

    RoleGroupVO keepRoleGroup(RoleGroupVO jsonRequest);

    String verificationDeleteRoleGroup(RoleGroupVO jsonRequest);
}
