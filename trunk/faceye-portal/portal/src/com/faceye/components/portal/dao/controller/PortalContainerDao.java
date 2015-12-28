package com.faceye.components.portal.dao.controller;

import java.io.Serializable;
import java.util.List;

import com.faceye.components.portal.dao.iface.IPortalContainerDao;
import com.faceye.components.portal.dao.model.PortalContainer;
import com.faceye.components.portal.dao.model.PortalStyle;
import com.faceye.core.componentsupport.dao.controller.DomainDao;
import com.faceye.core.util.helper.PaginationSupport;

public class PortalContainerDao extends DomainDao implements
		IPortalContainerDao {

	public PortalContainer getPortalContainerByUserId(Serializable userId) {
		// TODO Auto-generated method stub
		String hql = "from " + PortalContainer.class.getName()
				+ " p where p.user.id=:userId";
		List items = this.getAllByHQL(hql, "userId", userId);
		if (null != items && !items.isEmpty() && items.size() > 0) {
			return (PortalContainer) items.get(0);
		} else {
			return null;
		}
	}

	public void saveOrUpdate(PortalContainer portalContainer) {
		// TODO Auto-generated method stub
		super.saveOrUpdateObject(portalContainer);
	}

	public PaginationSupport getPortalAllStyles() {
		// TODO Auto-generated method stub
		String hql = "from " + PortalStyle.class.getName()
				+ " p order by p.id desc";
		List items = this.getAllByHQL(hql);
		PaginationSupport ps = new PaginationSupport(items);
		return ps;
	}

	public PaginationSupport getNewerPortalContainers(int startIndex,int pageSize) {
		// TODO Auto-generated method stub
		String hql="from "+PortalContainer.class.getName()+" p order by p.createTime desc";
		PaginationSupport ps=this.getPageByHQL(hql, pageSize, startIndex);
		return ps;
	}


}
