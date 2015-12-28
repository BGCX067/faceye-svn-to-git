package com.faceye.core.service.security.dao.controller;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.faceye.core.componentsupport.dao.controller.DomainDao;
import com.faceye.core.service.security.dao.iface.IUserDao;
import com.faceye.core.service.security.model.Role;
import com.faceye.core.service.security.model.User;

public class UserDao extends DomainDao implements IUserDao {

	public String getPageUsers(int startIndex) {
		// TODO Auto-generated method stub
		return this.getPageByDomain(User.class, startIndex);
	}

	public void authUser(User user, Set roles) {
		user.setRoles(roles);
		this.saveOrUpdateObject(user);
	}

	public void authUser(User user, Serializable roleId) {
		// TODO Auto-generated method stub
		Role role = (Role) this.loadObject(Role.class, roleId);
		Set roles = user.getRoles();
		roles.add(role);
		this.authUser(user, roles);
	}

	public void authUser(User user, Serializable[] rolesId) {
		// TODO Auto-generated method stub
		Set roles = new HashSet();
		if (null != rolesId) {
			for (int i = 0; i < rolesId.length; i++) {
				if (StringUtils.isNotEmpty(rolesId[i].toString())) {
					Role item = (Role) this.loadObject(Role.class, rolesId[i]);
					roles.add(item);
				}
			}
		}
		this.authUser(user, roles);
	}
	public void authUser(Serializable userId, Serializable roleId) {
		// TODO Auto-generated method stub
		User user = (User) this.loadObject(User.class, userId);
		Role role = (Role) this.loadObject(Role.class, roleId);
		Set roles = new HashSet();
		roles.add(role);
		this.authUser(user, roles);
	}

	public void authUser(Serializable userId, Serializable[] rolesId) {
		// TODO Auto-generated method stub
		User user = (User) this.loadObject(User.class, userId);
		Set roles = new HashSet(0);
		if (null != rolesId) {
			for (int i = 0; i < rolesId.length; i++) {
				if (StringUtils.isNotEmpty(rolesId[i].toString())) {
					Role item = (Role) this.loadObject(Role.class, rolesId[i]);
					roles.add(item);
				}
			}
		}
		this.authUser(user, roles);
	}

	public UserDetails loadUserByUsername(String arg0) {
		// TODO Auto-generated method stub
		String hql = "from " + User.class.getName()
				+ " u where u.username=:username";
		List items = this.getAllByHQL(hql, "username", arg0);
		if (items == null || items.isEmpty()) {
			hql = "from " + User.class.getName() + " u where u.email=:email";
			items = this.getAllByHQL(hql, "email", arg0);
			if (CollectionUtils.isEmpty(items)) {
				return null;
			} else {
				User user = (User) items.get(0);
				UserDetails o = (UserDetails) user;
				return o;
			}
		} else {
			User user = (User) items.get(0);
			UserDetails o = (UserDetails) user;
			return o;
		}
	}

}
