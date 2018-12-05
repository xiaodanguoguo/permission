package com.ego.services.juri.facade.service.jurisdiction;



import com.ego.services.juri.api.vo.jurisdiction.FunctionManageVO;

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

    List<FunctionManageVO> ListFunctionCode(FunctionManageVO jsonRequest);

    String verificationDeleteFunction(FunctionManageVO jsonRequest);
}
