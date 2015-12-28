package com.faceye.core.service.security.dao.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.faceye.core.componentsupport.dao.controller.DomainDao;
import com.faceye.core.service.security.dao.iface.IRoleDao;
import com.faceye.core.service.security.model.Permission;
import com.faceye.core.service.security.model.Resource;
import com.faceye.core.service.security.model.Role;
import com.faceye.core.service.security.model.Tree;
import com.faceye.core.service.security.model.User;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.util.helper.StringPool;

public class RoleDao extends DomainDao implements IRoleDao {

	public String getPageRoles(int startIndex) {
		// TODO Auto-generated method stub
		return this.getPageByDomain(Role.class, startIndex);
	}

	public String getRolesByUser(Serializable userId, boolean exists) {
		// TODO Auto-generated method stub
		List roles = null;

		if (null != userId) {
			if (StringUtils.isNotEmpty(userId.toString())) {
				User user = (User) this.loadObject(User.class, userId);
				if (exists) {
					roles = new ArrayList(user.getRoles());
				} else {
					roles = this.loadAllObjects(Role.class);
					roles.removeAll(user.getRoles());
				}
			} else {
				roles = this.loadAllObjects(Role.class);
			}
		} else {
			roles = this.loadAllObjects(Role.class);
		}
		return new PaginationSupport(roles).json();
	}

	public void saveOrUpdateRoleTree(Serializable roleId, Serializable[] treeIds) {
		// TODO Auto-generated method stub
		if (null != roleId & null != treeIds) {
			Role role = (Role) this.loadObject(Role.class, roleId);
			Set trees = new HashSet(0);
			Set permissions=new HashSet(0);
			Set resources =new HashSet(0);
			for (int i = 0; i < treeIds.length; i++) {
				if (!treeIds[i].toString().equals(StringPool.TREE_ROOT_ID)) {
					Tree item = (Tree) this.loadObject(Tree.class, treeIds[i]);
					trees.add(item);
					if (StringUtils.isNotEmpty(item.getUrl())) {
						Resource resource = new Resource();
						resource.setName(item.getName());
						resource.setResourceStr(item.getUrl());
						resource.setResourceType(StringPool.SECURITY_RESOURCE_TYPE_URL);
						if(this.isNotUnique(resource,"url")){
							resource=(Resource)this.getObject(Resource.class, "url", item.getUrl());
						}
					}
					if (StringUtils.isNotEmpty(item.getAction())) {
                        Resource resource=new Resource();
                        resource.setName(item.getName());
					}
				}
			}
			role.setTrees(trees);
			this.saveOrUpdateObject(role);
		}

	}

	public void saveOrUpdateRolePermission(Serializable roleId,
			Serializable[] permissionIds) {
		if (null != roleId && null != permissionIds) {
			Role role = (Role) this.loadObject(Role.class, roleId);
			Set permissions = new HashSet(0);
			for (int i = 0; i < permissionIds.length; i++) {
				if (StringUtils.isNotEmpty(permissionIds[i].toString())) {
					Permission permission = (Permission) this.loadObject(
							Permission.class, permissionIds[i]);
					permissions.add(permission);
				}
			}
			role.setPermissions(permissions);
			this.saveOrUpdateObject(role);
		}

	}

}
