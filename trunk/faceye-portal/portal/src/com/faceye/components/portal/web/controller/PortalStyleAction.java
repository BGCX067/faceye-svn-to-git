package com.faceye.components.portal.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import com.faceye.components.portal.service.iface.IPortalContainerService;
import com.faceye.core.web.controller.ExtTemplateAction;

public class PortalStyleAction extends ExtTemplateAction {
    private IPortalContainerService portalContainerService=null;
	public IPortalContainerService getPortalContainerService() {
		return portalContainerService;
	}

	public void setPortalContainerService(
			IPortalContainerService portalContainerService) {
		this.portalContainerService = portalContainerService;
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
        String json=this.getPortalContainerService().getPortalAllStylesJson();
		return json;
	}

}
