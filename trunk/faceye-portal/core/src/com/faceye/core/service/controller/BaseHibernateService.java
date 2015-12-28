package com.faceye.core.service.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.faceye.core.dao.hibernate.iface.IBaseHibernateDao;
import com.faceye.core.dao.hibernate.model.BaseObject;
import com.faceye.core.service.iface.IBaseHibernateService;
import com.faceye.core.service.iface.IBaseJdbcQueryService;
import com.faceye.core.util.exception.ServiceException;
import com.faceye.core.util.helper.PaginationSupport;

/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.core.service.controller.BaseHibernateService.java
 * @Description:基础服务类
 */
public class BaseHibernateService implements IBaseHibernateService {
	private IBaseHibernateDao baseDao;

	private IBaseJdbcQueryService baseJdbcQueryService;

	protected final Log log = LogFactory.getLog(getClass());

	public IBaseHibernateDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseHibernateDao baseDao) {
		this.baseDao = baseDao;
	}

	// public IDAOUtils getDAO() {
	// // TODO Auto-generated method stub
	// return DAO;
	// }
	public PaginationSupport getPage(String queryName, String[] params,
			Object[] values, int startIndex) throws ServiceException {
		// TODO Auto-generated method stub

		return getBaseDao().getPage(queryName, params, values,
				startIndex);

	}

	public PaginationSupport getPage(String queryName, String param,
			Object value, int startIndex) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPage(queryName, param, value,
				startIndex);
	}

	public PaginationSupport getPage(String queryName, int startIndex)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPage(queryName, startIndex);
	}

	public Object  getObject(Class classz, Serializable id)
			throws ServiceException {
		// TODO Auto-generated method stub
		return  getBaseDao().getObject(classz, id);
	}

	public void removeObject(Class classz, Serializable id)
			throws ServiceException {
		// TODO Auto-generated method stub
		getBaseDao().removeObject(classz, id);

	}

	public void removeMultiObjects(Class classz, Serializable[] id)
			throws ServiceException {
		if (id != null) {
			for (int i = 0; i < id.length; i++) {
				if (id[i] != null) {
					this.removeObject(classz, id[i]);
				}
			}
		}
	}

	public void saveOrUpdateObject(Object o) throws ServiceException {
		// TODO Auto-generated method stub
		getBaseDao().saveOrUpdateObject(o);

	}

	public PaginationSupport getPage(String queryName, String param,
			Object value) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPage(queryName, param, value);
	}

	public PaginationSupport getPage(String queryName, String[] params,
			Object[] values) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPage(queryName, params, values);
	}

	public PaginationSupport getPage(String queryName) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPage(queryName);
	}

	public void saveOrUpdateObjects(Object[] os) throws ServiceException {
		// TODO Auto-generated method stub
		if (os != null) {
			for (int i = 0; i < os.length; i++) {
				if (os[i] != null) {
					this.saveOrUpdateObject(os[i]);
				}
			}
		}
	}

	public PaginationSupport getPage(String queryName, int pageSize,
			int startIndex) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPage(queryName, pageSize, startIndex);
	}

	public int getCont(String queryName, Object value) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getCont(queryName, value);
	}

	public int getCount(String queryName, Object[] values)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getCount(queryName, values);
	}

	public PaginationSupport getPage(String queryName, Object value)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPage(queryName, value);
	}

	public PaginationSupport getPage(String queryName, Object[] values)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPage(queryName, values);
	}

	public PaginationSupport getPage(String queryName, Object value,
			int startIndex) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPage(queryName, value, startIndex);
	}

	public PaginationSupport getPage(String queryName, Object value,
			int pageSize, int startIndex) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPage(queryName, value, pageSize,
				startIndex);
	}

	public PaginationSupport getPage(String queryName, Object[] values,
			int startIndex) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPage(queryName, values, startIndex);
	}

	public PaginationSupport getPage(String queryName, Object[] values,
			int pageSize, int startIndex) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPage(queryName, values, pageSize,
				startIndex);
	}

	public List getAll(DetachedCriteria detachedCriteria)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getAll(detachedCriteria);
	}

	public List getAll(String queryName) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getAll(queryName);
	}

	public List getAll(String queryName, String param, Object value)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getAll(queryName, param, value);
	}

	public List getAll(String queryName, String[] params, Object[] values)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getAll(queryName, params, values);
	}

	public List getAll(String queryName, Object[] values)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getAll(queryName, values);
	}

	public List getAll(String queryName, Object value) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getAll(queryName, value);
	}

	public int getCount(DetachedCriteria detachedCriteria)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getCount(detachedCriteria);
	}

	public int getCount(String queryName) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getCount(queryName);
	}

	public int getCount(String queryName, String param, Object value)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getCount(queryName, param, value);
	}

	public int getCount(String queryName, String[] params, Object[] values)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getCount(queryName, params, values);
	}

	public Session getCurrentSession() throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getCurrentSession();
	}

	public Object getObject(String entityName, Serializable id,
			LockMode lockMode) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getObject(entityName, id, lockMode);
	}

	public Object getObject(Class entityClass, Serializable id,
			LockMode lockMode) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getObject(entityClass, id, lockMode);
	}

	public Object getObject(String entityName, Serializable id) {
		// TODO Auto-generated method stub
		return getBaseDao().getObject(entityName, id);
	}

	public PaginationSupport getPage(String queryName, String param,
			Object value, int pageSize, int startIndex) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPage(queryName, param, value, pageSize,
				startIndex);
	}

	public PaginationSupport getPage(String queryName, String[] params,
			Object[] values, int pageSize, int startIndex)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPage(queryName, params, values,
				pageSize, startIndex);
	}

	public PaginationSupport getPage(DetachedCriteria detachedCriteria)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPage(detachedCriteria);
	}

	public PaginationSupport getPage(DetachedCriteria detachedCriteria,
			int startIndex) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPage(detachedCriteria, startIndex);
	}

	public PaginationSupport getPage(DetachedCriteria detachedCriteria,
			int pageSize, int startIndex) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPage(detachedCriteria, pageSize,
				startIndex);
	}

	public List loadAllObjects(Class entityClass) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().loadAllObjects(entityClass);
	}

	public Object loadObject(String entityName, Serializable id)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().loadObject(entityName, id);
	}

	public Object loadObject(String entityName, Serializable id,
			LockMode lockMode) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().loadObject(entityName, id, lockMode);
	}

	public void loadObject(Object entity, Serializable id)
			throws ServiceException {
		// TODO Auto-generated method stub
		getBaseDao().loadObject(entity, id);
	}

	public Object loadObject(Class entityClass, Serializable id)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().loadObject(entityClass, id);
		// if(o==""){
		// throw new ServiceException("Service E");
		// }
		// return o;
	}

	public void mergeObject(Object entity) throws ServiceException {
		// TODO Auto-generated method stub
		getBaseDao().mergeObject(entity);
	}

	public void persist(Object entity) throws ServiceException {
		// TODO Auto-generated method stub
		getBaseDao().persist(entity);

	}

	public void saveObject(Object entity) throws ServiceException {
		// TODO Auto-generated method stub
		getBaseDao().saveObject(entity);
	}

	public void updateObject(Object entity) throws ServiceException {
		// TODO Auto-generated method stub
		getBaseDao().updateObject(entity);
	}

	
	public PaginationSupport getPage(String queryName, String tableid)
			throws ServiceException {
		return getBaseDao().getPage(queryName, tableid);
	}

	public void closeCurrentSession(Session session) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			if (session != null) {
				this.getBaseDao().getSpringHibernateTemplate()
						.getSessionFactory().close();
			}
		} catch (Exception e) {
			if (session != null) {
				session.clear();
				session.close();
			}
		} finally {
			if (session != null) {
				session.clear();
				session.close();
			}
		}
	}

	/**
	 * ---------------------------------------------------------------------------------------------------
	 * JDBC Template Controller
	 * ---------------------------------------------------------------------------------------------------
	 */

	public void removeObject(Object entity) throws ServiceException {
		// TODO Auto-generated method stub
		this.getBaseDao().removeObject(entity);

	}

	public void removeObjects(Collection entites) throws ServiceException {
		// TODO Auto-generated method stub
		this.getBaseDao().removeAllObjects(entites);
	}

	
	public List getAllByHQL(String hql) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getAllByHQL(hql);
	}

	public List getAllByHQL(String hql, Object value) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getAllByHQL(hql, value);
	}

	public List getAllByHQL(String hql, Object[] values)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getAllByHQL(hql, values);
	}

	public List getAllByHQL(String hql, String param, Object value)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getAllByHQL(hql, param, value);
	}

	public List getAllByHQL(String hql, String[] params, Object[] values)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getAllByHQL(hql, params, values);
	}

	public int getCountByHQL(String hql) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getCountByHQL(hql);
	}

	public int getCountByHQL(String hql, Object value) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getCountByHQL(hql, value);
	}

	public int getCountByHQL(String hql, Object[] values)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getCountByHQL(hql, values);
	}

	public int getCountByHQL(String hql, String param, Object value)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getCountByHQL(hql, param, value);
	}

	public int getCountByHQL(String hql, String[] params, Object[] values)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getCountByHQL(hql, params, values);
	}

	public PaginationSupport getPageByHQL(String hql) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPageByHQL(hql);
	}

	public PaginationSupport getPageByHQL(String hql, int startIndex)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPageByHQL(hql, startIndex);
	}

	public PaginationSupport getPageByHQL(String hql, int pageSize,
			int startIndex) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPageByHQL(hql, pageSize, startIndex);
	}

	public PaginationSupport getPageByHQL(String hql, Object value)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPageByHQL(hql, value);
	}

	public PaginationSupport getPageByHQL(String hql, Object value,
			int startIndex) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPageByHQL(hql, value, startIndex);
	}

	public PaginationSupport getPageByHQL(String hql, Object value,
			int pageSize, int startIndex) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPageByHQL(hql, value, pageSize,
				startIndex);
	}

	public PaginationSupport getPageByHQL(String hql, Object[] values,
			int startIndex) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPageByHQL(hql, values, startIndex);
	}

	public PaginationSupport getPageByHQL(String hql, Object[] values,
			int pageSize, int startIndex) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPageByHQL(hql, values, pageSize,
				startIndex);
	}

	public PaginationSupport getPageByHQL(String hql, String param, Object value)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPageByHQL(hql, param, value);
	}

	public PaginationSupport getPageByHQL(String hql, String param,
			Object value, int startIndex) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao()
				.getPageByHQL(hql, param, value, startIndex);
	}

	public PaginationSupport getPageByHQL(String hql, String param,
			Object value, int pageSize, int startIndex) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPageByHQL(hql, param, value, pageSize,
				startIndex);
	}

	public PaginationSupport getPageByHQL(String hql, String[] params,
			Object[] values) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPageByHQL(hql, params, values);
	}

	public PaginationSupport getPageByHQL(String hql, String[] params,
			Object[] values, int startIndex) throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPageByHQL(hql, params, values,
				startIndex);
	}

	public PaginationSupport getPageByHQL(String hql, String[] params,
			Object[] values, int pageSize, int startIndex)
			throws ServiceException {
		// TODO Auto-generated method stub
		return getBaseDao().getPageByHQL(hql, params, values,
				pageSize, startIndex);
	}

	public HibernateTemplate getSpringHibernateTemplate() {
		// TODO Auto-generated method stub
		return getBaseDao().getSpringHibernateTemplate();
	}

	public boolean isNotUnique(Object entity, String names) {
		// TODO Auto-generated method stub
		boolean result = getBaseDao().isNotUnique(entity, names);
		return result;
	}

	public IBaseJdbcQueryService getBaseJdbcQueryService() {
		// TODO Auto-generated method stub
		return baseJdbcQueryService;
	}

	public void setBaseJdbcQueryService(
			IBaseJdbcQueryService baseJdbcQueryService) {
		this.baseJdbcQueryService = baseJdbcQueryService;
	}

	public Criteria createCriteria(Class entityClass, Criterion... criterions) {
		// TODO Auto-generated method stub
		Criteria criteria = this.getCurrentSession()
				.createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	public Criteria createCirteria(Class entityClass, String orderBy,
			boolean isAsc, Criterion... criterions) {
		// TODO Auto-generated method stub
		Criteria criteria = createCriteria(entityClass, criterions);
		if (isAsc) {
			criteria.addOrder(Order.asc(orderBy));
		} else {
			criteria.addOrder(Order.desc(orderBy));
		}
		return criteria;
	}

	public Object getObject(Class classz, String propertyName,
			Object propertyValue) {
		// TODO Auto-generated method stub
		return this.getBaseDao().getObject(classz, propertyName, propertyValue);
	}
}
