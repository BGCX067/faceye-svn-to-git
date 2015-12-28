package com.faceye.components.portal.service.iface;

import java.io.Serializable;

import com.faceye.components.portal.dao.model.Portlet;
import com.faceye.core.componentsupport.service.iface.IDomainService;
import com.faceye.core.util.helper.PaginationSupport;

public interface IPortletService extends IDomainService {
	public void saveOrUpdate(Portlet portlet);

	public PaginationSupport getPortletsByPortalColumnId(Serializable portalColumnId);
	
	public PaginationSupport getPortlets(int startIndex,int pageSize);
	
	public String getPortletsJson(int startIndex,int pageSize);
	/**
	 * 根据用户ID,取得所有portlet
	 * 用于用户订阅
	 * userId用于标识当前portlet是否已经被订阅.
	 * @param userId
	 * @return
	 */
	public String getPagePortletsByUserIdForUserSubscribe(Serializable userId,Serializable portalId,int startIndex,int pageSize);
}
