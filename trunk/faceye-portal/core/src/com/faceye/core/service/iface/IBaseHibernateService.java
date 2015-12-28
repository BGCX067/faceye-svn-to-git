package com.faceye.core.service.iface;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.faceye.core.dao.hibernate.iface.IBaseHibernateDao;
import com.faceye.core.dao.hibernate.model.BaseObject;
import com.faceye.core.util.exception.ServiceException;
import com.faceye.core.util.helper.PaginationSupport;

public interface IBaseHibernateService {
 
	public IBaseJdbcQueryService getBaseJdbcQueryService();

	public int getCount(DetachedCriteria detachedCriteria)
			throws ServiceException;


	public int getCount(String queryName) throws ServiceException;

	
	public int getCount(String queryName, String param, Object value)
			throws ServiceException;

	
	public int getCount(String queryName, String[] params, Object[] values)
			throws ServiceException;

	
	public PaginationSupport getPage(String queryName, String[] params,
			Object[] values, int startIndex) throws ServiceException;

	
	public PaginationSupport getPage(String queryName, String param,
			Object value, int startIndex) throws ServiceException;

	
	public PaginationSupport getPage(String queryName, int startIndex)
			throws ServiceException;

	
	public PaginationSupport getPage(String queryName, int startIndex,
			int pageSize) throws ServiceException;

	
	public PaginationSupport getPage(String queryName, String param,
			Object value, int pageSize, int startIndex) throws ServiceException;

	
	public PaginationSupport getPage(String queryName, String[] params,
			Object[] values, int pageSize, int startIndex)
			throws ServiceException;

	
	public PaginationSupport getPage(String queryName, String param,
			Object value) throws ServiceException;

	
	public PaginationSupport getPage(String queryName, String[] params,
			Object[] values) throws ServiceException;

	
	public PaginationSupport getPage(String queryName) throws ServiceException;

	
	public int getCont(String queryName, Object value) throws ServiceException;

	
	public int getCount(String queryName, Object[] values)
			throws ServiceException;

	
	public PaginationSupport getPage(String queryName, Object value)
			throws ServiceException;

	
	public PaginationSupport getPage(String queryName, Object[] values)
			throws ServiceException;

	
	public PaginationSupport getPage(String queryName, Object value,
			int startIndex) throws ServiceException;

	
	public PaginationSupport getPage(String queryName, Object value,
			int pageSize, int startIndex) throws ServiceException;

	
	public PaginationSupport getPage(String queryName, Object[] values,
			int startIndex) throws ServiceException;

	
	public PaginationSupport getPage(String queryName, Object[] values,
			int pageSize, int startIndex) throws ServiceException;

	
	public PaginationSupport getPage(final DetachedCriteria detachedCriteria)
			throws ServiceException;

	
	public PaginationSupport getPage(final DetachedCriteria detachedCriteria,
			final int startIndex) throws ServiceException;

	
	public PaginationSupport getPage(final DetachedCriteria detachedCriteria,
			final int pageSize, final int startIndex) throws ServiceException;

	
	public List getAll(final DetachedCriteria detachedCriteria)
			throws ServiceException;

	
	public List getAll(String queryName) throws ServiceException;

	
	public List getAll(String queryName, String param, Object value)
			throws ServiceException;

	
	public List getAll(String queryName, String[] params, Object[] values)
			throws ServiceException;

	
	public List getAll(String queryName, Object[] values)
			throws ServiceException;

	
	public List getAll(String queryName, Object value) throws ServiceException;

	
	public List loadAllObjects(Class entityClass) throws ServiceException;

	
	public void saveOrUpdateObject(Object o) throws ServiceException;

	
	public void removeObject(Class classz, Serializable id) throws ServiceException;

	
	public void removeObject(Object entity) throws ServiceException;

	
	public void removeObjects(Collection entites) throws ServiceException;

	

	
	public void removeMultiObjects(Class classz, Serializable[] id)
			throws ServiceException;

	

	
	public Object getObject(Class classz, Serializable id) throws ServiceException;



	
	public void saveOrUpdateObjects(Object[] os) throws ServiceException;

	
	public void saveObject(Object entity) throws ServiceException;

	public void updateObject(Object entity) throws ServiceException;

	
	public void mergeObject(Object entity) throws ServiceException;

	
	public void persist(Object entity) throws ServiceException;

	
	public Object loadObject(String entityName, Serializable id)
			throws ServiceException;

	
	public Object loadObject(String entityName, Serializable id,
			LockMode lockMode) throws ServiceException;

	
	public void loadObject(Object entity, Serializable id)
			throws ServiceException;

	
	public Object loadObject(Class entityClass, Serializable id)
			throws ServiceException;

	
	public Object getObject(String entityName, Serializable id,
			LockMode lockMode) throws ServiceException;

	public Object getObject(Class entityClass, Serializable id,
			LockMode lockMode) throws ServiceException;

	public Object getObject(String entityName, Serializable id)
			throws ServiceException;



	
	public Session getCurrentSession() throws ServiceException;

	
	public void closeCurrentSession(Session session) throws ServiceException;

	public HibernateTemplate getSpringHibernateTemplate();

	
	

	
	public List getAllByHQL(String hql) throws ServiceException;

	
	public List getAllByHQL(String hql, Object value) throws ServiceException;

	
	public List getAllByHQL(String hql, Object[] values)
			throws ServiceException;

	

	public List getAllByHQL(String hql, String param, Object value)
			throws ServiceException;

	

	public List getAllByHQL(String hql, String[] params, Object[] values)
			throws ServiceException;

	
	public PaginationSupport getPageByHQL(String hql) throws ServiceException;

	
	public PaginationSupport getPageByHQL(String hql, int startIndex)
			throws ServiceException;

	
	public PaginationSupport getPageByHQL(String hql, int pageSize,
			int startIndex) throws ServiceException;

	
	public PaginationSupport getPageByHQL(String hql, Object value)
			throws ServiceException;

	
	public PaginationSupport getPageByHQL(String hql, Object value,
			int startIndex) throws ServiceException;

	
	public PaginationSupport getPageByHQL(String hql, Object value,
			int pageSize, int startIndex) throws ServiceException;

	
	public PaginationSupport getPageByHQL(String hql, Object[] values,
			int startIndex) throws ServiceException;

	
	public PaginationSupport getPageByHQL(final String hql,
			final Object[] values, final int pageSize, final int startIndex)
			throws ServiceException;

	
	public PaginationSupport getPageByHQL(String hql, String param, Object value)
			throws ServiceException;

	

	public PaginationSupport getPageByHQL(String hql, String param,
			Object value, int startIndex) throws ServiceException;

	
	public PaginationSupport getPageByHQL(String hql, String param,
			Object value, int pageSize, int startIndex) throws ServiceException;

	
	public PaginationSupport getPageByHQL(String hql, String[] params,
			Object[] values) throws ServiceException;

	
	public PaginationSupport getPageByHQL(String hql, String[] params,
			Object[] values, int startIndex) throws ServiceException;

	
	public PaginationSupport getPageByHQL(final String hql,
			final String[] params, final Object[] values, final int pageSize,
			final int startIndex) throws ServiceException;

	public int getCountByHQL(String hql) throws ServiceException;

	public int getCountByHQL(String hql, Object value) throws ServiceException;

	public int getCountByHQL(String hql, Object[] values)
			throws ServiceException;

	public int getCountByHQL(String hql, String param, Object value)
			throws ServiceException;

	public int getCountByHQL(String hql, String[] params, Object[] values)
			throws ServiceException;



	public boolean isNotUnique(Object entity, String names);
	
	
	
	public Criteria createCriteria(Class entityClass,Criterion... criterions);
	public Criteria createCirteria(Class entityClass,String orderBy,boolean isAsc,Criterion... criterions);
	
	public Object getObject(Class classz, String propertyName,
			Object propertyValue);
}
