package com.faceye.core.service.security.cache.iface;

public interface ICacheService {

 public IResourceCacheService getResourceCacheService();
 public IUserCacheService getUserCacheService();
 public ICommonCacheService getCommonCacheService();
}
