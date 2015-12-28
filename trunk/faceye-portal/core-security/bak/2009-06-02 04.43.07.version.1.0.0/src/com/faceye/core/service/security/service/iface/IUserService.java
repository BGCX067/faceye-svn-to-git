package com.faceye.core.service.security.service.iface;

import java.io.Serializable;
import java.util.Set;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

import com.faceye.core.componentsupport.service.iface.IDomainService;
import com.faceye.core.service.security.model.User;

public interface IUserService extends IDomainService {
  public UserDetails loadUserByUsername(String arg0);
  public void saveOrUpdateUser(User user);
  public void removeUser(String id);
  
  public void removeUsers(Serializable [] ids);
  /**
   * 为用户授权
   * @param user
   * @param roles
   */
  public void authUser(User user,Set roles);
  public void authUser(User user,Serializable roleId);
  public void authUser(User user,Serializable [] rolesId);
  public void authUser(Serializable userId,Serializable roleId);
  public void authUser(Serializable userId,Serializable[] rolesId);
  
  public String getPageUsers(int startIndex);
  
}
