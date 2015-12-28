package com.faceye.core.service.security.service.iface;

import java.util.Set;

import com.faceye.core.service.iface.IBaseHibernateService;
import com.faceye.core.service.security.dao.iface.IUserDao;
import com.faceye.core.service.security.model.User;

public interface IUserService extends IBaseHibernateService {
  public void saveOrUpdateUser(User user);
  public void removeUser(String id);
  public void authUser(User user,Set roles);
}
