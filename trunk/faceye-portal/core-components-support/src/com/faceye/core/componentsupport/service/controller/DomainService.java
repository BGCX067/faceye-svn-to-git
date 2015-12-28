package com.faceye.core.componentsupport.service.controller;

import org.hibernate.criterion.DetachedCriteria;

import com.faceye.core.componentsupport.dao.iface.IDomainDao;
import com.faceye.core.componentsupport.service.iface.IDomainService;
import com.faceye.core.service.controller.BaseHibernateService;

public class DomainService extends BaseHibernateService implements IDomainService{
	private IDomainDao domainDao=null;

	public IDomainDao getDomainDao() {
		return domainDao;
	}

	public void setDomainDao(IDomainDao domainDao) {
		this.domainDao = domainDao;
	}

	public String getAllByDetachedCriteria(DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getAllByDetachedCriteria(detachedCriteria);
	}

	public String getAllByDomain(Class clazz) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getAllByDomain(clazz);
	}

	public String getPageByDetachedCriteria(DetachedCriteria detachedCriteria,
			int startIndex) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByDetachedCriteria(detachedCriteria, startIndex);
	}

	public String getPageByDetachedCriteria(DetachedCriteria detachedCriteria,
			int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByDetachedCriteria(detachedCriteria, startIndex, pageSize);
	}

	public String getPageByDomain(Class clazz, int startIndex) {
		// TODO Auto-generated method stub
		return this.getPageByDomain(clazz, startIndex);
	}

	public String getPageByDomain(Class clazz, int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		return this.getPageByDomain(clazz, startIndex, pageSize);
	}

	public String getPageByHql(String hql, int startIndex) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByHql(hql, startIndex);
	}

	public String getPageByHql(String hql, int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByHql(hql, startIndex, pageSize);
	}

	public String getPageByHql(String hql, String param, Object value,
			int startIndex) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByHql(hql,param, value, startIndex);
	}

	public String getPageByHql(String hql, String param, Object value,
			int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByHql(hql, param, value, startIndex, pageSize);
	}

	public String getPageByHql(String hql, String[] params, Object[] values,
			int startIndex) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByHql(hql, params, values, startIndex);
	}

	public String getPageByHql(String hql, String[] params, Object[] values,
			int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByHql(hql, params, values, startIndex, pageSize);
	}

	public String getPageByHql(String hql, Object value, int startIndex) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByHql(hql, value, startIndex);
	}

	public String getPageByHql(String hql, Object value, int startIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByHql(hql, value, startIndex, pageSize);
	}

	public String getPageByHql(String hql, Object[] values, int startIndex) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByHql(hql, values, startIndex);
	}

	public String getPageByHql(String hql, Object[] values, int startIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByHql(hql, values, startIndex, pageSize);
	}

	public String getPageByNamedHql(String queryName, int startIndex) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByNamedHql(queryName, startIndex);
	}

	public String getPageByNamedHql(String queryName, int startIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByNamedHql(queryName, startIndex, pageSize);
	}

	public String getPageByNamedHql(String queryName, String param,
			Object value, int startIndex) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByNamedHql(queryName, param, value, startIndex);
	}

	public String getPageByNamedHql(String queryName, String param,
			Object value, int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByNamedHql(queryName, param, value, startIndex, pageSize);
	}

	public String getPageByNamedHql(String queryName, String[] params,
			Object[] values, int startIndex) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByNamedHql(queryName, params, values, startIndex);
	}

	public String getPageByNamedHql(String queryName, String[] params,
			Object[] values, int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByNamedHql(queryName, params, values, startIndex, pageSize);
	}

	public String getPageByNamedHql(String queryName, Object value,
			int startIndex) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByNamedHql(queryName, value, startIndex);
	}

	public String getPageByNamedHql(String queryName, Object value,
			int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByNamedHql(queryName, value, startIndex, pageSize);
	}

	public String getPageByNamedHql(String queryName, Object[] values,
			int startIndex) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByNamedHql(queryName, values, startIndex);
	}

	public String getPageByNamedHql(String queryName, Object[] values,
			int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		return this.getDomainDao().getPageByNamedHql(queryName, values, startIndex, pageSize);
	}

}
