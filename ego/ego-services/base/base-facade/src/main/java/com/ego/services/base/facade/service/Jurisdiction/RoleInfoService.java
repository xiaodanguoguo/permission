package com.ego.services.base.facade.service.jurisdiction;



import com.ebase.core.page.PageInfo;
import com.ego.services.base.api.vo.jurisdiction.RoleInfoVO;

import java.util.List;

/**
 * @Auther: zhaoyuhang
 */
public interface RoleInfoService {

    PageInfo<RoleInfoVO> roleInfoList(RoleInfoVO jsonRequest);

    List<RoleInfoVO> roleInfoAll(RoleInfoVO jsonRequest);

    List<RoleInfoVO> orgQuoteRoleInfo(RoleInfoVO jsonRequest);

    List<RoleInfoVO> roleInfoAllLike(RoleInfoVO jsonRequest);

    List<RoleInfoVO> roleRoleAcctInfo(RoleInfoVO jsonRequest);

    List<RoleInfoVO> roleInfoTree(RoleInfoVO jsonRequest);

    List<RoleInfoVO> verificationIsTtitleRole(RoleInfoVO jsonRequest);

    RoleInfoVO keepRoleInfo(RoleInfoVO jsonRequest);

    RoleInfoVO saveCopyRole(RoleInfoVO jsonRequest);

    String verificationDeleteRoelInfo(RoleInfoVO jsonRequest);

    String verQuoteRoleTitle(RoleInfoVO jsonRequest);

    String verQuoteRoleIds(RoleInfoVO jsonRequest);
}
