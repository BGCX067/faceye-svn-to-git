package com.faceye.components.navigation.dao.iface;

import java.io.Serializable;
import java.util.List;

import com.faceye.core.componentsupport.dao.iface.IDomainDao;
import com.faceye.core.util.helper.PaginationSupport;
/**
 *  
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.navigation.dao.iface.ICategoryDao.java
 * @Description: 网站分类DAO
 */
public interface ICategoryDao extends IDomainDao {
	//取得所有分类
   public List getCategories();
}
