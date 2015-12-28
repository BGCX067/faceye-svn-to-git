package com.faceye.core.service.security.cache.controller;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import com.faceye.core.service.security.cache.iface.ICommonCacheService;

public class CommonCacheServie implements ICommonCacheService {
	private Cache commonCache = null;

	public Element getElementFromCache(Object key) {
		// TODO Auto-generated method stub
		return this.getCommonCache().get(key);
	}

	public void putElementInCache(Element element) {
		// TODO Auto-generated method stub
		this.getCommonCache().put(element);
	}

	public void removeElementFromCache(Object key) {
		// TODO Auto-generated method stub
		this.getCommonCache().remove(key);
	}

	public Cache getCommonCache() {
		return commonCache;
	}

	public void setCommonCache(Cache commonCache) {
		this.commonCache = commonCache;
	}

	public void modifyElementInCache(Element element) {
		// TODO Auto-generated method stub
		if (null != element) {
			if (this.getElementFromCache(element.getKey()) != null) {
				this.removeElementFromCache(element.getKey());
			}
			this.putElementInCache(element);
		}
	}


}
