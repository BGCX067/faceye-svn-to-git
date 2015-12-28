package com.faceye.core.service.security.service.controller;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import com.faceye.core.componentsupport.service.controller.DomainService;
import com.faceye.core.service.security.dao.iface.IPermissionDao;
import com.faceye.core.service.security.model.Permission;
import com.faceye.core.service.security.model.Resource;
import com.faceye.core.service.security.service.iface.IPermissionService;

public class PermissionService extends DomainService implements IPermissionService {
    private IPermissionDao permissionDao=null;
	public String getPagePermissions(int startIndex) {
		// TODO Auto-generated method stub
		return this.getPermissionDao().getPagePermissions(startIndex);
	}
	public IPermissionDao getPermissionDao() {
		return permissionDao;
	}
	public void setPermissionDao(IPermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}
	public void saveOrUpdatePermissionResources(Serializable permissionId,
			Serializable[] resourceIds) {
		// TODO Auto-generated method stub
		this.getPermissionDao().saveOrUpdatePermissionResources(permissionId, resourceIds);
		
	}
	public void saveOrUpdatePermission(Permission permission) {
		// TODO Auto-generated method stub
		this.getPermissionDao().saveOrUpdatePermission(permission);
	}
	public String getPermissionsByRole(Serializable roleId, boolean exists) {
		// TODO Auto-generated method stub
		return this.getPermissionDao().getPermissionsByRole(roleId, exists);
	}
	public void saveOrUpdatePermissions(Set permissions) {
		// TODO Auto-generated method stub
		if(null!=permissions&&!permissions.isEmpty()){
			Iterator it=permissions.iterator();
			Permission permission=(Permission) it.next();
			this.saveOrUpdatePermission(permission);
		}
	}
	public void removePermission(Serializable permissionId) {
		// TODO Auto-generated method stub
		Permission permission=(Permission) this.loadObject(Permission.class, permissionId);
		Set resources=permission.getResources();
		Iterator it=resources.iterator();
		while(it.hasNext()){
			Resource resource=(Resource) it.next();
			resource.getPermissions().remove(permission);
			this.saveOrUpdateObject(resource);
		}
		this.removeObject(permission);
	}
	public void removePermissions(Serializable[] permissionIds) {
		// TODO Auto-generated method stub
		for(int i=0;i<permissionIds.length;i++){
			this.removePermission(permissionIds[i]);
		}
		
	}


}
