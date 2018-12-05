package com.ebase.ego.webapps.op.shiro;

import com.alibaba.fastjson.JSON;
import com.ebase.core.StringHelper;
import com.ebase.core.cache.CacheService;
import com.ebase.core.session.TableCondition;
import com.ebase.core.session.User;
import com.ebase.utils.BeanCopyUtil;
import com.ebase.utils.secret.Md5Util;
import com.ego.services.juri.api.controller.dataauthority.PowerExpressionAPI;
import com.ego.services.juri.api.controller.jurisdiction.AcctAPI;
import com.ego.services.juri.api.vo.dataauthority.PowerExpressionVO;
import com.ego.services.juri.api.vo.jurisdiction.AcctInfoVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangqj on 2017/4/21.
 */
public class EgoShiroRealm extends AuthorizingRealm {

	public static final String USER_SESSION_KEY = "userSessionKey";
    @Autowired
    private AcctAPI acctAPI;

    @Autowired
    private PowerExpressionAPI powerExpressionAPI;

    @Autowired
    private CacheService cacheService;


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
        if (acct == null){
            throw new UnknownAccountException();
        }
//        Session session = SecurityUtils.getSubject().getSession();
//        acct.setId(session.getId());
        Session session = SecurityUtils.getSubject().getSession();
       // SimpleSession session = (SimpleSession) SecurityUtils.getSubject().getSession();
        acct.setId(session.getId());
        Long acctId = acct.getAcctId();
        String encrpt = Md5Util.encrpt(String.valueOf(acctId));


        //acct.setId(session);

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
        List<TableCondition> tableConditions=new ArrayList<>();
        PowerExpressionVO powerExpressionVO = powerExpressionAPI.selectAcctConfig(acct.getAcctId()).getRetContent();
        if(powerExpressionVO!=null){
            if(!CollectionUtils.isEmpty(powerExpressionVO.getRoleConfig())) {
                for (int i=0;i<powerExpressionVO.getRoleConfig().size();i++) {
                    TableCondition tableCondition=new TableCondition();
                    tableCondition.setFieldValue(powerExpressionVO.getRoleConfig().get(i).getFieldValue());
                    tableCondition.setTableName(powerExpressionVO.getRoleConfig().get(i).getTableName());
                    tableCondition.setFieldName(powerExpressionVO.getRoleConfig().get(i).getFieldName());
                    tableCondition.setOperator(operator(powerExpressionVO.getRoleConfig().get(i).getExpressionType()));
                    tableConditions.add(tableCondition);
                }
            }
            if(!CollectionUtils.isEmpty(powerExpressionVO.getAcctConfig())) {
                for (int i=0;i<powerExpressionVO.getAcctConfig().size();i++) {
                    TableCondition tableCondition=new TableCondition();
                    tableCondition.setFieldValue(powerExpressionVO.getAcctConfig().get(i).getFieldValue());
                    tableCondition.setTableName(powerExpressionVO.getAcctConfig().get(i).getTableName());
                    tableCondition.setFieldName(powerExpressionVO.getAcctConfig().get(i).getFieldName());
                    tableCondition.setOperator(operator(powerExpressionVO.getAcctConfig().get(i).getExpressionType()));
                    tableConditions.add(tableCondition);
                }
            }
            if(!CollectionUtils.isEmpty(powerExpressionVO.getOrgConfig())) {
                for (int i=0;i<powerExpressionVO.getOrgConfig().size();i++) {
                    TableCondition tableCondition=new TableCondition();
                    tableCondition.setFieldValue(powerExpressionVO.getOrgConfig().get(i).getFieldValue());
                    tableCondition.setTableName(powerExpressionVO.getOrgConfig().get(i).getTableName());
                    tableCondition.setFieldName(powerExpressionVO.getOrgConfig().get(i).getFieldName());
                    tableCondition.setOperator(operator(powerExpressionVO.getOrgConfig().get(i).getExpressionType()));
                    tableConditions.add(tableCondition);
                }
            }
        }
        cacheService.setList("a_"+user.getAcctId().toString(),tableConditions,7200);
        cacheService.set("acctTitle_"+user.getAcctId().toString(),user.getAcctTitle(),7200);

        session.setAttribute(USER_SESSION_KEY, JSON.toJSONString(user));



        return authenticationInfo;
    }


    private static String operator(Byte type){
        if(type==1 || type.equals(1)){
            return " > ";
        }
        if(type==2 || type.equals(2)){
            return " < ";
        }
        if(type==3 || type.equals(3)){
            return " = ";
        }
        if(type==4 || type.equals(4)){
            return " <= ";
        }
        if(type==5 || type.equals(5)){
            return " >= ";
        }
        if(type==6 || type.equals(6)){
            return " != ";
        }
        return "  ";
    }

}
