package com.faceye.core.service.security.service.controller;

import java.util.List;
import java.util.Set;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

import com.faceye.core.service.controller.BaseHibernateService;
import com.faceye.core.service.security.dao.iface.IUserDao;
import com.faceye.core.service.security.model.User;
import com.faceye.core.service.security.service.iface.IUserService;
public class UserService extends BaseHibernateService implements IUserService {


	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException, DataAccessException {
		// TODO Auto-generated method stub
		String hql="from "+User.class.getName()+" u where u.name=:name";
		List items=super.getAllByHQL(hql,arg0);
		if(items==null){
			return null;
		}else{
			UserDetails o=(UserDetails) items.iterator().next();
			return o;
		}
	}

	public void authUser(User user, Set roles) {
		// TODO Auto-generated method stub
		//user.setRoles(roels);
		//this.getDaoUtils.getHibernateSupport.saveOrUpdateObject(user);
		user.setRoles(roles);
		super.saveOrUpdateObject(user);
	}

	public void removeUser(String id) {
		// TODO Auto-generated method stub
		//this.getDaoUtils.getHibernateSupport.removeObject(User.class,id);
		super.removeObject(User.class, id);
//		this.getUserCache().removeUserFromCache(arg0);
		
	}

	public void saveOrUpdateUser(User user) {
		// TODO Auto-generated method stub
//		this.getDaoUtils().getHibernateSupport().saveOrUpdateObject(user);
		super.saveOrUpdateObject(user);
		//this.getUserCache().putUserInCache((UserDetails)user);
		
	}

}
