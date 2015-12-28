package com.faceye.core.service.security.dao.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.faceye.core.componentsupport.dao.controller.DomainDao;
import com.faceye.core.service.security.dao.iface.IPermissionDao;
import com.faceye.core.service.security.model.Permission;
import com.faceye.core.service.security.model.Resource;
import com.faceye.core.service.security.model.Role;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.util.helper.StringPool;

public class PermissionDao extends DomainDao implements IPermissionDao {

	public String getPagePermissions(int startIndex) {
		// TODO Auto-generated method stub
		return this.getPageByDomain(Permission.class, startIndex);
	}

	public void saveOrUpdatePermissionResources(Serializable permissionId,
			Serializable[] resourceIds) {
		// TODO Auto-generated method stub
		Permission permission = (Permission) this.loadObject(Permission.class,
				permissionId);
		Set resources = new HashSet(0);
		if (null != resourceIds) {
			for (int i = 0; i < resourceIds.length; i++) {
				if (StringUtils.isNotEmpty(resourceIds[i].toString())) {
					Resource item = (Resource) this.loadObject(Resource.class,
							resourceIds[i]);
					resources.add(item);
				}
			}
		}
		permission.setResources(resources);
		this.saveOrUpdateObject(permission);

	}

	public void saveOrUpdatePermission(Permission permission) {
		// TODO Auto-generated method stub
//		
		if(StringUtils.isEmpty(permission.getId())){
			permission.setAlaisName(StringPool.SECURITY_AUTH_PERMISSION);
			this.saveOrUpdateObject(permission);
		}
		permission.setAlaisName(StringPool.SECURITY_AUTH_PERMISSION+permission.getId());
		this.saveOrUpdateObject(permission);
		
	}

	public String getPermissionsByRole(Serializable roleId, boolean exists) {
		// TODO Auto-generated method stub
		List permissions=null;
		if(null!=roleId){
			if(StringUtils.isNotEmpty(roleId.toString())){
				Role role=(Role)this.loadObject(Role.class, roleId);
				if(exists){
					permissions=new ArrayList(role.getPermissions());
				}else{
					permissions=this.loadAllObjects(Permission.class);
					permissions.removeAll(role.getPermissions());
				}
			}
		}
		return new PaginationSupport(permissions).json();
	}

}
