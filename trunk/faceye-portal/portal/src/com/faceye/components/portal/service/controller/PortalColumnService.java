package com.faceye.components.portal.service.controller;

import java.io.Serializable;
import java.util.List;

import com.faceye.components.portal.dao.iface.IPortalColumnDao;
import com.faceye.components.portal.dao.model.PortalColumn;
import com.faceye.components.portal.dao.model.PortalColumnTemplate;
import com.faceye.components.portal.service.iface.IPortalColumnService;
import com.faceye.core.componentsupport.service.controller.DomainService;
import com.faceye.core.util.helper.PaginationSupport;

public class PortalColumnService extends DomainService implements
		IPortalColumnService {
    private IPortalColumnDao portalColumnDao=null;
	public PaginationSupport getPortalColumnByPortalId(
			Serializable portalId) {
		// TODO Auto-generated method stub
		return this.getPortalColumnDao().getPortalColumnByPortalId(portalId);
	}

	public void saveOrUpdate(PortalColumn portalColumn) {
		// TODO Auto-generated method stub
         this.getPortalColumnDao().saveOrUpdate(portalColumn);
	}

	public IPortalColumnDao getPortalColumnDao() {
		return portalColumnDao;
	}

	public void setPortalColumnDao(IPortalColumnDao portalColumnDao) {
		this.portalColumnDao = portalColumnDao;
	}

	public PaginationSupport getAllPortalColumnTemplates() {
		// TODO Auto-generated method stub
		return this.getPortalColumnDao().getAllPortalColumnTemplates();
	}

	public String getAllPortalColumnTemplatesJson() {
		// TODO Auto-generated method stub
		return this.getAllPortalColumnTemplates().json();
	}

	public PortalColumnTemplate getSystemDefaultPortalColumnTemplate() {
		// TODO Auto-generated method stub
		return this.getPortalColumnDao().getSystemDefaultPortalColumnTemplate();
	}

	public PortalColumn getFirstPortalColumnByPortalId(Serializable portalId) {
		// TODO Auto-generated method stub
		PaginationSupport ps=this.getPortalColumnByPortalId(portalId);
		PortalColumn portalColumn=(PortalColumn)ps.getItems().get(0);
		return portalColumn;
	}

}
