package com.ebase.core;

import com.ebase.core.session.Acct;
import com.ebase.core.session.AcctSession;
import com.ebase.core.session.UserSession;

/**
 * @Auther: wangyu
 */
public class AdminContext {


    private static ThreadLocal<AcctSession> allContext = new ThreadLocal<AcctSession>();

    public static void init(AcctSession acctSession) {
        allContext.set(acctSession);
    }

    public static AcctSession get() {
        return allContext.get();
    }



    public final static void destroy() {
        allContext.remove();
    }
    
    public final static Long getAcctId() {
    	AcctSession acctSession = get();
    	if (acctSession == null)
    		return null;
    	Acct acct = acctSession.getAcct();
    	if (acct == null)
    		return null;
    	return acct.getAcctId();
    }
}
