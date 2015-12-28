package com.faceye.core.service.security.web.controller;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.acegisecurity.providers.encoding.PasswordEncoder;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.faceye.core.service.security.model.User;
import com.faceye.core.service.security.service.iface.IUserService;
import com.faceye.core.service.security.web.form.UserForm;
import com.faceye.core.util.helper.StringPool;
import com.faceye.core.web.controller.ExtTemplateAction;

public class UserAction extends ExtTemplateAction {
	private IUserService userService = null;

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IUserService getUserService() {
		return userService;
	}

	/**
	 * 保存用户
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			Map params = super.getRequestParameterMap(request);
			User user = null;
			PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
			if (StringUtils.isEmpty(params.get("id").toString())) {
				user = new User();
				user.setUsername(params.get("username").toString());
				user.setName(params.get("username").toString());
				user.setPassword(passwordEncoder.encodePassword(params.get(
						"password").toString(), null));
				user.setEmail(params.get("email").toString());
			} else {
				user = (User) this.getEntity(User.class, params.get("id")
						.toString());
				if (!user.getPassword().equals(
						params.get("password").toString())) {
					user.setPassword(passwordEncoder.encodePassword(params.get(
							"password").toString(), null));
				}
			}
			user.setEnabled(true);
			this.getUserService().saveOrUpdateUser(user);
			// Object o = this.initEntity(request, form);
			// this.saveOrUpate(o);
			return null;
		} catch (Exception e) {
			log.info("faceye exception:" + e.toString() + " in method save()");
			return null;
		}
	}

	/**
	 * 锁定或解锁用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward lock(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Override
	protected void onInitEntity(HttpServletRequest request, Object o,
			ActionForm form) {
		// TODO Auto-generated method stub
		// User user=(User)o;
		PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		Map params = this.getRequestParameterMap(request);
		if (StringUtils.isNotEmpty(params.get("id").toString())) {
			o = null;

			User user = new User();
			try {
				BeanUtils.copyProperties(user, params);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			User oldUser = (User) this.getUserService().loadObject(User.class,
					user.getId());
			if (!StringUtils.equals(oldUser.getPassword(), user.getPassword())) {
				user.setPassword(passwordEncoder.encodePassword(user
						.getPassword(), null));
			}
		} else {
			User user = (User) o;
			user.setPassword(passwordEncoder.encodePassword(user.getPassword(),
					null));
		}
	}

	@Override
	protected void onInitForm(HttpServletRequest request, ActionForm form,
			Object o) {
		// TODO Auto-generated method stub
		UserForm uf = (UserForm) form;

	}

	protected String getPageJson(HttpServletRequest request) {
		return this.getUserService().getPageUsers(
				this.getHttp().getCurrentIndex(request));
	}

	public ActionForward permission(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = this.getRequestParameterMap(request);
		String userId = params.get("userId").toString();
		User user = (User) this.getEntity(User.class, userId);
		String[] roleIds = params.get("roleIds").toString().split(",");
		this.getUserService().authUser(user, roleIds);
		return null;
	}

	public ActionForward remove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = this.getRequestParameterMap(request);
		this.getUserService().removeUsers(
				params.get(StringPool.ENTITY_IDS).toString().split(
						StringPool.ENTITY_IDS_SPLIT_WITH));
		return null;
	}

	/**
	 * 判断用户是否已经登陆
	 */
	public ActionForward isUserLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Serializable userId = this.getHttp().getUserId(request);
		StringBuffer json = new StringBuffer("{\"success\":true,\"rows\":[{\"");
		if (userId.equals(StringPool.USER_GUEST_ID)) {
			json.append("login\":\"no\"}]}");
		} else {
			json.append("login\":\"yes\"}]}");
		}
		this.jsonPrint(response, json.toString());
		return null;
	}

	/**
	 * 判断用户名是否已经存在
	 */
	public ActionForward isUserNameExists(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String username = this.get("username", request);
		User user = null;
		if (StringUtils.isNotEmpty(username)) {
			user = (User) this.getService().getBaseService()
					.getBaseHibernateService().getObject(User.class,
							"username", username);
			// StringBuffer json=new
			// StringBuffer("{\"success\":true,\"rows\":[{\"");
			StringBuffer json = new StringBuffer("{\"success\":true,");
			if (null != user) {
				json.append("\"valid\":\"false\",\"reason\":\"用户名 " + username
						+ " 已经被注册,请试用其它用户名!\"}");
				// json.append("exists\":\"yes\"}]}");
			} else {
				json.append("\"valid\":\"true\",\"reason\":\"用户名 " + username
						+ " 可以使用!\"}");
				// json.append("exists\":\"no\"}]}");
			}
			this.jsonPrint(response, json.toString());
		}

		return null;
	}

	/**
	 * 判断用户使用的电子邮件是否已经被使用。
	 */
	public ActionForward isEmailExists(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String email = this.get("email", request);
		User user = null;
		if (StringUtils.isNotEmpty(email)) {
			user = (User) this.getService().getBaseService()
					.getBaseHibernateService().getObject(User.class, "email",
							email);
			// StringBuffer json=new
			// StringBuffer("{\"success\":true,\"rows\":[{\"");
			StringBuffer json = new StringBuffer("{\"success\":true,");
			// StringBuffer json=new StringBuffer();
			if (null != user) {
				// json.append("exists\":\"yes\"}]}");
				json.append("\"valid\":\"false\",\"reason\":\"邮件 " + email
						+ " 邮件已经被注册,请使用其它电子邮件注册!\"}");
				// json.append("user is exists");
			} else {
				json.append("\"valid\":\"true\",\"reason\":\"邮件 " + email
						+ " 可以使用!\"}");
				// json.append("exists\":\"no\"}]}");
			}
			this.jsonPrint(response, json.toString());
		}

		return null;
	}

}
