package com.faceye.components.navigation.dao.iface;

import java.io.Serializable;
import java.util.List;

import com.faceye.components.navigation.dao.model.FeedSubscribe;
import com.faceye.components.navigation.dao.model.UserResourceCategory;
import com.faceye.core.componentsupport.dao.iface.IDomainDao;
import com.faceye.core.service.security.model.User;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.navigation.dao.iface.IUserResourceCategoryDao.java
 * @Description:用户资源分类维护
 */
public interface IUserResourceCategoryDao extends IDomainDao {
	/**
	 * 保存用户资源分类
	 * @param user
	 * @param parentUserResourceCategory
	 * @param nowSavedUserResourceCategory
	 */
   public void saveOrUpdateUserResourceCategory(User user,UserResourceCategory parentUserResourceCategory,UserResourceCategory nowSavedUserResourceCategory);
   /**
    * 根据用户ID，查询取得用户的资源分类
    * @param userId
    * @return
    */
   public List getUserResourceCategoryByUser(Serializable userId);
   /**
    * 删除用户资源分类，同步将本分类下的所有资源转移到默认分类中。
    * @param userResourceCategory
    */
   public void removeUserResourceCategory(UserResourceCategory userResourceCategory);
   /**
    * 取得用户资源分类中的根分类
    * @param userId
    * @return
    */
   public UserResourceCategory getRootUserResourceCategory(Serializable userId);
	 /**
    * 取得用户资源分类的下一个排序值
    */
   public Integer getNextNodeOrderOfUserResourceCategory(Serializable userId,Serializable parentUserResourceCategoryId);
   
 
}
