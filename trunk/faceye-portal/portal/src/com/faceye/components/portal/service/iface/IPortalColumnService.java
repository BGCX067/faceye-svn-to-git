package com.faceye.components.portal.service.iface;

import java.io.Serializable;
import java.util.List;

import com.faceye.components.portal.dao.model.PortalColumn;
import com.faceye.components.portal.dao.model.PortalColumnTemplate;
import com.faceye.core.componentsupport.service.iface.IDomainService;
import com.faceye.core.util.helper.PaginationSupport;

public interface IPortalColumnService extends IDomainService {
	public PaginationSupport getPortalColumnByPortalId(
			Serializable portalId);

	public void saveOrUpdate(PortalColumn portalColumn);
	public PaginationSupport getAllPortalColumnTemplates();
	public String getAllPortalColumnTemplatesJson();
	/**
	 * 取得系统默认的样式表
	 * @return
	 */
	public PortalColumnTemplate getSystemDefaultPortalColumnTemplate();
	
	/**
	 * 取得一个portal的第一个portalColumn.即最左边第一列,新添加的portlet将添加到本列
	 */
	public PortalColumn getFirstPortalColumnByPortalId(Serializable portalId);
	
}
