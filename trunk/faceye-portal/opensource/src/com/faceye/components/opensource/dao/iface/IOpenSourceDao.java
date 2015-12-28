package com.faceye.components.opensource.dao.iface;

import java.io.Serializable;

import com.faceye.core.dao.hibernate.iface.IBaseHibernateDao;
import com.faceye.core.util.helper.PaginationSupport;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.opensource.dao.iface.IOpenSourceDao.java
 * @Description:
 */
public interface IOpenSourceDao extends IBaseHibernateDao {
	/**
	 * 取得Opensource
	 * @return
	 */
   public PaginationSupport getOpenSource(int startIndex);
   /**
    * 根据栏目ID加载opensource
    * @param columnId
    * @return
    */
   public PaginationSupport getOpenSource(Serializable columnId,int startIndex);
}
