package com.faceye.core.service.security.dao.iface;

import java.io.Serializable;

import com.faceye.core.componentsupport.dao.iface.IDomainDao;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.core.service.security.dao.iface.IRoleDao.java
 * @Description:角色Dao
 */
public interface IRoleDao extends IDomainDao {
	public String getPageRoles(int startIndex);
	/**
	 * 为用户取得角色
	 * 为用户进行授权做准备
	 * 根据用户ID取得
	 * 当exists==true时,取得用户已有角色
	 * 当exists==false时,取得用户没有的角色(即准备授权给用户的角色)
	 * @param userId
	 * @param exists
	 * @return
	 */
	public String getRolesByUser(Serializable userId,boolean exists);
	
	  /**
     * 对角色进行授权,确定角色可以使用的模块.
     * @param roleId
     * @param modelIds
     */
    public void saveOrUpdateRoleTree(Serializable roleId,Serializable [] treeIds);
    /**
     * 对角色进行授权,授于资源访问权限
     * @param roleId
     * @param permissionIds
     */
    public void saveOrUpdateRolePermission(Serializable roleId,Serializable [] permissionIds);

}
