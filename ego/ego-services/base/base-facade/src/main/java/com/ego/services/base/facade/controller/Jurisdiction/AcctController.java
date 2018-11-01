package com.ego.services.base.facade.controller.jurisdiction;

import com.ebase.core.cache.CacheService;
import com.ebase.core.exception.BusinessException;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.session.AcctLogin;
import com.ebase.core.session.AcctSession;
import com.ebase.core.session.CacheKeyConstant;
import com.ebase.utils.BeanCopyUtil;
import com.ebase.utils.secret.base64.Base64Util;
import com.ego.services.base.api.vo.jurisdiction.AcctInfoVO;
import com.ego.services.base.api.vo.jurisdiction.FunctionManageVO;
import com.ego.services.base.facade.model.jurisdiction.AcctInfo;
import com.ego.services.base.facade.model.jurisdiction.FunctionManage;
import com.ego.services.base.facade.service.jurisdiction.AcctService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: wangyu
 */
@RestController
@RequestMapping("/acct")
public class AcctController {

    private static Logger LOG = LoggerFactory.getLogger(AcctController.class);

    @Autowired
    private AcctService acctService;

    @Autowired
    private CacheService cacheService;
    
    /**
     * 根据当前用户查询出，当前用户的全部功能
     * @return
     */
    @RequestMapping("/getUserFunctionAll")
    public ServiceResponse<List<FunctionManageVO>> getUserFunctionAll(@RequestBody AcctInfoVO acctInfoVO){
        ServiceResponse<List<FunctionManageVO>> serviceResponse = new ServiceResponse<>();
        AcctInfo acctInfo = BeanCopyUtil.copy(acctInfoVO, AcctInfo.class);
        /*Long i = (long)123;
        acctInfo.setAcctId(i);*/
        try{
        	List<FunctionManage> sunctionManageAll = acctService.getUserFunctionAll(acctInfo);
        	List<FunctionManageVO> functionManageVO = BeanCopyUtil.copyList(sunctionManageAll, FunctionManageVO.class);
            serviceResponse.setRetContent(functionManageVO);

        }catch (Exception e){
            serviceResponse.setException(new BusinessException("500"));
            serviceResponse.setResponseCode("500");
        }

        return serviceResponse;
    }

    /**
     * 注册
     * @return
     */
    @RequestMapping("/register")
    public ServiceResponse<Integer> userRegister(@RequestBody AcctInfoVO acctInfoVO){
        ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();

        try{

            //校验
//            ReturnMessageVO returnMessageVO = custInfoService.getUniqueResult(custInfoVO);

//            if(returnMessageVO.getFlag()){
                // 注册用户
                Integer integer = acctService.userRegister(acctInfoVO);
                serviceResponse.setRetContent(integer);
//            }else{
//                serviceResponse.setRetCode(returnMessageVO.getErrorCode());
//                serviceResponse.setRetMessage(returnMessageVO.getMessage());
//            }

        }catch (Exception e){
            LOG.error("注册 error  = {}", e);
            serviceResponse.setException(new BusinessException("500"));
            serviceResponse.setResponseCode("500");
        }

        return serviceResponse;
    }

    /**
     * 用户登录接口
     * @return
     */
    @RequestMapping("/login")
    public ServiceResponse<AcctSession> userLogin(@RequestBody AcctLogin acctLogin){
        ServiceResponse<AcctSession> serviceResponse = new ServiceResponse();
        try{

            //用户注册，查出用户 并 生成key 放到 redis 中
            ServiceResponse<AcctSession> response = acctService.userLogin(acctLogin);

            //如果正常查出对象
            if(ServiceResponse.SUCCESS_CODE.equals(response.getRetCode())){
                serviceResponse.setRetContent(response.getRetContent());
            }else{
                serviceResponse.setResponseCode(response.getRetCode());
            }
        }catch (Exception e){
            LOG.error("用户登录 error  = {}", e);
            serviceResponse.setException(new BusinessException("500"));
            serviceResponse.setResponseCode("500");
        }

        return serviceResponse;
    }

    /**
     * 从缓存中 拿 用户登录对象
     * @return
     */
    @RequestMapping("/getCacheUser")
    public ServiceResponse<AcctSession> getUser(@RequestParam(value = "sessionId",required = false) String sessionId){
        ServiceResponse<AcctSession> serviceResponse = new ServiceResponse<>();

        LOG.info("sessionId = {}",sessionId);
        try{
            if(sessionId == null){
                serviceResponse.setResponseCode("0702005");
            }else{

                String key = CacheKeyConstant.ACCT_SESSION + Base64Util.decode(sessionId);

                AcctSession acctSession = cacheService.getObject(key,AcctSession.class);

                serviceResponse.setRetContent(acctSession);
            }

        }catch (Exception e){
            LOG.error("查询缓存 中用户信息 error  = {}", e);
            serviceResponse.setException(new BusinessException("500"));
        }

        return serviceResponse;
    }


    /**
     * 从缓存中 删除用户会话信息
     * @return
     */
    @RequestMapping("/delCacheUser")
    public ServiceResponse<Boolean> delUser(@RequestParam(value = "sessionId",required = false) String sessionId){
        ServiceResponse<Boolean> serviceResponse = new ServiceResponse<>();

        try{
            if(sessionId == null){
                serviceResponse.setResponseCode("0702005");
            }else{
                String key = CacheKeyConstant.ACCT_SESSION + Base64Util.decode(sessionId);
                Boolean boo = cacheService.delete(key);
                if(boo){
                    serviceResponse.setRetContent(boo);
                }else{
                    serviceResponse.setRetContent(boo);
                    serviceResponse.setResponseCode("0702006");
                }
            }

        }catch (Exception e){
            LOG.error("去除会话信息 error  = {}", e);
            serviceResponse.setException(new BusinessException("500"));
        }

        return serviceResponse;
    }


    /**
     * 获得用户信息
     * @return
     */
    @RequestMapping("/getAcct")
    public ServiceResponse<AcctInfoVO> getAcct(@RequestParam(value = "acctName",required = false) String acctName){
        ServiceResponse<AcctInfoVO> serviceResponse = new ServiceResponse<>();

        try{
            if(acctName == null){
                serviceResponse.setResponseCode("0702005");
            }else {

                AcctInfoVO response = acctService.getAcct(acctName);

                //如果正常查出对象
                serviceResponse.setRetContent(response);
            }

        }catch (Exception e){
            LOG.error("获取用户信息 error  = {}", e);
            serviceResponse.setException(new BusinessException("500"));
        }

        return serviceResponse;
    }
}
