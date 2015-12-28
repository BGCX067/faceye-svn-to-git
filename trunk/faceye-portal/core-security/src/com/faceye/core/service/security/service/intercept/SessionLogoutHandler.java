package com.faceye.core.service.security.service.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.acegisecurity.Authentication;
import org.acegisecurity.concurrent.SessionRegistry;
import org.acegisecurity.ui.logout.LogoutHandler;

import com.faceye.core.util.helper.StringPool;

/**
 * 简单的将session清空。
 * 
 */

public class SessionLogoutHandler implements LogoutHandler {
	private SessionRegistry sessionRegistry;

	public void logout(HttpServletRequest request,HttpServletResponse response, Authentication authentication) {
		if (request.getSession().getAttribute(StringPool.USER_IN_SESSION) != null) {
			request.getSession().removeAttribute(StringPool.USER_IN_SESSION);
		}
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			// 因为使用了concurrentSessionController 在限制用户登陆,所以登出时移除相应的session信息
			sessionRegistry.removeSessionInformation(session.getId());
			request.getSession().invalidate();
		}
	}

	public void setSessionRegistry(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}

}
