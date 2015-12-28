package com.faceye.core.service.security.service.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.ui.webapp.AuthenticationProcessingFilter;
import org.acegisecurity.userdetails.UserDetails;

import com.faceye.core.service.security.model.User;
import com.faceye.core.service.security.service.iface.IUserService;
import com.faceye.core.util.helper.StringPool;

public class UserAuthenticationProcessingFilter extends
		AuthenticationProcessingFilter {
	
	private IUserService userService=null;
	protected boolean requiresAuthentication(HttpServletRequest request,
			HttpServletResponse response) {
		boolean requiresAuth = super.requiresAuthentication(request, response);
		HttpSession httpSession = null;
		try {
			httpSession = request.getSession(false);
		} catch (IllegalStateException ignored) {
		}
		if (httpSession != null) {
			if (httpSession.getAttribute(StringPool.USER_IN_SESSION) == null) {
				if (!requiresAuth) {
					SecurityContext sc = SecurityContextHolder.getContext();
					Authentication auth = sc.getAuthentication();
					if (auth != null
							&& auth.getPrincipal() instanceof UserDetails) {
						UserDetails ud = (UserDetails) auth.getPrincipal();
//						User user = this.getUserService().getUserByLoginidAndPasswd(ud
//								.getUsername(), ud.getPassword());
						
						UserDetails userDetails=this.getUserService().loadUserByUsername(ud.getUsername());
						if(null!=userDetails){
						User user=(User)userDetails;
						// User user
						// =userManager.getUserNameAndPassword(ud.getUsername(),
						// ud.getPassword());
						httpSession.setAttribute(StringPool.USER_IN_SESSION,
								user);
						}
					}
				}

			}
		}
		return requiresAuth;
	}
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
}
