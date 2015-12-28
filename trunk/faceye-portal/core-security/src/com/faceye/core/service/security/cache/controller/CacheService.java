package com.faceye.core.service.security.cache.controller;

import com.faceye.core.service.security.cache.iface.ICacheService;
import com.faceye.core.service.security.cache.iface.ICommonCacheService;
import com.faceye.core.service.security.cache.iface.IResourceCacheService;
import com.faceye.core.service.security.cache.iface.IUserCacheService;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.core.service.security.cache.controller.CacheService.java
 * @Description:缓存管理，系统资源，用户缓存门面
 */
public class CacheService implements ICacheService {
  
  
  private IResourceCacheService resourceCacheService =null;

  private IUserCacheService userCacheService=null;
  
  private ICommonCacheService commonCacheService=null;


public void setCommonCacheService(ICommonCacheService commonCacheService) {
	this.commonCacheService = commonCacheService;
}

public IUserCacheService getUserCacheService() {
	return userCacheService;
}

public void setUserCacheService(IUserCacheService userCacheService) {
	this.userCacheService = userCacheService;
}

public IResourceCacheService getResourceCacheService(){
	return resourceCacheService;
}

public void setResourceCacheService(IResourceCacheService resourceCacheService) {
	this.resourceCacheService = resourceCacheService;
}

public ICommonCacheService getCommonCacheService() {
	// TODO Auto-generated method stub
	return commonCacheService;
}


}
