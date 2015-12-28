package com.faceye.core.service.security.service.controller;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import com.faceye.core.componentsupport.service.controller.DomainService;
import com.faceye.core.service.security.cache.iface.IResourceCacheService;
import com.faceye.core.service.security.dao.iface.IResourceDao;
import com.faceye.core.service.security.model.Permission;
import com.faceye.core.service.security.model.Resource;
import com.faceye.core.service.security.service.iface.IResourceService;

public class ResourceService extends DomainService implements IResourceService {
	private IResourceDao resourceDao = null;
	private IResourceCacheService resourceCacheService = null;

	public IResourceCacheService getResourceCacheService() {
		return resourceCacheService;
	}

	public void setResourceCacheService(
			IResourceCacheService resourceCacheService) {
		this.resourceCacheService = resourceCacheService;
	}

	public IResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(IResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	public String getPageResources(int startIndex) {
		// TODO Auto-generated method stub
		return this.getResourceDao().getPageResources(startIndex);
	}

	public String getResourcesByPermission(Serializable permissionId,
			boolean exists) {
		// TODO Auto-generated method stub
		return this.getResourceDao().getResourcesByPermission(permissionId,
				exists);
	}

	public void saveOrUpdateResource(Resource resource) {
		// TODO Auto-generated method stub
		this.getResourceDao().saveOrUpdateResource(resource);
		this.getResourceCacheService().modifyResourceInCache(resource);
	}

	public void saveOrUpdateResources(Set resources) {
		// TODO Auto-generated method stub
		if (null != resources && !resources.isEmpty()) {
			Iterator it = resources.iterator();
			while (it.hasNext()) {
              Resource resource=(Resource) it.next();
              this.saveOrUpdateResource(resource);
			}
		}
	}

	public void removeResource(Serializable resourceId) {
		// TODO Auto-generated method stub
	   Resource resource=(Resource) this.loadObject(Resource.class, resourceId);
	   Set permissions=resource.getPermissions();
	   Iterator it=permissions.iterator();
	   while(it.hasNext()){
		   Permission permission=(Permission) it.next();
		   permission.getResources().remove(resource);
		   this.saveOrUpdateObject(permission);
	   }
	   this.getResourceCacheService().removeResourceFromCache(resource.getResourceStr());
	   this.removeObject(resource);
	}

	public void removeResources(Serializable[] resourceIds) {
		// TODO Auto-generated method stub
		for(int i=0;i<resourceIds.length;i++){
			this.removeResource(resourceIds[i]);
		}
	}

}
