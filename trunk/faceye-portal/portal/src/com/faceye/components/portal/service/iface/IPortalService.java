package com.faceye.components.portal.service.iface;

import java.io.Serializable;

import com.faceye.components.portal.dao.model.Portal;
import com.faceye.core.componentsupport.service.iface.IDomainService;
import com.faceye.core.util.helper.PaginationSupport;

public interface IPortalService extends IDomainService {
	public void saveOrUpdatePortal(Portal portal);

	public void removePortal(Serializable id);

	public PaginationSupport getAllPortalsByPortalContainerId(
			Serializable portalContainerId);
	public String getAllPortalsByPortalContainerIdJson(Serializable portalContainerId);
	/**
	 * 保存portal版式改变
	 */
	public void savePortalColumnTemplateChange(Serializable portalId,Serializable portalColumnTemplateId);
	/**
	 * 取得用户添加的最后一个portal面板
	 * @param userId
	 * @return
	 */
	public Portal getLastPortalByUserId(Serializable userId);
}
