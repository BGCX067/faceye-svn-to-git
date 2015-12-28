package com.faceye.components.navigation.dao.iface;

import java.io.Serializable;
import java.util.List;

import com.faceye.core.dao.hibernate.iface.IBaseHibernateDao;
import com.faceye.core.util.helper.PaginationSupport;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.navigation.dao.iface.ITraditionDao.java
 * @Description:传统导航DAO
 */
public interface ITraditionDao extends IBaseHibernateDao {
	/**
	 * 根据索引取得导航
	 * @param startIndex
	 * @return
	 */
    public PaginationSupport getTraditions(int startIndex);
    /**
     * 根据栏目取得导航
     * @param columnId
     * @param startIndex
     * @return
     */
    public PaginationSupport getTraditions(Serializable columnId,int startIndex);
    /**
     * 根据分类取得导航
     * @param categoryId
     * @param startIndex
     * @return
     */
	public PaginationSupport getTraditionsByCategory(Serializable categoryId,int startIndex);
	/**
	 * 根据分类取得所有导航
	 * @param categoryId
	 * @return
	 */
	public List getAllTraditionsByCategory(Serializable categoryId);
	/**
	 * 取得所有导航　
	 * @return
	 */
	public List getAllTraditions();
}
