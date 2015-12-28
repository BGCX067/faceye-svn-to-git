package com.faceye.core.dao.hibernate.iface;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.faceye.core.dao.hibernate.model.BaseObject;
import com.faceye.core.util.exception.DBException;
import com.faceye.core.util.helper.PaginationSupport;

/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.core.dao.hibernate.iface.IBaseHibernateDao.java
 * @Description:
 */
public interface IBaseHibernateDao {
	/**
	 * 取得记录总数
	 * 
	 * @param queryName,查询名（HBM配置)
	 * @return 根据查询条件查询得到的记录总数
	 * @throws DBException
	 */
	public int getCount(String queryName) throws DBException;

	/**
	 * 取得记录总数
	 * 
	 * @param queryName,查询名（HBM配置)
	 * @param param,参数名
	 * @param value,参数值
	 * @return返回带查询条件的记录总数
	 * @throws DBException
	 */
	public int getCount(String queryName, String param, Object value)
			throws DBException;

	/**
	 * 返回记录总数
	 * 
	 * @param queryName,查询名（HBM配置)
	 * @param params,参数数组
	 * @param values,参数值数组
	 * @return记录总数int
	 * @throws DBException
	 */
	public int getCount(String queryName, String[] params, Object[] values)
			throws DBException;

	/**
	 * 
	 * @param detachedCriteria数据查询对像
	 * @return记录总数
	 * @throws DBException
	 */
	public int getCount(final DetachedCriteria detachedCriteria)
			throws DBException;

	/**
	 * 
	 * @param queryName,查询名（HBM配置)
	 * @return分页对像（一页数据)
	 * @throws DBException
	 */
	public PaginationSupport getPage(String queryName) throws DBException;

	/**
	 * 
	 * @param queryName,查询名（HBM配置)
	 * @param startIndex,开始索引
	 * @return 分页对像
	 * @throws DBException
	 */

	public PaginationSupport getPage(String queryName, int startIndex)
			throws DBException;

	/**
	 * 
	 * @param queryName,查询名（HBM配置)
	 * @param pageSize,每页显示数据总数
	 * @param startIndex,起始索引
	 * @return 分页对像
	 * @throws DBException
	 */

	public PaginationSupport getPage(String queryName, int pageSize,
			int startIndex) throws DBException;
/**
 * 
 * @param queryName,查询名（HBM配置)
 * @param param,参数名
 * @param value,参数值
 * @return　分页对像
 * @throws DBException
 */
	public PaginationSupport getPage(String queryName, String param,
			Object value) throws DBException;
/**
 * 
 * @param queryName,查询名（HBM配置)
 * @param param,参数名
 * @param value,参数值
 * @param startIndex,起始索引
 * @return 分页对像
 * @throws DBException
 */
	public PaginationSupport getPage(String queryName, String param,
			Object value, int startIndex) throws DBException;
/**
 * 
 * @param queryName,查询名（HBM配置)
 * @param param,参数名
 * @param value,参数值
 * @param pageSize,一页显示的数据总数
 * @param startIndex,起始索引
 * @return　分页对像
 * @throws DBException
 */
	public PaginationSupport getPage(String queryName, String param,
			Object value, int pageSize, int startIndex) throws DBException;
/**
 * 
 * @param queryName,查询名（HBM配置)
 * @param params,参数名数组
 * @param values,参数值数组
 * @return　分页对像
 * @throws DBException
 */
	public PaginationSupport getPage(String queryName, String[] params,
			Object[] values) throws DBException;
/**
 * 
 * @param queryName,查询名（HBM配置)
 * @param params,参数名数组
 * @param values,参数值数组
 * @param startIndex,起始索引
 * @return　分页对像
 * @throws DBException
 */
	public PaginationSupport getPage(String queryName, String[] params,
			Object[] values, int startIndex) throws DBException;
/**
 * 
 * @param queryName,查询名（HBM配置)
 * @param params,参数名数组
 * @param values,参数值数组
 * @param pageSize,一页显示数据总数
 * @param startIndex,起始索引
 * @return　分页对像
 * @throws DBException
 */
	public PaginationSupport getPage(String queryName, String[] params,
			Object[] values, int pageSize, int startIndex) throws DBException;
/**
 * 
 * @param queryName,查询名（HBM配置)
 * @param value,参数值
 * @return 记录总数(int)
 * @throws DBException
 */
	public int getCont(String queryName, Object value) throws DBException;
/**
 * 
 * @param queryName,查询名（HBM配置)
 * @param values,参数值数组
 * @return　记录总数(int)
 * @throws DBException
 */
	public int getCount(String queryName, Object[] values) throws DBException;
/**
 * 
 * @param queryName,查询名（HBM配置)
 * @param value,参数值
 * @return　分页对像
 * @throws DBException
 */
	public PaginationSupport getPage(String queryName, Object value)
			throws DBException;
/**
 * 
 * @param queryName,查询名（HBM配置)
 * @param values,参数值列表
 * @return 分页对像
 * @throws DBException
 */
	public PaginationSupport getPage(String queryName, Object[] values)
			throws DBException;
/**
 * 
 * @param queryName,查询名（HBM配置)
 * @param value,参数值
 * @param startIndex,起始索引
 * @return　分页对像
 * @throws DBException
 */
	public PaginationSupport getPage(String queryName, Object value,
			int startIndex) throws DBException;
/**
 * 
 * @param queryName,查询名（HBM配置)
 * @param value,参数值
 * @param pageSize,一页显示的数据总数
 * @param startIndex,起始索引
 * @return　分页对像
 * @throws DBException
 */
	public PaginationSupport getPage(String queryName, Object value,
			int pageSize, int startIndex) throws DBException;
/**
 * 
 * @param queryName,查询名（HBM配置)
 * @param values,参数值数组
 * @param startIndex,起始索引
 * @return　分页对像
 * @throws DBException
 */
	public PaginationSupport getPage(String queryName, Object[] values,
			int startIndex) throws DBException;
/**
 * 
 * @param queryName,查询名（HBM配置)
 * @param values,参数值数组
 * @param pageSize,一页显示的数据总数
 * @param startIndex,起始索引
 * @return　分页对像
 * @throws DBException
 */
	public PaginationSupport getPage(String queryName, Object[] values,
			int pageSize, int startIndex) throws DBException;
/**
 * 
 * @param detachedCriteria,数据查询对像
 * @return　分页对像
 * @throws DBException
 */
	public PaginationSupport getPage(final DetachedCriteria detachedCriteria)
			throws DBException;
/**
 * 
 * @param detachedCriteria,数据查询对像
 * @param startIndex　,起始索引
 * @return　　分页对像
 * @throws DBException
 */
	public PaginationSupport getPage(final DetachedCriteria detachedCriteria,
			final int startIndex) throws DBException;
/**
 * 
 * @param detachedCriteria,数据查询对像
 * @param pageSize,一页显示的数据总数
 * @param startIndex,起始索引
 * @return　分页对像
 * @throws DBException
 */
	public PaginationSupport getPage(final DetachedCriteria detachedCriteria,
			final int pageSize, final int startIndex) throws DBException;
	/**
	 * 根据数据查询对像取得所有数据
	 * @param detachedCriteria,数据查询对像
	 * @return
	 */
	public List getAll(final DetachedCriteria detachedCriteria);
/**
 * 取得所有数据
 * @param queryName,查询名（HBM配置)
 * @return 数据列表
 * @throws DBException
 */
	public List getAll(String queryName) throws DBException;
/**
 * 取得所有数据
 * @param queryName,查询名（HBM配置)
 * @param param,参数名
 * @param value,参数值
 * @return
 * @throws DBException
 */
	public List getAll(String queryName, String param, Object value)
			throws DBException;
/**
 * 取得所有数据
 * @param queryName,查询名（HBM配置)
 * @param params,参数名数组
 * @param values,参数值数组
 * @return 
 * @throws DBException
 */
	public List getAll(String queryName, String[] params, Object[] values)
			throws DBException;
/**
 * 取得所有数据
 * @param queryName,查询名（HBM配置)
 * @param values,参数值数组
 * @return
 * @throws DBException
 */
	public List getAll(String queryName, Object[] values) throws DBException;
	/**
	 * 取得所有数据
	 * @param queryName,查询名（HBM配置)
	 * @param value,参数值
	 * @return
	 * @throws DBException
	 */
	public List getAll(String queryName, Object value) throws DBException;
/**
 * 取得所有数据
 * @param entityClass,实体类
 * @return
 * @throws DBException
 */
	public List loadAllObjects(Class entityClass) throws DBException;
/**
 * 保存数据
 * @param entity,实体
 * @throws DBException
 */
	public void saveObject(Object entity) throws DBException;
/**
 * 保存数据（新增或更新)
 * @param entity,实体
 * @throws DBException
 */
	public void saveOrUpdateObject(Object entity) throws DBException;
/**
 * 保存数据（更新）
 * @param entity,实体
 * @throws DBException
 */
	public void updateObject(Object entity) throws DBException;
/**
 * 保存数据（用于多对像)
 * @param entity
 * @throws DBException
 */
	public void mergeObject(Object entity) throws DBException;
/**
 * 保存数据
 * @param entity
 * @throws DBException
 */
	public void persist(Object entity) throws DBException;
/**
 * 清除数据
 * @param entity
 * @throws DBException
 */
	public void removeObject(Object entity) throws DBException;
/**
 * 根据ID,实体类清除数据
 * @param entityClass
 * @param id
 * @throws DBException
 */
	public void removeObject(Class entityClass, Serializable id)
			throws DBException;
/**
 * 清除传入的所有数据
 * @param entities
 * @throws DBException
 */
	public void removeAllObjects(Collection entities) throws DBException;
/**
 * 加载对像
 * @param entityName
 * @param id
 * @return
 * @throws DBException
 */
	public Object loadObject(String entityName, Serializable id)
			throws DBException;
/**
 * 加载数据
 * @param entityName
 * @param id
 * @param lockMode
 * @return
 * @throws DBException
 */
	public Object loadObject(String entityName, Serializable id,
			LockMode lockMode) throws DBException;
/**
 * 加载数据
 * @param entity
 * @param id
 * @throws DBException
 */
	public void loadObject(Object entity, Serializable id) throws DBException;
/**
 * 加载数据
 * @param entityClass
 * @param id
 * @return
 * @throws DBException
 */
	public Object loadObject(Class entityClass, Serializable id)
			throws DBException;
/**
 * 加载数据
 * @param entityName
 * @param id
 * @param lockMode
 * @return
 * @throws DBException
 */
	public Object getObject(String entityName, Serializable id,
			LockMode lockMode) throws DBException;
/**
 *  加载数据
 * @param entityClass
 * @param id
 * @param lockMode
 * @return
 * @throws DBException
 */
	public Object getObject(Class entityClass, Serializable id,
			LockMode lockMode) throws DBException;
/**
 * 加载数据
 * @param entityName
 * @param id
 * @return
 * @throws DBException
 */
	public Object getObject(String entityName, Serializable id)
			throws DBException;
/**
 * 加载数据
 * @param entityClass
 * @param id
 * @return
 * @throws DBException
 */
	public Object getObject(Class entityClass, Serializable id)
			throws DBException;
/**
 * 加载所有数据
 * @param hql
 * @return
 * @throws DBException
 */
	public List getAllByHQL(String hql) throws DBException;
/**
 * 加载所有数据
 * @param hql
 * @param value
 * @return
 * @throws DBException
 */
	public List getAllByHQL(String hql, Object value) throws DBException;
/**
 * 加载所有数据
 * @param hql
 * @param values
 * @return
 * @throws DBException
 */
	public List getAllByHQL(String hql, Object[] values) throws DBException;
/**
 * 加载所有数据
 * @param hql
 * @param param
 * @param value
 * @return
 * @throws DBException
 */
	public List getAllByHQL(String hql, String param, Object value)
			throws DBException;
/**
 * 加载所有数据　
 * @param hql
 * @param params
 * @param values
 * @return
 * @throws DBException
 */
	public List getAllByHQL(String hql, String[] params, Object[] values)
			throws DBException;
/**
 * 加载所有数据
 * @param hql
 * @return
 * @throws DBException
 */
	public PaginationSupport getPageByHQL(String hql) throws DBException;
/**
 * 加载所有数据
 * @param hql
 * @param startIndex
 * @return
 * @throws DBException
 */
	public PaginationSupport getPageByHQL(String hql, int startIndex)
			throws DBException;
	public PaginationSupport getPageByHQL(String hql, int pageSize,
			int startIndex) throws DBException;

	public PaginationSupport getPageByHQL(String hql, Object value)
			throws DBException;

	public PaginationSupport getPageByHQL(String hql, Object value,
			int startIndex) throws DBException;

	public PaginationSupport getPageByHQL(String hql, Object value,
			int pageSize, int startIndex) throws DBException;

	public PaginationSupport getPageByHQL(String hql, Object[] values,
			int startIndex) throws DBException;

	public PaginationSupport getPageByHQL(final String hql,
			final Object[] values, final int pageSize, final int startIndex)
			throws DBException;

	public PaginationSupport getPageByHQL(String hql, String param, Object value)
			throws DBException;

	public PaginationSupport getPageByHQL(String hql, String param,
			Object value, int startIndex) throws DBException;

	public PaginationSupport getPageByHQL(String hql, String param,
			Object value, int pageSize, int startIndex) throws DBException;

	public PaginationSupport getPageByHQL(String hql, String[] params,
			Object[] values) throws DBException;

	public PaginationSupport getPageByHQL(String hql, String[] params,
			Object[] values, int startIndex) throws DBException;

	public PaginationSupport getPageByHQL(final String hql,
			final String[] params, final Object[] values, final int pageSize,
			final int startIndex) throws DBException;

	public int getCountByHQL(String hql) throws DBException;

	public int getCountByHQL(String hql, Object value) throws DBException;

	public int getCountByHQL(String hql, Object[] values) throws DBException;

	public int getCountByHQL(String hql, String param, Object value)
			throws DBException;

	public int getCountByHQL(String hql, String[] params, Object[] values)
			throws DBException;

	public Session getCurrentSession() throws DBException;

	public void closeSession(Session session) throws DBException;

	public Connection getConnection() throws DBException;

	public void closeConnection(Connection conn) throws SQLException;

	public HibernateTemplate getSpringHibernateTemplate();

	public boolean isNotUnique(Object entity, String names);

	public Object getObject(Class classz, String propertyName,
			Object propertyValue);
}
