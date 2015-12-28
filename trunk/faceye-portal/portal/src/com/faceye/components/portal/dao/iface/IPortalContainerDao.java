package com.faceye.components.portal.dao.iface;

import java.io.Serializable;

import com.faceye.components.portal.dao.model.PortalContainer;
import com.faceye.core.componentsupport.dao.iface.IDomainDao;
import com.faceye.core.util.helper.PaginationSupport;

public interface IPortalContainerDao extends IDomainDao {
   public void saveOrUpdate(PortalContainer portalContainer);
   public PortalContainer getPortalContainerByUserId(Serializable userId);
   public PaginationSupport getPortalAllStyles();
   /**
    * 取得最新加入portalContainer，既最新注册的用户blog
    * 按时间进到倒序排序。
    * @return
    */
   public PaginationSupport getNewerPortalContainers(int startIndex,int pageSize);
}
