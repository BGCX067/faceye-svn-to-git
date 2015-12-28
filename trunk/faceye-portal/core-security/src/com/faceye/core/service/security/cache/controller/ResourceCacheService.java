package com.faceye.core.service.security.cache.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Element;

import com.faceye.core.service.security.cache.iface.IResourceCacheService;
import com.faceye.core.service.security.cache.iface.IUserCacheService;
import com.faceye.core.service.security.dao.iface.IResourceDao;
import com.faceye.core.service.security.model.Permission;
import com.faceye.core.service.security.model.Resource;
import com.faceye.core.service.security.model.Role;
import com.faceye.core.service.security.model.User;
import com.faceye.core.util.helper.StringPool;

public class ResourceCacheService implements IResourceCacheService {
	private Cache cache;
	private IResourceDao resourceDao=null;
	private IUserCacheService userCacheService=null;

	private static boolean isInitializedResourceCache = false;

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public Resource getResourceFromCache(String key) {
		// TODO Auto-generated method stub
		Element element = null;
		try {
			element = this.getCache().get(key);
		} catch (CacheException e) {

		}
		if (element == null) {
			return null;
		} else {
			return (Resource) element.getValue();
		}

	}

	public void putResourceInCache(Resource resource) {
		// TODO Auto-generated method stub
		Element element = new Element(resource.getResourceStr(), resource);
		this.getCache().put(element);
	}

	public void initResourceCache() {
		if (!isInitializedResourceCache) {
			List resources = this.getResourceDao().loadAllObjects(Resource.class);
			for (Iterator it = resources.iterator(); it.hasNext();) {
				Resource resource = (Resource) it.next();
				this.putResourceInCache(resource);
			}
			isInitializedResourceCache = true;
		}
	}

	public List getUrlResourceStrings() {
		// TODO Auto-generated method stub
		return this.getResourceByType(StringPool.SECURITY_RESOURCE_TYPE_URL);
	}

	private List getResourceByType(String resourceType) {
		List result = null;
		List resources = null;
		try {
			resources = this.getCache().getKeys();
			Iterator it = resources.iterator();
			result = new ArrayList();
			while (it.hasNext()) {
				String resourceStr = (String) it.next();
				Resource resource = this.getResourceFromCache(resourceStr);
				if (resource != null) {
					if (resource.getResourceType().equals(resourceType)) {
						result.add(resource);
					}
				}
			}
		} catch (CacheException e) {
               
		}
		return result;
	}



	public IResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(IResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	public void modifyResourceInCache(Resource resource) {
		// TODO Auto-generated method stub
		if(null!=resource){
			if(this.getResourceFromCache(resource.getResourceStr())!=null){
				this.removeResourceFromCache(resource.getResourceStr());
			}
			this.putResourceInCache(resource);
		}
		
	}

	public void removeResourceFromCache(String key) {
		// TODO Auto-generated method stub
		if(this.getResourceFromCache(key)!=null){
			this.getCache().remove(key);
		}
	}
	
	private void AfterRemoveResourceFromCache(String resourceKey){
		String resourceStr=resourceKey;
		Resource resource=(Resource)this.getResourceDao().getObject(Resource.class, "resourceStr", resourceStr);
		Set permissions=resource.getPermissions();
		Set roles=new HashSet(0);
		Set users=new HashSet(0);
		Iterator permissionIt=permissions.iterator();
	    
		while(permissionIt.hasNext()){
			Permission permission=(Permission) permissionIt.next();
			roles.addAll(permission.getRoles());
		}
		Iterator roleIt=roles.iterator();
		while(roleIt.hasNext()){
			Role role=(Role) roleIt.next();
			users.addAll(role.getUsers());
		}
		Iterator userIt=users.iterator();
		while(userIt.hasNext()){
			User user=(User) userIt.next();
			this.getUserCacheService().modifyUserInCache(user);
		}
	}

	public IUserCacheService getUserCacheService() {
		return userCacheService;
	}

	public void setUserCacheService(IUserCacheService userCacheService) {
		this.userCacheService = userCacheService;
	}
}
