package com.faceye.core.service.security.web.controller;

import java.io.Serializable;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.faceye.core.service.security.model.Permission;
import com.faceye.core.service.security.model.Role;
import com.faceye.core.service.security.service.iface.IPermissionService;
import com.faceye.core.web.controller.ActionTemplate;

public class PermissionAction extends ActionTemplate {
	private IPermissionService permissionService;

	public IPermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(IPermissionService permissionService) {
		this.permissionService = permissionService;
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
/**
 * ׼��Ȩ����ݣ�Ϊ��ɫ��Ȩ��׼��
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 */
	public ActionForward getPermissionsForAuthRole(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Serializable roleid = super.getHttp().getEntityId(request);
		Role role = (Role) super.getEntity(Role.class, roleid);
		Set permissions = role.getPermissions();
		if (permissions != null && !permissions.isEmpty()) {
			super.getHttp().setRequestAttribute(request, "oldPermissions",
					permissions);
		}
		super.getHttp().setRequestAttribute(
				request,
				"permissions",
				super.getService().getBaseService().getBaseHibernateService()
						.loadAllObjects(Permission.class));
		return null;
	}
}
