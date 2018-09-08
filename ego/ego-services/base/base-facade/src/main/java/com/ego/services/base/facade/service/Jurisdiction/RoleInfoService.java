package com.ego.services.base.facade.service.Jurisdiction;



import com.ebase.core.page.PageDTO;
import com.ego.services.base.api.vo.Jurisdiction.RoleInfoVO;

import java.util.List;

/**
 * @Auther: zhaoyuhang
 */
public interface RoleInfoService {

    PageDTO<RoleInfoVO> roleInfoList(RoleInfoVO jsonRequest);

    List<RoleInfoVO> roleInfoAll(RoleInfoVO jsonRequest);

    List<RoleInfoVO> roleInfoAllLike(RoleInfoVO jsonRequest);

    List<RoleInfoVO> roleRoleAcctInfo(RoleInfoVO jsonRequest);

    List<RoleInfoVO> roleInfoTree(RoleInfoVO jsonRequest);

    List<RoleInfoVO> verificationIsTtitleRole(RoleInfoVO jsonRequest);

    RoleInfoVO keepRoleInfo(RoleInfoVO jsonRequest);

    String verificationDeleteRoelInfo(RoleInfoVO jsonRequest);
}
