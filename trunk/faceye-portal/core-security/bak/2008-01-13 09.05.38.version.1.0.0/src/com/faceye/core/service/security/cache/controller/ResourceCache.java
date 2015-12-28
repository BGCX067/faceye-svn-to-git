package com.faceye.core.service.security.cache.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Element;

import com.faceye.core.service.iface.IBaseHibernateService;
import com.faceye.core.service.security.cache.iface.IResourceCache;
import com.faceye.core.service.security.model.Resource;
import com.faceye.core.util.helper.StringPool;

public class ResourceCache implements IResourceCache {
	private Cache cache;

	private IBaseHibernateService baseHibernateService;

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
			List resources = this.getBaseHibernateService().loadAllObjects(
					Resource.class);
			for (Iterator it = resources.iterator(); it.hasNext();) {
				Resource resource = (Resource) it.next();
				this.putResourceInCache(resource);
			}
			isInitializedResourceCache = true;
		}
		// TODO Auto-generated method stub

	}

	public IBaseHibernateService getBaseHibernateService() {
		return baseHibernateService;
	}

	public void setBaseHibernateService(
			IBaseHibernateService baseHibernateService) {
		this.baseHibernateService = baseHibernateService;
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
                System.out.println(e.toString());
		}
		return result;
	}
}
