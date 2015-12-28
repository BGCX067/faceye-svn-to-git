package com.faceye.core.service.security.cache.iface;

import net.sf.ehcache.Element;

/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.core.service.security.cache.iface.ICommonCacheService.java
 * @Description:系统中通用的缓存服务
 */
public interface ICommonCacheService {
    public void putElementInCache(Element element);
    public Element getElementFromCache(Object key);
    public void removeElementFromCache(Object key);
    public void modifyElementInCache(Element element);
}
