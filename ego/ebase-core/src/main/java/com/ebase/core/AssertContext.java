package com.ebase.core;

import com.ebase.core.session.User;
import com.ebase.core.session.UserSession;
import org.apache.commons.lang.StringUtils;

/**
 * @Auther: wangyu
 */
public class AssertContext {


    private static ThreadLocal<UserSession> allContext = new ThreadLocal<UserSession>();

    public static void init(UserSession userSession) {
        allContext.set(userSession);
    }

    public static UserSession get() {
        return allContext.get();
    }

    /**
     * 用户id
     * @return
     */
    public static Long getUserId() {
        UserSession userSession = get();
        if (userSession != null) {
            return userSession.getUser().getUserId();
        }
        return null;
    }


    /**
     * 账号id
     * @return
     */
    public static String getAcctId() {
        UserSession userSession = get();
        if (userSession != null) {
            return userSession.getUser().getAcctId();
        }
        return null;
    }


    /**
     * 供应商ID
     * @return
     */
    public static Long getSupplierId() {
        UserSession userSession = get();
        if (userSession != null) {
            return userSession.getUser().getSupplierId();
        }
        return null;
    }

    /**
     * 公司ID
     * @return
     */
    public static String getCompanyId() {
        UserSession userSession = get();
        if (userSession != null) {
            return userSession.getUser().getCompanyId();
        }
        return null;
    }

    /**
     * 客户id
     * @return
     */
    public static Long getCustId(){
        UserSession userSession = get();
        if (userSession != null) {
            return userSession.getUser().getCustId();
        }
        return null;
    }

    /**
     * 用户名称
     * @return
     */
    public static String getUserName(){
        UserSession userSession = get();
        if (userSession != null) {
            return userSession.getUser().getUserName();
        }
        return null;
    }

    /**
     * 组织id
     * @return
     */
    public static String getOrgId(){
        UserSession userSession = get();
        if (userSession != null) {
            return userSession.getUser().getOrgId();
        }
        return null;
    }

    public static String getSessionId() {
        UserSession userSession = get();
        if (userSession != null) {
            return userSession.getSessionId();
        }
        return null;
    }

    public final static void destroy() {
        allContext.remove();
    }
}
