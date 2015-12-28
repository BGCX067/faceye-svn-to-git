package com.faceye.core.service.security.dao.iface;

import java.io.Serializable;

import com.faceye.core.componentsupport.dao.iface.IDomainDao;
import com.faceye.core.service.security.model.Resource;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.core.service.security.dao.iface.IResourceDao.java
 * @Description:资源维护
 */
public interface IResourceDao extends IDomainDao {
   public String getPageResources(int startIndex);
   /**
    * 根据权限ID取得本权限可以访问的资源列表
    * 如果exists=true
    * 则取得本权限目前已可以访问的资源列表
    * 如果exists=false
    * 则取得本权限目前不可以访问的资源列表
    * @param permissionId
    * @return
    */
   public String getResourcesByPermission(Serializable permissionId,boolean exists);
   
   public void saveOrUpdateResource(Resource resource);
   
   public void removeResource(Serializable resourceId);
}
