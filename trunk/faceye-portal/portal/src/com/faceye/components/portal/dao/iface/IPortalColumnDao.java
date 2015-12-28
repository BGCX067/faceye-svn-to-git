package com.faceye.components.portal.dao.iface;

import java.io.Serializable;

import com.faceye.components.portal.dao.model.PortalColumn;
import com.faceye.components.portal.dao.model.PortalColumnTemplate;
import com.faceye.core.componentsupport.dao.iface.IDomainDao;
import com.faceye.core.util.helper.PaginationSupport;

public interface IPortalColumnDao extends IDomainDao {
  public PaginationSupport getPortalColumnByPortalId(Serializable portalId);
  public void saveOrUpdate(PortalColumn portalColumn);
  public PaginationSupport getAllPortalColumnTemplates();
  public PortalColumnTemplate getSystemDefaultPortalColumnTemplate();
}
