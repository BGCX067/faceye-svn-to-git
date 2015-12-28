package com.faceye.components.portal.web.controller;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.faceye.components.portal.dao.model.Portal;
import com.faceye.components.portal.service.iface.IPortalService;
import com.faceye.core.util.helper.JSONUtil;
import com.faceye.core.web.controller.ExtTemplateAction;

public class PortalAction extends ExtTemplateAction {
	private IPortalService portalService = null;

	public IPortalService getPortalService() {
		return portalService;
	}

	public void setPortalService(IPortalService portalService) {
		this.portalService = portalService;
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
	 * 取得所有portal
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getPortals(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String portalContainerId=this.getHttp().getParameter(request,"portalContainerId");
		String json=this.getPortalService().getAllPortalsByPortalContainerIdJson(portalContainerId);
		this.jsonPrint(response, json);
		return null;
	}
	
	public ActionForward getSinglePortal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		return null;
	}
	/**
	 * 改变portal的版式结构
	 * @return
	 */
	
	public ActionForward changePortalColumnTempate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
	    String portalId=this.getHttp().getParameter(request, "portalId");
	    String portalColumnTemplateId=this.getHttp().getParameter(request, "portalColumnTemplateId");
	    this.getPortalService().savePortalColumnTemplateChange(portalId, portalColumnTemplateId);
		return null;
	}
	/**
	 * 改变单个portal的名字
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward changePortalName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
	    Map params=this.getRequestParameterMap(request);
	    String portalId=params.get("portalId").toString();
	    String portalName=params.get("portalName").toString();
		Portal portal=(Portal)this.getPortalService().loadObject(Portal.class, portalId);
		if(null!=portal){
			portal.setName(portalName);
			this.getPortalService().saveOrUpdatePortal(portal);
		}
		return null;
	}
	
	/**
	 *  取得用户最近一次添加的portal
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getLastPortalByUserId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Serializable userId=this.getHttp().getUserId(request);
		Portal portal=this.getPortalService().getLastPortalByUserId(userId);
		this.jsonPrint(response, JSONUtil.rowJson(portal.json()));
		return null;
	}
	/**
	 * 删除portal
	 */
	public ActionForward remove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Serializable portalId=this.getHttp().getParameter(request, "portalId");
		this.getPortalService().removePortal(portalId);
		return null;
	}
	
	

}
