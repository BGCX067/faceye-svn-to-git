package com.faceye.core.service.security.cache.iface;

import java.util.List;

import com.faceye.core.service.security.model.Resource;

public interface IResourceCache {
   public void putResourceInCache(Resource resource);
   public Resource getResourceFromCache(String key);
   public void initResourceCache();
   public List getUrlResourceStrings();
}
