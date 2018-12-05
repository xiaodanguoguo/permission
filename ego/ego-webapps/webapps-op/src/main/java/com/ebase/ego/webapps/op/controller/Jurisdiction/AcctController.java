package com.ebase.ego.webapps.op.controller.jurisdiction;

import com.ebase.core.AssertContext;
import com.ebase.core.MD5Util;
import com.ebase.core.log.SearchableLoggerFactory;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.session.Acct;
import com.ebase.core.session.AcctLogin;
import com.ebase.core.session.User;
import com.ebase.core.web.json.JsonRequest;
import com.ebase.core.web.json.JsonResponse;
import com.ebase.utils.BeanCopyUtil;
import com.ebase.utils.CookieUtil;
import com.ebase.utils.WebUtil;
import com.ego.services.juri.api.controller.jurisdiction.AcctAPI;
import com.ego.services.juri.api.vo.jurisdiction.AcctInfoVO;
import com.ego.services.juri.api.vo.jurisdiction.FunctionManageVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 后台用户
 * @Auther: wangyu
 */
@RestController
@RequestMapping("/acct")
public class AcctController {


    private final static Logger LOG = SearchableLoggerFactory.getDefaultLogger();

    public static final String USER_SESSION_KEY = "userSessionKey";

    @Autowired
    private AcctAPI backMemberAPI;

    @Autowired
    private AcctAPI acctAPI;

   /**
     * 用户注册
     * @param jsonRequest
     * @return
     */
    @RequestMapping("/register")
    private JsonResponse<Integer> userRegister(@RequestBody JsonRequest<AcctInfoVO> jsonRequest){
        JsonResponse<Integer> jsonResponse = new JsonResponse<>();

        try{
            AcctInfoVO acctInfoVO = jsonRequest.getReqBody();

            ServiceResponse<Integer> response =  backMemberAPI.userRegister(acctInfoVO);

            if (ServiceResponse.SUCCESS_CODE.equals(response.getRetCode())) {
                jsonResponse.setRspBody(response.getRetContent());
            }else {
                if (response.isHasError()) {
                    jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
                } else {
                    jsonResponse.setRetCode(response.getRetCode());
                    jsonResponse.setRetDesc(response.getRetMessage());
                }
            }

        }catch (Exception e){
            LOG.error(e.getMessage());
            e.printStackTrace();
            jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
        }

        return jsonResponse;
    }

    /**
     * 用户登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public JsonResponse<User> userLogin(@RequestBody JsonRequest<AcctLogin> jsonRequest, HttpServletRequest request ,
                                        HttpServletResponse response){
            //TODO 异常码迁移到facade层
             JsonResponse<User> jsonResponse = new JsonResponse<>();



            AcctLogin acctLogin = jsonRequest.getReqBody();
            //acctLogin.setSessionId(request.getSession().getId());
            acctLogin.setClientType(WebUtil.getClientType(request));

            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(acctLogin.getAcctId(), MD5Util.encrypByMd5(acctLogin.getPassword()));
            try {
                subject.login(token);

                AcctInfoVO acctInfoVO = new AcctInfoVO();
                User user = AssertContext.get();
                //subject.logout();
                //获取当前登陆用户id用户
                acctInfoVO.setAcctId(Long.valueOf(user.getAcctId()));
                ServiceResponse<List<FunctionManageVO>> userFunctionAll = acctAPI.getUserFunctionAll(acctInfoVO);
                List<FunctionManageVO> retContent = userFunctionAll.getRetContent();
                List<String> permissions = new ArrayList<>();
                retContent.forEach(per -> permissions.add(per.getFunctionCode()));
                user.setPermissions(permissions);
                jsonResponse.setRspBody(user);
            } catch (LockedAccountException e) {
                LOG.error(e.getMessage());
                token.clear();
                jsonResponse.setRetCode("0703002");
                jsonResponse.setRetDesc("账号锁定");
            } catch (AuthenticationException e) {
                LOG.error(e.getMessage());
                token.clear();
                jsonResponse.setRetCode("0703002");
                jsonResponse.setRetDesc("登录失败");
            }catch (Exception e){
                LOG.error(e.getMessage());
                e.printStackTrace();
                jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
            }


            return jsonResponse;


    }












    /**
     * 获得用户信息
     * @return
     */
    @RequestMapping("/getAcct")
    public JsonResponse<Acct> getUser(HttpServletRequest request ){
        JsonResponse<Acct> jsonResponse = new JsonResponse<Acct>();

        try{
            //从shiro的session中获取登录用户
//            Session session = SecurityUtils.getSubject().getSession();
//            if(null != session.getAttribute(USER_SESSION_KEY)){

                User vo = (User) AssertContext.get();

                Acct acct = new Acct();
                BeanCopyUtil.copy(vo,acct);
                acct.setAcctId(Long.valueOf(vo.getAcctId()));
                acct.setName(vo.getAcctTitle());

                jsonResponse.setRspBody(acct);
                jsonResponse.setRetDesc("操作成功");
//            }else {
//                jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
//            }
        }catch (Exception e){
            LOG.error(e.getMessage());
            e.printStackTrace();
            jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
        }
        return jsonResponse;
    }


    @RequestMapping("/remSession")
    public JsonResponse<Boolean> logOut() {
        JsonResponse<Boolean> jsonResponse = new JsonResponse<Boolean>();

        Subject subject = SecurityUtils.getSubject();
//        Serializable id = subject.getSession().getId();
//        System.out.println(id);
        subject.logout();
        jsonResponse.setRspBody(true);
//        session.removeAttribute("user");

        return jsonResponse;
    }

    /**
     * 用户注销
     * @return
     */
    @RequestMapping("/logOut")
    public JsonResponse<Boolean> remSession(HttpServletRequest request , HttpServletResponse response){
        JsonResponse<Boolean> jsonResponse = new JsonResponse<Boolean>();
        try{
            String sessionId = CookieUtil.getSessionId(request);

            ServiceResponse<Boolean> serviceResponse = backMemberAPI.delUser(sessionId);
            if (ServiceResponse.SUCCESS_CODE.equals(serviceResponse.getRetCode())) {
                LOG.error("注销成功 !!");
                jsonResponse.setRspBody(true);

            }else {

            }
            CookieUtil.removeCookie(response,"sessionId");
        }catch (Exception e){
            LOG.error(e.getMessage());
            e.printStackTrace();
            jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
        }
        return jsonResponse;
    }

}
