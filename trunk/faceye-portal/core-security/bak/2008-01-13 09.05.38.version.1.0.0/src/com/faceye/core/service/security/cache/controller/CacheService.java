package com.faceye.core.service.security.cache.controller;

import org.acegisecurity.providers.dao.UserCache;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;

import com.faceye.core.service.security.cache.iface.ICacheService;

public class CacheService implements ICacheService {
  private UserCache userCache;



public UserCache getUserCache() {
	return userCache;
}

public void setUserCache(UserCache userCache) {
	this.userCache = userCache;
}

public void initUserCache() {
	// TODO Auto-generated method stub
	
}
}
