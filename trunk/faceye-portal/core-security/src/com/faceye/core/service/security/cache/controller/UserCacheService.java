package com.faceye.core.service.security.cache.controller;

import java.util.Iterator;
import java.util.List;

import org.acegisecurity.providers.dao.UserCache;
import org.acegisecurity.userdetails.UserDetails;

import com.faceye.core.service.security.cache.iface.IUserCacheService;
import com.faceye.core.service.security.dao.iface.IUserDao;
import com.faceye.core.service.security.model.User;

public class UserCacheService implements IUserCacheService {
	private UserCache userCache = null;
	private IUserDao userDao = null;
	// userCache是否已经初始化
	private boolean isInitializedUserCache = false;

	public UserDetails getUserFromCache(String key) {
		// TODO Auto-generated method stub
		return this.getUserCache().getUserFromCache(key);
	}

	public void initUserCache() {
		if (!isInitializedUserCache) {
			List users = this.getUserDao().loadAllObjects(User.class);
			if (null != users) {
				Iterator it = users.iterator();
				while (it.hasNext()) {
					UserDetails item = (UserDetails) it.next();
					this.putUserInCache(item);
				}
			}
			isInitializedUserCache = true;
		}
	}

	public void modifyUserInCache(UserDetails userDetails) {
		// TODO Auto-generated method stub
		if (this.getUserCache().getUserFromCache(userDetails.getUsername()) != null) {
			this.getUserCache().removeUserFromCache(userDetails.getUsername());
		}
		this.putUserInCache(userDetails);
	}

	public void putUserInCache(UserDetails userDetails) {
		// TODO Auto-generated method stub
		this.getUserCache().putUserInCache(userDetails);

	}

	public void removeUserFormCache(UserDetails userDetails) {
		// TODO Auto-generated method stub
		this.getUserCache().removeUserFromCache(userDetails.getUsername());
	}

	public UserCache getUserCache() {
		return userCache;
	}

	public void setUserCache(UserCache userCache) {
		this.userCache = userCache;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

}
