package com.faceye.core.service.security.webengine.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.faceye.core.service.security.model.User;
import com.faceye.core.service.security.service.iface.IUserService;
import com.faceye.core.util.helper.StringPool;
import com.faceye.core.web.controller.CommonTemplateController;

public class UserSecurityController extends CommonTemplateController {

	private IUserService userService;
	public void beforBind(HttpServletRequest request, Object command, ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		
	}

	
	public ModelAndView beforeSave(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return new ModelAndView("register");
	}

	
	public ModelAndView beforeUpdate(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ModelAndView doMultiRemove(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Object doQuery(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String hql="from User u";
		Map map=new HashMap();
		map.put(StringPool.QUERY_HQL, hql);
		map.put(StringPool.QUERY_TYPE, StringPool.QUERY_TYPE_BY_HQL);
		map.put(StringPool.FORWARD, "security.user.list");
		return map;
	}

	
	public ModelAndView doRemove(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ModelAndView doSave(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		User user=new User();
		try {
			BindingResult result=super.bindObject(request, user);
			this.getUserService().saveOrUpdateUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(log.isDebugEnabled()){
				log.debug(e.toString());
			}
		}
		return new ModelAndView("default");
	}


	public IUserService getUserService() {
		return userService;
	}


	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
   
}
