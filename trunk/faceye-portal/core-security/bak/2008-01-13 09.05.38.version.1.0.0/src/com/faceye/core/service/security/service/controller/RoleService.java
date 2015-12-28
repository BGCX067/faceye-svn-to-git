package com.faceye.core.service.security.service.controller;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.faceye.core.service.controller.BaseHibernateService;
import com.faceye.core.service.security.model.Permission;
import com.faceye.core.service.security.model.Role;
import com.faceye.core.service.security.service.iface.IRoleService;

public class RoleService extends BaseHibernateService implements IRoleService {

	public void saveOrUpdateRolePermissions(Serializable roleid,String[] permissionIds) {
		// TODO Auto-generated method stub
		Role role=(Role) super.getObject(Role.class, roleid);
		role.getPermissions().clear();
		role.setPermissions(null);
		Set permissions=new HashSet(0);
		for(int i=0;i<permissionIds.length;i++){
			Permission permission=(Permission) super.getObject(Permission.class, permissionIds[i]);
			permissions.add(permission);
		}
		role.setPermissions(permissions);
		super.saveOrUpdateObject(role);
	}

}
