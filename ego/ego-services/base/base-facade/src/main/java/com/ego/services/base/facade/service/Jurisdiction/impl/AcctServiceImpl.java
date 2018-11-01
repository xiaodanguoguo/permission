package com.ego.services.base.facade.service.jurisdiction.impl;

import com.ebase.core.MD5Util;
import com.ebase.core.cache.CacheService;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.session.Acct;
import com.ebase.core.session.AcctLogin;
import com.ebase.core.session.AcctSession;
import com.ebase.core.session.CacheKeyConstant;
import com.ebase.utils.BeanCopyUtil;
import com.ebase.utils.secret.base64.Base64Util;
import com.ego.services.base.api.vo.jurisdiction.AcctInfoVO;
import com.ego.services.base.facade.common.IsDelete;
import com.ego.services.base.facade.common.Status;
import com.ego.services.base.facade.dao.jurisdiction.AcctInfoMapper;
import com.ego.services.base.facade.dao.jurisdiction.FunctionManageMapper;
import com.ego.services.base.facade.model.jurisdiction.AcctInfo;
import com.ego.services.base.facade.model.jurisdiction.FunctionManage;
import com.ego.services.base.facade.service.jurisdiction.AcctService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: wangyu
 */
@Service
public class AcctServiceImpl implements AcctService {

    private static Logger LOG = LoggerFactory.getLogger(AcctServiceImpl.class);


    private static String VERSION = "1";


    private static Integer TIME_EXPIRE = 60 * 30; //30分钟

    @Autowired
    private AcctInfoMapper acctInfoMapper;

    @Autowired
    private CacheService cacheService;
    
    @Autowired
    private FunctionManageMapper functionManageMapper;

    @Override
    public Integer userRegister(AcctInfoVO acctInfoVO) {

        //加密一下
        acctInfoVO.setAcctPassword(MD5Util.encrypByMd5(acctInfoVO.getAcctPassword()));

        //保存用户表的信息 和 客户表
        AcctInfo acctInfo = BeanCopyUtil.copy(acctInfoVO,AcctInfo.class);

        int count =  acctInfoMapper.insertSelective(acctInfo);

        return count;
    }

    /**
     * 用户登录
     * @param acctLogin
     * @return
     */
    @Override
    public ServiceResponse<AcctSession> userLogin(AcctLogin acctLogin) {

        ServiceResponse<AcctSession> response = new ServiceResponse<AcctSession>();

        try{
            //校验用户信息
            if(StringUtils.isEmpty(acctLogin.getAcctId())){
                response.setResponseCode("0701009");
                return response;
            }
            if(StringUtils.isEmpty(acctLogin.getPassword())){
                response.setResponseCode("0702000");
                return response;
            }
            //根据账号名查询账号对象
            AcctInfo acctInfo = acctInfoMapper.selectByLogin(acctLogin.getAcctId());


            if(acctInfo == null){
                response.setResponseCode("0702001");
                return response;
            }

            //用户状态
            if(Status.STOP.getCode().equals(acctInfo.getStatus())){
                response.setResponseCode("0702002");
                return response;
            }else if(IsDelete.YES.getCode().equals(acctInfo.getIsDelete())){ //是否删除
                response.setResponseCode("0702012");
                return response;
            }

            //验证两次密码是否一致
            String password = MD5Util.encrypByMd5(acctLogin.getPassword());
            if(acctInfo.getAcctPassword().equals(password)){
                //登录成功
                Acct acct = BeanCopyUtil.copy(acctInfo,Acct.class);

                AcctSession acctSession = loginSuccess(acct,acctLogin);

                response.setRetContent(acctSession);
            }else{
                response.setResponseCode("0702004");
                return response;
            }


        }catch (Exception e){
            LOG.error("error = {}",e);
            e.printStackTrace();
        }
        return response;

    }

    @Override
    public AcctInfoVO getAcct(String acctName) {
        AcctInfo acctInfo = acctInfoMapper.selectByLogin(acctName);


        return BeanCopyUtil.copy(acctInfo,AcctInfoVO.class);
    }


    private AcctSession loginSuccess(Acct acct, AcctLogin acctLogin) {

        AcctSession acctSession = new AcctSession();
        acctSession.setAcct(acct);

        //生成key
        String key = CacheKeyConstant.ACCT_SESSION + acctLogin.getSessionId() + acctLogin.getClientType();

        //保存到 session 中 30 分钟
        cacheService.set(key,acctSession,TIME_EXPIRE);

        acctSession.setSessionId(Base64Util.encode(acctLogin.getSessionId() + acctLogin.getClientType()));

        return acctSession;

    }
    
    /**
     * 根据当前用户查询出，当前用户的全部功能
     */
	@Override
	public List<FunctionManage> getUserFunctionAll(AcctInfo acctInfo) {
		List<FunctionManage> functionManageAll = functionManageMapper.selsctUserFunctionAll(acctInfo);
		return functionManageAll;
	}
}
