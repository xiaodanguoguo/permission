package com.ebase.ego.webapps.op.shiro;

import com.alibaba.fastjson.JSON;
import com.ebase.core.StringHelper;
import com.ebase.core.session.User;
import com.ebase.utils.BeanCopyUtil;
import com.ego.services.base.api.controller.jurisdiction.AcctAPI;
import com.ego.services.base.api.vo.jurisdiction.AcctInfoVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangqj on 2017/4/21.
 */
public class EgoShiroRealm extends AuthorizingRealm {

	public static final String USER_SESSION_KEY = "userSessionKey";
    @Autowired
    private AcctAPI acctAPI;


    //TODO 菜单校验
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user= (User) SecurityUtils.getSubject().getPrincipal();//User{id=1, username='admin', password='3ef7164d1f6167cb9f2658c07d3c2f0a', enable=1}
        Map<String,Object> map = new HashMap<String,Object>();
        //map.put("userid",user.getId());
        //List<Resources> resourcesList = resourcesService.loadUserResources(map);
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //for(Resources resources: resourcesList){
        //    info.addStringPermission(resources.getResurl());
        // }
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String acctName = (String) token.getPrincipal();
        if (StringHelper.isBlank(acctName)){
			throw new UnknownAccountException();
		}
        AcctInfoVO acct = acctAPI.getAcct(acctName).getRetContent();
        Session session = SecurityUtils.getSubject().getSession();
        acct.setId(session.getId());
//        acct.setId(session);
		if (acct == null){
			throw new UnknownAccountException();
		}
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
        		acct, //用户
        		acct.getAcctPassword(),
                ByteSource.Util.bytes(acctName),
                getName()  //realm name
        );
        // 当验证都通过后，把用户信息放在session里

//        User user = BeanCopyUtil.copy(acct, User.class);
//        user.setAcctId(String.valueOf(acct.getAcctId()));
        User user = new User();
//        System.out.println(session.getId());
        BeanCopyUtil.copy(acct, user);
        user.setAcctId(String.valueOf(acct.getAcctId()));
        user.setoInfoId(acct.getoInfoId());
        user.setAcctTitle(acct.getAcctTitle());
        user.setAcctType(acct.getAcctType());
        user.setCompanyId(acct.getCompanyId());
        session.setAttribute(USER_SESSION_KEY, JSON.toJSONString(user));

        return authenticationInfo;
    }




}
