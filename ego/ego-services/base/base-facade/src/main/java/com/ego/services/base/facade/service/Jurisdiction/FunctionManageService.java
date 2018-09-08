package com.ego.services.base.facade.service.Jurisdiction;



import com.ego.services.base.api.vo.Jurisdiction.FunctionManageVO;

import java.util.List;

/**
 * @Auther: zhaoyuhang
 */
public interface FunctionManageService {

    List<FunctionManageVO> functionManageList(FunctionManageVO jsonRequest);

    List<FunctionManageVO> functionManageRoleList(FunctionManageVO functionManageVO);

    List<FunctionManageVO> verificationFunIsTtitle(FunctionManageVO functionManageVO);

    Integer updateFunctionManageStatus(List<FunctionManageVO> jsonRequest);

    FunctionManageVO keepFunctionManage(FunctionManageVO jsonRequest);

    String verificationDeleteFunction(FunctionManageVO jsonRequest);
}
