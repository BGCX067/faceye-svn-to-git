package com.faceye.components.portal.dao.iface;

import java.io.Serializable;

import com.faceye.components.portal.dao.model.Portal;
import com.faceye.core.componentsupport.dao.iface.IDomainDao;
import com.faceye.core.util.helper.PaginationSupport;

public interface IPortalDao extends IDomainDao {
   public void saveOrUpdatePortal(Portal portal);
   public void removePortal(Portal  portal);
   public PaginationSupport getAllPortalsByPortalContainerId(Serializable portalContainerId);
   /*
    *  取得用户添加的最后一个portal面板
    */
   public Portal getLastPortalByUserId(Serializable userId);
}
