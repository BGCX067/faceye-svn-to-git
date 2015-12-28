package com.faceye.core.service.security.webengine.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.faceye.core.service.security.model.Permission;
import com.faceye.core.service.security.service.iface.IPermissionService;
import com.faceye.core.util.helper.StringPool;
import com.faceye.core.web.controller.CommonTemplateController;

public class PermissionController extends CommonTemplateController {
	private IPermissionService permissionService;

	public void beforBind(HttpServletRequest request, Object command,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub

	}

	public ModelAndView beforeSave(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return new ModelAndView("updatePermission");
	}

	public ModelAndView beforeUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
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
		map.put(StringPool.FORWARD, "list.permissions");
		map.put(StringPool.QUERY_TYPE, StringPool.QUERY_TYPE_BY_HQL);
		map.put(StringPool.QUERY_HQL, "from Permission");
		return map;
	}

	public ModelAndView doRemove(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
	    super.removeObject(Permission.class, super.getEntityID(request));
		return super.removeSuccess(request, response);
	}

	public ModelAndView doSave(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Permission permission = new Permission();
		try {
			BindingResult result = super.bindObject(request, permission);
			this.getPermissionService().saveOrUpdateObject(permission);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return super.saveSuccess(request, response);
	}

	public ModelAndView queryPermissionForSelect(HttpServletRequest request,
			HttpServletResponse response) {
        List permissions=super.getService().getBaseService().getBaseHibernateService().getAll("queryPermissionsForSelect");
		return new ModelAndView("security.permission.list.for.select","permissions",permissions);
	}

	public IPermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(IPermissionService permissionService) {
		this.permissionService = permissionService;
	}

}
