package com.faceye.core.service.security.web.controller;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.faceye.core.service.security.model.Permission;
import com.faceye.core.service.security.model.Role;
import com.faceye.core.service.security.service.iface.IPermissionService;
import com.faceye.core.util.helper.StringPool;
import com.faceye.core.web.controller.ExtTemplateAction;

public class PermissionAction extends ExtTemplateAction {
	private IPermissionService permissionService;

	public IPermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(IPermissionService permissionService) {
		this.permissionService = permissionService;
	}

/**
 * 为角色选择权限,准备为角色授权
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 */
	public ActionForward getPermissionsForRole(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Map params=this.getRequestParameterMap(request);
		this.jsonPrint(response, this.getPermissionService().getPermissionsByRole(params.get("roleId").toString(), new Boolean(params.get("exists").toString())));
		return null;
	}
	
	/**
	 * 为权限授于相应的资源
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward permission(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
	   Map params=this.getRequestParameterMap(request);
	   this.getPermissionService().saveOrUpdatePermissionResources(params.get("permissionId").toString(), params.get("resourceIds").toString().split(StringPool.CHARACTER_COMMA));
		return null;
	}
	/*
	 * 保存数据
	 * @see com.faceye.core.web.controller.ExtTemplateAction#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			Map params = super.getRequestParameterMap(request);
			Permission o = (Permission)this.initEntity(request, form);
			this.getPermissionService().saveOrUpdatePermission(o);
			return null;
		} catch (Exception e) {
			log.info("faceye exception:" + e.toString() + " in method save()");
			return null;
		}
	}
	
	public ActionForward remove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Map params=this.getRequestParameterMap(request);
		if(params.containsKey(StringPool.ENTITY_IDS)){
			this.getPermissionService().removePermissions(params.get(StringPool.ENTITY_IDS).toString().split(StringPool.ENTITY_IDS_SPLIT_WITH));
		}
		return null;
	}
//				
	
	protected String getPageJson(HttpServletRequest request){
//		return this.getResourceService().getPageResources(this.getHttp().getCurrentIndex(request));
		return this.getPermissionService().getPagePermissions(this.getHttp().getCurrentIndex(request));
	}

	@Override
	protected void onInitEntity(HttpServletRequest request, Object o,
			ActionForm form) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onInitForm(HttpServletRequest request, ActionForm form,
			Object o) {
		// TODO Auto-generated method stub
		
	}
}
