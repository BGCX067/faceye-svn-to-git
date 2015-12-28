package com.faceye.components.portal.dao.controller;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.faceye.components.portal.dao.iface.IPortalDao;
import com.faceye.components.portal.dao.model.Portal;
import com.faceye.core.componentsupport.dao.controller.DomainDao;
import com.faceye.core.util.helper.PaginationSupport;

public class PortalDao  extends DomainDao implements IPortalDao{

	public PaginationSupport getAllPortalsByPortalContainerId(
			Serializable portalContainerId) {
		// TODO Auto-generated method stub
		String hql="from "+Portal.class.getName()+" p where p.portalContainer.id=:portalContainerId order by p.createTime asc";
	    List items=this.getAllByHQL(hql, "portalContainerId", portalContainerId);
		return new PaginationSupport(items);
	}
	public void removePortal(Portal portal) {
		// TODO Auto-generated method stub
		this.removeObject(portal);
	}

	public void saveOrUpdatePortal(Portal portal) {
		// TODO Auto-generated method stub
		super.saveOrUpdateObject(portal);
	}
	public Portal getLastPortalByUserId(Serializable userId) {
		// TODO Auto-generated method stub
	   String hql="from "+Portal.class.getName()+" p where p.portalContainer.user.id=:userId order by p.createTime desc";
	   List items=this.getAllByHQL(hql, "userId", userId);
	   Portal portal=null;
	   if(CollectionUtils.isNotEmpty(items)){
		   portal=(Portal)items.get(0);
	   }
		return portal;
	}

}
