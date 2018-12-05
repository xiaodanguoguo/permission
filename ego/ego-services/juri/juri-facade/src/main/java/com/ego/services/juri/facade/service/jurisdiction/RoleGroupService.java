package com.ego.services.juri.facade.service.jurisdiction;



import com.ebase.core.page.PageInfo;
import com.ego.services.juri.api.vo.jurisdiction.RoleGroupVO;

import java.util.List;

/**
 * @Auther: zhaoyuhang
 */
public interface RoleGroupService {

    PageInfo<RoleGroupVO> roleGroupList(RoleGroupVO jsonRequest);

    List<RoleGroupVO> findAll(RoleGroupVO jsonRequest);

    RoleGroupVO keepRoleGroup(RoleGroupVO jsonRequest);

    String verificationDeleteRoleGroup(RoleGroupVO jsonRequest);
}
