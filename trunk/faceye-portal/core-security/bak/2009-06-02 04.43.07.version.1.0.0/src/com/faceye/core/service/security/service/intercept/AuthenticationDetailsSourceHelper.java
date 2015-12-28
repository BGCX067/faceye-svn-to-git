package com.faceye.core.service.security.service.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.acegisecurity.ui.AuthenticationDetailsSourceImpl;

/**
 * 解决 remember me 和 currentSession 冲突问题
 *
 */

public class AuthenticationDetailsSourceHelper extends AuthenticationDetailsSourceImpl{
	  public Object buildDetails(HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        if (session == null){
	            session = request.getSession(true);
	        }
	        return super.buildDetails(request);
	    }

}
