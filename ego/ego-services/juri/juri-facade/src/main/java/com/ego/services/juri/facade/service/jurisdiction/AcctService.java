package com.ego.services.juri.facade.service.jurisdiction;

import com.ebase.core.service.ServiceResponse;
import com.ebase.core.session.AcctLogin;
import com.ebase.core.session.AcctSession;
import com.ego.services.juri.api.vo.jurisdiction.AcctInfoVO;
import com.ego.services.juri.facade.model.jurisdiction.AcctInfo;
import com.ego.services.juri.facade.model.jurisdiction.FunctionManage;

import java.util.List;

/**
 * 后台用户
 * @Auther: wangyu
 */
public interface AcctService {

    /**
     * 用户注册
     * @param acctInfoVO
     * @return
     */
    Integer userRegister(AcctInfoVO acctInfoVO);

    //用户登录
    ServiceResponse<AcctSession> userLogin(AcctLogin acctLogin);

    /**
     * 根据账号名 获得 账号信息
     * @param acctName
     * @return
     */
    AcctInfoVO getAcct(String acctName);
    
    /**
     * 根据当前用户查询出，当前用户的全部功能
     * @param acctInfo
     * @return
     */
	List<FunctionManage> getUserFunctionAll(AcctInfo acctInfo);
}
