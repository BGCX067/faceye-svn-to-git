package com.faceye.components.portal.dao.iface;

import java.io.Serializable;
import java.util.List;

import com.faceye.components.portal.dao.model.Portlet;
import com.faceye.components.portal.dao.model.PortletSubscribe;
import com.faceye.core.componentsupport.dao.iface.IDomainDao;
import com.faceye.core.util.helper.PaginationSupport;

public interface IPortletDao extends IDomainDao {
  public void saveOrUpdate(Portlet portlet);
  /**
   * 根据portalColumn取得一个portalColumn拥有的所有portlet
   * @param portalColumnId
   * @return
   */
  public PaginationSupport getPortletsByPortalColumnId(Serializable portalColumnId);
  public PaginationSupport getPortletSubscribeByPortalColumnId(Serializable portalColumnId);
  public PortletSubscribe getPortletSubscribeByPortletAndPortleColumn(Serializable portalColumnId,Serializable portletId);
  public PortletSubscribe getPortletSubscribeByPortalAndPortalColumnAndPortlet(Serializable portletId,Serializable portalColumnId,Serializable portalId);
  public PaginationSupport getPortlets(int startIndex);
  
  public int getNextPortletSubscribeOrder(Serializable portalColumnId);
  /**
   * 根据portal取得一个portal拥有的所有portlet
   * @param portalId
   * @return
   */
  public PaginationSupport getAllPortletsByPortalId(Serializable portalId);
  /**
   * 取得一个用户拥有的所有portlet
   * @param userId
   * @return
   */
  public List getAllPortletSubscribeByUserId(Serializable userId,Serializable portalId);
}
