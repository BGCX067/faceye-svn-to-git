package com.faceye.core.service.security.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import com.faceye.core.service.security.service.iface.IRoleService;
import com.faceye.core.web.controller.ActionTemplate;


public class RoleAction extends ActionTemplate {

//	public ActionForward save(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) {
//		Role role = null;
//		RoleForm roleForm = (RoleForm) form;
//		if (StringUtils.isEmpty(roleForm.getId())) {
//			role = new Role();
//		} else {
//			role = (Role) super.getEntity(Role.class, roleForm.getId());
//		}
//		copyForm2Model(role, roleForm);
//		this.getRoleService().saveOrUpdateObject(role);
//		return this.baseSave(mapping, form, request, response, role);
//	}
//	public ActionForward update(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response){
//		this.copyModel2Form(form, this.getEntity(Role.class, request));
//		return mapping.findForward("save");
//	}
//	
//	public ActionForward query(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
//		String hql="from "+Role.class.getName();
//		PaginationSupport ps=null;
//		ps=this.getRoleService().getPageByHQL(hql, super.getHttp().getCurrentIndex(request));
//		super.getHttp().setPaginationSupport(ps, request);
//		return mapping.findForward("list");
//	}
	
	private IRoleService roleService=null;

	public IRoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	@Override
	protected void onInitEntity(HttpServletRequest request, Object o, ActionForm form) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void onInitForm(HttpServletRequest request, ActionForm form, Object o) {
		// TODO Auto-generated method stub
		
	}
	
	
}
