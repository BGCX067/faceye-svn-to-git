package com.faceye.core.service.security.service.controller;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.faceye.core.componentsupport.service.controller.DomainService;
import com.faceye.core.service.security.cache.iface.IUserCacheService;
import com.faceye.core.service.security.dao.iface.IRoleDao;
import com.faceye.core.service.security.dao.iface.ITreeDao;
import com.faceye.core.service.security.model.Permission;
import com.faceye.core.service.security.model.Resource;
import com.faceye.core.service.security.model.Role;
import com.faceye.core.service.security.model.Tree;
import com.faceye.core.service.security.model.User;
import com.faceye.core.service.security.service.iface.IPermissionService;
import com.faceye.core.service.security.service.iface.IResourceService;
import com.faceye.core.service.security.service.iface.IRoleService;
import com.faceye.core.service.security.service.iface.ITreeService;
import com.faceye.core.service.security.service.iface.IUserService;
import com.faceye.core.util.helper.StringPool;

public class RoleService extends DomainService implements IRoleService {
	private IRoleDao roleDao = null;
	private IUserCacheService userCacheService = null;
	private IResourceService resourceService = null;
	private IPermissionService permissionService = null;
	private IUserService userService=null;
	private ITreeService treeService=null;

	public ITreeService getTreeService() {
		return treeService;
	}

	public void setTreeService(ITreeService treeService) {
		this.treeService = treeService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IUserCacheService getUserCacheService() {
		return userCacheService;
	}

	public void setUserCacheService(IUserCacheService userCacheService) {
		this.userCacheService = userCacheService;
	}

	public void saveOrUpdateRole(Role role) {
		// TODO Auto-generated method stub
		this.saveOrUpdateObject(role);
	}

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public String getPageRoles(int startIndex) {
		// TODO Auto-generated method stub
		return this.getRoleDao().getPageRoles(startIndex);
	}

	public String getRolesByUser(Serializable userId, boolean exists) {
		// TODO Auto-generated method stub
		return this.getRoleDao().getRolesByUser(userId, exists);
	}

	public void saveOrUpdateRoleTree(Serializable roleId, Serializable[] treeIds) {
		// TODO Auto-generated method stub
		// this.getRoleDao().saveOrUpdateRoleTree(roleId, treeIds);
		Role role = (Role) this.getRoleDao().loadObject(Role.class, roleId);
		Set trees = new HashSet(0);
		Set resources = new HashSet(0);
		Set permissions = new HashSet(0);
		for (int i = 0; i > treeIds.length; i++) {
			Tree tree = (Tree) this.loadObject(Tree.class, treeIds[i]);
			resources.add(this.buildResourceFromTree(tree));
			permissions.add(this.buildPermissionFromTree(tree));
		}
		this.getResourceService().saveOrUpdateResources(resources);
		this.getPermissionService().saveOrUpdatePermissions(permissions);
		role.setTrees(trees);
		role.getPermissions().addAll(permissions);
		this.saveOrUpdateRole(role);
		this.modifyUserCacheAfterRolePermission(roleId);

	}

	public void saveOrUpdateRolePermission(Serializable roleId,
			Serializable[] permissionIds) {
		this.getRoleDao().saveOrUpdateRolePermission(roleId, permissionIds);
		this.modifyUserCacheAfterRolePermission(roleId);
	}

	/**
	 * 在对角色进行授权以后，将新授权后的角色，用户，权限，资源信息，更新到缓存中。
	 */
	private void modifyUserCacheAfterRolePermission(Serializable roleId) {
		Role role = (Role) this.getRoleDao().loadObject(Role.class, roleId);
		Set users = role.getUsers();
		Iterator it = users.iterator();
		while (it.hasNext()) {
			User user = (User) it.next();
			this.getUserCacheService().modifyUserInCache(user);
		}
	}

	/**
	 * 根据Tree结构创建Resource结构
	 * 
	 * @param tree
	 * @return
	 */
	private Resource buildResourceFromTree(Tree tree) {
		if(StringUtils.isEmpty(tree.getUrl())){
			return null;
		}
		Resource resource = new Resource();
		resource.setResourceStr(tree.getUrl());
		if (this.isNotUnique(resource, "resourceStr")) {
			resource = (Resource) this.getObject(Resource.class, "url", tree
					.getUrl());
		}
		resource.setName(tree.getName()+"_URL");
		resource.setResourceType(StringPool.SECURITY_RESOURCE_TYPE_URL);

		return resource;
	}

	private Permission buildPermissionFromTree(Tree tree) {
		if(StringUtils.isEmpty(tree.getUrl())){
			return null;
		}
		Permission permission = new Permission();
		permission.setName(tree.getName() + "_" + tree.getId());
		if (this.isNotUnique(permission, "name")) {
			permission = (Permission) this.getObject(Permission.class, "name",
					tree.getName() + "_" + tree.getId());
		}
		permission.getResources().add(this.buildResourceFromTree(tree));
		return permission;
	}

	public IResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(IResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public IPermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(IPermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public void removeRole(Serializable roleId) {
		// TODO Auto-generated method stub
		Role role=(Role) this.loadObject(Role.class, roleId);
		Set users=role.getUsers();
		Set trees=role.getTrees();
		Set permissions=role.getPermissions();
		Iterator userIt=users.iterator();
		while(userIt.hasNext()){
			User user=(User) userIt.next();
			user.getRoles().remove(role);
			this.getUserService().saveOrUpdateUser(user);
		}
		Iterator treeIt=trees.iterator();
		while(treeIt.hasNext()){
			Tree tree=(Tree) treeIt.next();
			tree.getRoles().remove(role);
			this.getTreeService().saveOrUpdateTree(tree);
		}
		Iterator permissionIt=permissions.iterator();
		while(permissionIt.hasNext()){
			Permission permission=(Permission) permissionIt.next();
			permission.getRoles().remove(role);
			this.getPermissionService().saveOrUpdatePermission(permission);
		}
		role.setUsers(null);
		role.setTrees(null);
		role.setPermissions(null);
	    this.getRoleDao().removeObject(role);
		
	}

	public void removeRoles(Serializable [] roleIds) {
		// TODO Auto-generated method stub
		for(int i=0;i<roleIds.length;i++){
			this.removeRole(roleIds[i]);
		}
	}

}
