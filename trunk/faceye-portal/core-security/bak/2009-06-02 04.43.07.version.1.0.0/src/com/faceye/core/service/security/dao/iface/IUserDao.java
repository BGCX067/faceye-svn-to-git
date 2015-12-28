package com.faceye.core.service.security.dao.iface;

import java.io.Serializable;
import java.util.Set;

import org.acegisecurity.userdetails.UserDetails;

import com.faceye.core.componentsupport.dao.iface.IDomainDao;
import com.faceye.core.service.security.model.User;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.core.service.security.dao.iface.IUserDao.java
 * @Description:用户接口
 */
public interface IUserDao extends IDomainDao {
	/**
	 *  取得数据
	 * @param startIndex
	 * @return
	 */
  public String getPageUsers(int startIndex);
  /**
   * 授权
   * @param user
   * @param roles
   */
  public void authUser(User user,Set roles);
  public void authUser(User user,Serializable roleId);
  public void authUser(User user,Serializable [] rolesId);
  public void authUser(Serializable userId,Serializable roleId);
  public void authUser(Serializable userId,Serializable[] rolesId);
  /**
   * 身份认证
   * @param arg0
   * @return
   */
  public UserDetails loadUserByUsername(String arg0);
}
