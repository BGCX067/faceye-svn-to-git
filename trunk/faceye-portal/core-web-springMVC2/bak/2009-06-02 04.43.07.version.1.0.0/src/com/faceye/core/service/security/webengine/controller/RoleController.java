package com.faceye.core.service.security.webengine.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.faceye.core.service.security.model.Role;
import com.faceye.core.service.security.service.iface.IRoleService;
import com.faceye.core.util.helper.StringPool;
import com.faceye.core.web.controller.CommonTemplateController;

public class RoleController extends CommonTemplateController {
   private IRoleService roleService;
	public void beforBind(HttpServletRequest request, Object command,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub

	}

	public ModelAndView beforeSave(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return new ModelAndView("security.role.update");
	}

	public ModelAndView beforeUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Role command = (Role) super.getEntity(Role.class, super
				.getEntityID(request));
//		System.out.println(role.getName());
		ModelAndView mav=new ModelAndView("security.role.update");
		mav.addObject(command);
		return mav;
	}

	public ModelAndView doMultiRemove(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object doQuery(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put(StringPool.QUERY_TYPE, StringPool.QUERY_TYPE_BY_HQL);
		map.put(StringPool.FORWARD, "security.role.list");
		map.put(StringPool.QUERY_HQL, "from Role r");
		return map;
	}

	public ModelAndView doRemove(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		super.removeObject(Role.class, super.getEntityID(request));
		return super.removeSuccess(request, response);
	}

	public ModelAndView doSave(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Role role = new Role();
		BindingResult result = super.bindObject(request, role);
		super.getService().getBaseService().getBaseHibernateService()
				.saveOrUpdateObject(role);
		return super.saveSuccess(request, response);
	}
	
	public ModelAndView authPermission(HttpServletRequest request,HttpServletResponse response){
		String [] ids=request.getParameterValues("id");
		String roleId=super.getHttp().getParameter(request, "id");
		this.getRoleService().saveOrUpdateRolePermissions(roleId, ids);
		return super.saveSuccess(request, response);
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

}
