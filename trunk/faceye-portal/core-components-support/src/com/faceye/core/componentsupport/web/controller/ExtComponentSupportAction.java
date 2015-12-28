package com.faceye.core.componentsupport.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import com.faceye.core.componentsupport.service.iface.IDomainService;
import com.faceye.core.web.controller.ExtTemplateAction;

public abstract class ExtComponentSupportAction extends ExtTemplateAction {

	protected IDomainService domainService=null;

	public IDomainService getDomainService() {
		return domainService;
	}

	public void setDomainService(IDomainService domainService) {
		this.domainService = domainService;
	}


}
