package com.faceye.core.service.security.dao.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.faceye.core.componentsupport.dao.controller.DomainDao;
import com.faceye.core.service.security.dao.iface.IResourceDao;
import com.faceye.core.service.security.model.Permission;
import com.faceye.core.service.security.model.Resource;
import com.faceye.core.util.helper.PaginationSupport;

public class ResourceDao extends DomainDao implements IResourceDao {

	public String getPageResources(int startIndex) {
		// TODO Auto-generated method stub
		return this.getPageByDomain(Resource.class, startIndex);
	}

	public String getResourcesByPermission(Serializable permissionId,
			boolean exists) {
		// TODO Auto-generated method stub
		Permission permission=(Permission) this.loadObject(Permission.class, permissionId);
		List resources=null;
		if(exists){
			resources=new ArrayList(permission.getResources());
		}else{
			resources=this.loadAllObjects(Resource.class);
			resources.removeAll(permission.getResources());
		}
		return new PaginationSupport(resources).json();

}

	public void saveOrUpdateResource(Resource resource) {
		// TODO Auto-generated method stub
		this.saveOrUpdateObject(resource);
	}

	public void removeResource(Serializable resourceId) {
		// TODO Auto-generated method stub
		this.removeObject(Resource.class, resourceId);
	}
}