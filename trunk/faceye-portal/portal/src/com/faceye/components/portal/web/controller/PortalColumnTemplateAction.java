package com.faceye.components.portal.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.faceye.components.portal.service.iface.IPortalColumnService;
import com.faceye.core.web.controller.ExtTemplateAction;

public class PortalColumnTemplateAction extends ExtTemplateAction {
    private IPortalColumnService portalColumnService=null;
	public IPortalColumnService getPortalColumnService() {
		return portalColumnService;
	}

	public void setPortalColumnService(IPortalColumnService portalColumnService) {
		this.portalColumnService = portalColumnService;
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
	protected String getPageJson(HttpServletRequest request){
		String json=this.getPortalColumnService().getAllPortalColumnTemplatesJson();
		return json;
	}
	/**
	 * 用于前台版面样式表的显示
	 * @return
	 */
	public ActionForward getPortalColummnTemplates(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String json=this.getPortalColumnService().getAllPortalColumnTemplatesJson();
		this.jsonPrint(response, json);
		return null;
	}

}
