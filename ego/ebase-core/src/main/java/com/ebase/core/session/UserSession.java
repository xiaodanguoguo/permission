package com.ebase.core.session;

import java.io.Serializable;

/**
 * @Auther: wangyu
 */
public class UserSession implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sessionId;

    private User user;

    public UserSession() {}
    
    public UserSession(String sessionId,  User user) {
    	if (user == null)
    		throw new RuntimeException("用户不能为空");
    	this.sessionId = sessionId;
    	this.user = user;
    }
    
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}