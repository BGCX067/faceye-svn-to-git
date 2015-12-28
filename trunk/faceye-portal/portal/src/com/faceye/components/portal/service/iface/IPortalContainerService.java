package com.faceye.components.portal.service.iface;

import java.io.Serializable;

import com.faceye.components.portal.dao.model.PortalContainer;
import com.faceye.core.componentsupport.service.iface.IDomainService;
import com.faceye.core.util.helper.PaginationSupport;

public interface IPortalContainerService extends IDomainService {
	public void saveOrUpdatePortalContainer(PortalContainer portalContainer);

	public void removePortalContainer(Serializable portalContainerId);

	/**
	 * 根据用户ID取得用户容器.
	 * 
	 * @param userId
	 * @return
	 */
	public PortalContainer getPortalContainerByUserId(Serializable userId);

	/**
	 * 用户第一次进入,为用户设置默认portalContainer,同时,向portalContaier中填充内容
	 * 
	 * @param userId
	 */
	public void saveDefaultUserPortalContainer(Serializable userId);
	/**
	 * 用户添加自定义portalContainer.
	 * @param userId
	 */
	public void saveDefaultUserDefinePortalContainer(Serializable userId);

	public PaginationSupport getPortalAllStyles();

	public String getPortalAllStylesJson();
	
	public boolean isExistsUserPortalContainer(Serializable userId);
	
	/**
	 * 用户portlet订阅
	 */
	public void saveUserPortletSubscribe(Serializable portalId,Serializable portletId);
	
	public void removeUserPorletSubscribe(Serializable portletId,Serializable portalId);
	
	/**
	 * 只在用户在拖动时改变的portlet的位置
	 */
	public void saveChangePortletPosition(Serializable portletId,Serializable portalColumnId,Serializable portalId,int index);
	
	   /**
	    * 取得最新加入portalContainer，既最新注册的用户blog
	    * 按时间进到倒序排序。
	    * @return
	    */
	   public PaginationSupport getNewerPortalContainers(int startIndex,int pageSize);
}
