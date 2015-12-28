package com.faceye.core.service.security.dao.iface;

import java.io.Serializable;

import com.faceye.core.componentsupport.dao.iface.IDomainDao;
import com.faceye.core.service.security.model.Permission;

public interface IPermissionDao extends IDomainDao {
	/**
	 * 取得一页权限数据
	 * @param startIndex
	 * @return
	 */
   public String getPagePermissions(int startIndex);
   /*
    * 为权限授于相应的角色
    */
   public void saveOrUpdatePermissionResources(Serializable permissionId,Serializable [] resourceIds);
   /**
    * 保存 permission
    * @param permission
    */
   public void saveOrUpdatePermission(Permission permission);
   /**
    * 根据角色取得角色的权限,为角色授权做准备
    * @param roleId
    * @return
    */
   public String getPermissionsByRole(Serializable roleId,boolean exists);
}
