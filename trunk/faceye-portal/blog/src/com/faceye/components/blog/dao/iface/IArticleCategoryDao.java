package com.faceye.components.blog.dao.iface;

import java.io.Serializable;

import com.faceye.core.componentsupport.dao.iface.IDomainDao;
import com.faceye.core.util.helper.PaginationSupport;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.blog.dao.iface.IArticleCategoryDao.java
 * @Description:用户文章分类dao
 */
public interface IArticleCategoryDao extends IDomainDao {
	/**
	 * 取得一个用户拥有的所有article categories
	 * @param userId
	 * @return
	 */
	public PaginationSupport getAllArticleCategoriesByUserId(Serializable userId);
	
	public Integer getNextNodeOrder(Serializable userId,Serializable parentCategoryId);
}
