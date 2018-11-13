package com.ego.services.base.api.controller.jurisdiction;

import com.ebase.core.service.ServiceResponse;
import com.ebase.core.session.AcctLogin;
import com.ebase.core.session.AcctSession;
import com.ego.services.base.api.vo.jurisdiction.AcctInfoVO;
import com.ego.services.base.api.vo.jurisdiction.FunctionManageVO;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 后台账号
 * @Auther: wangyu
 */
@FeignClient(value = "${ser.name.base}")
public interface AcctAPI {
	
	/**
	 * 根据当前用户查询出，当前用户的全部功能
	 * @param acctInfoVO
	 * @return
	 */
	@RequestMapping(value = "/acct/getUserFunctionAll",method = RequestMethod.POST)
    ServiceResponse<List<FunctionManageVO>> getUserFunctionAll(@RequestBody AcctInfoVO acctInfoVO);
    /**
     * 账号注册
     * @param acctInfoVO
     * @return
     */
    @RequestMapping(value = "/acct/register",method = RequestMethod.POST)
    ServiceResponse<Integer> userRegister(AcctInfoVO acctInfoVO);

    /**
     * 账号登录
     * @param acctLogin
     * @return
     */
    @RequestMapping(value = "/acct/login",method = RequestMethod.POST)
    ServiceResponse<AcctSession> userLogin(AcctLogin acctLogin);


    /**
     * 从缓存中拿到session信息
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/acct/getCacheUser",method = RequestMethod.GET)
    ServiceResponse<AcctSession> getUser(@RequestParam(value = "sessionId",required = false) String sessionId);

    /**
     * 账号注销
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/acct/delCacheUser",method = RequestMethod.POST)
    ServiceResponse<Boolean> delUser(@RequestParam(value = "sessionId",required = false)String sessionId);

    /**
     * 获得用户信息
     * @return
     */
    @RequestMapping(value = "/acct/getAcct",method = RequestMethod.POST)
    public ServiceResponse<AcctInfoVO> getAcct(@RequestParam(value = "acctName",required = false) String acctName);
}
