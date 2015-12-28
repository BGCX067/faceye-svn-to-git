package com.faceye.components.portal.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.faceye.components.portal.service.iface.IPortalColumnService;
import com.faceye.core.web.controller.ExtTemplateAction;

public class PortalColumnAction extends ExtTemplateAction {
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
	/**
	 * 根据protalContainerId取得portalColumn
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getPortalColumns(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String portalId=this.getHttp().getParameter(request, "portalId");
		if(StringUtils.isNotEmpty(portalId)){
			String  json=this.getPortalColumnService().getPortalColumnByPortalId(portalId).json();
			this.jsonPrint(response, json);
		}
		
		return null;
	}

}
