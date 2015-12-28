package com.faceye.core.service.security.service.controller;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;

import com.faceye.core.componentsupport.service.controller.DomainService;
import com.faceye.core.service.security.cache.controller.UserCacheService;
import com.faceye.core.service.security.cache.iface.IUserCacheService;
import com.faceye.core.service.security.dao.iface.IUserDao;
import com.faceye.core.service.security.model.Role;
import com.faceye.core.service.security.model.User;
import com.faceye.core.service.security.service.iface.IUserService;
import com.faceye.core.util.helper.StringPool;

/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.core.service.security.service.controller.UserService.java
 * @Description:用户服务.
 */
public class UserService extends DomainService implements IUserService,
		UserDetailsService {
	private IUserDao userDao = null;
	private IUserCacheService userCacheService = null;

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException, DataAccessException {
		// TODO Auto-generated method stub
		UserDetails userDetails = this.getUserCacheService().getUserFromCache(
				arg0);
		if (userDetails == null) {
			userDetails = this.getUserDao().loadUserByUsername(arg0);
			this.getUserCacheService().putUserInCache(userDetails);
		}
		return userDetails;
	}

	public void authUser(User user, Set roles) {
		// TODO Auto-generated method stub
		user.setRoles(roles);
		super.saveOrUpdateObject(user);
	}

	public void removeUser(String id) {
		// TODO Auto-generated method stub
		// this.getDaoUtils.getHibernateSupport.removeObject(User.class,id);
		User user = (User) this.loadObject(User.class, id);
		Set roles = user.getRoles();
		Iterator it = roles.iterator();
		while (it.hasNext()) {
			Role role = (Role) it.next();
			role.getUsers().remove(user);
			this.saveOrUpdateObject(role);
		}
		user.setRoles(null);
		this.getUserCacheService().removeUserFormCache(user);
		super.removeObject(user);

	}

	public void saveOrUpdateUser(User user) {
		// TODO Auto-generated method stub
		// this.getDaoUtils().getHibernateSupport().saveOrUpdateObject(user);
		/**
		 * 新注册用户,给与默认角色(注册角色)
		 */
		if (user.getRoles().isEmpty() || null == user.getRoles()) {
			this.authUser(user, StringPool.SECURITY_REGISTER_ROLE_ID);
		} else {
			this.saveOrUpdateObject(user);
			this.getUserCacheService().modifyUserInCache(user);
		}

	}

	public String getPageUsers(int startIndex) {
		// TODO Auto-generated method stub
		return this.getUserDao().getPageUsers(startIndex);
	}

	public void removeUsers(Serializable[] ids) {
		// TODO Auto-generated method stub
		for (int i = 0; i < ids.length; i++) {
			this.removeUser(ids[i].toString());
		}
	}

	public void authUser(User user, Serializable roleId) {
		// TODO Auto-generated method stub
		boolean flag = false;
		if (null != user.getId() && StringUtils.isNotEmpty(user.getId())) {
			flag = true;
		}
		this.getUserDao().authUser(user, roleId);
		if (flag) {
			// 将User加入cache
			this.getUserCacheService().modifyUserInCache(user);
		}
	}

	public void authUser(User user, Serializable[] rolesId) {
		// TODO Auto-generated method stub
		boolean flag = false;
		if (null != user.getId() && StringUtils.isNotEmpty(user.getId())) {
			flag = true;
		}
		this.getUserDao().authUser(user, rolesId);
		if(flag){
		this.getUserCacheService().modifyUserInCache(user);
		}
	}

	public void authUser(Serializable userId, Serializable roleId) {
		// TODO Auto-generated method stub
		this.getUserDao().authUser(userId, roleId);
	}

	public void authUser(Serializable userId, Serializable[] rolesId) {
		// TODO Auto-generated method stub
		this.getUserDao().authUser(userId, rolesId);
	}

	public IUserCacheService getUserCacheService() {
		return userCacheService;
	}

	public void setUserCacheService(IUserCacheService userCacheService) {
		this.userCacheService = userCacheService;
	}

}
