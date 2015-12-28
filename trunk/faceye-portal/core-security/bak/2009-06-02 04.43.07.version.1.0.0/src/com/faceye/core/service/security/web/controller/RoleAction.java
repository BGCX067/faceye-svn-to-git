package com.faceye.core.service.security.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.faceye.core.service.security.service.iface.IRoleService;
import com.faceye.core.util.helper.StringPool;
import com.faceye.core.web.controller.ExtTemplateAction;

public class RoleAction extends ExtTemplateAction {

	/**
	 * 取得角色相关信息 为用户授权做准备
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getRolesForUserPermission(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Map params = this.getRequestParameterMap(request);
		String json = this.getRoleService().getRolesByUser(
				params.get("userId").toString(),
				new Boolean(params.get("exists").toString()));
		this.jsonPrint(response, json);
		return null;
	}
/**
 * 为角色进行授权
 * 授于角色模块权限
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 */
	public ActionForward permission(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Map params = this.getRequestParameterMap(request);
		this.getRoleService().saveOrUpdateRoleTree(params.get("roleId").toString(), params.get("treeIds").toString().split(StringPool.CHARACTER_COMMA));
		return null;
	}
	/**
	 * 角色授权
	 * 授于资源访问权限
	 * @return
	 */
	public ActionForward permissionRolePermissions(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		Map params=this.getRequestParameterMap(request);
		this.getRoleService().saveOrUpdateRolePermission(params.get("roleId").toString(), params.get("permissionIds").toString().split(StringPool.CHARACTER_COMMA));
		return null;
	}
	private IRoleService roleService = null;

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
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

	protected String getPageJson(HttpServletRequest request) {
		return this.getRoleService().getPageRoles(
				this.getHttp().getCurrentIndex(request));
	}
	
	public ActionForward remove(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		Map params=this.getRequestParameterMap(request);
		this.getRoleService().removeRoles(params.get(StringPool.ENTITY_IDS).toString().split(StringPool.ENTITY_IDS_SPLIT_WITH));
		return null;
	}

}
