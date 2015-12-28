package com.faceye.core.componentsupport.service.iface;

import org.hibernate.criterion.DetachedCriteria;

import com.faceye.core.service.iface.IBaseHibernateService;

public interface IDomainService extends IBaseHibernateService {
	/**
	 * 根据实体取得分页数据
	 * @param clazz
	 * @param startIndex
	 * @return
	 */
	public String getPageByDomain(Class clazz,int startIndex);
	public String getPageByDomain(Class clazz,int startIndex,int pageSize);
	public String getAllByDomain(Class clazz);
	/**
	 * 根据DetachedCriteria查询数据
	 * @param detachedCriteria
	 * @param startIndex
	 * @return
	 */
	public String getPageByDetachedCriteria(DetachedCriteria detachedCriteria,int startIndex);
	public String getPageByDetachedCriteria(DetachedCriteria detachedCriteria,int startIndex,int pageSize);
	public String getAllByDetachedCriteria(DetachedCriteria detachedCriteria);
	/**
	 * 根据HQL名进行查询.
	 * @param queryName
	 * @param startIndex
	 * @return
	 */
	public String getPageByNamedHql(String queryName,int startIndex);
	public String getPageByNamedHql(String queryName,int startIndex,int pageSize);
	public String getPageByNamedHql(String queryName,String param,Object value,int startIndex);
	public String getPageByNamedHql(String queryName,String param,Object value,int startIndex,int pageSize);
	public String getPageByNamedHql(String queryName,String[] params,Object []values,int startIndex);
	public String getPageByNamedHql(String queryName,String [] params,Object[]values,int startIndex,int pageSize);
	public String getPageByNamedHql(String queryName,Object value,int startIndex);
	public String getPageByNamedHql(String queryName,Object value,int startIndex,int pageSize);
	public String getPageByNamedHql(String queryName,Object[] values,int startIndex);
	public String getPageByNamedHql(String queryName,Object[] values ,int startIndex,int pageSize);
	
	/**
	 * 根据HQL进行查询
	 */
	public String getPageByHql(String hql,int startIndex);
	public String getPageByHql(String hql,int startIndex,int pageSize);
	public String getPageByHql(String hql,String param,Object value,int startIndex);
	public String getPageByHql(String hql,String param,Object value,int startIndex,int pageSize);
	public String getPageByHql(String hql,String[] params,Object []values,int startIndex);
	public String getPageByHql(String hql,String [] params,Object[]values,int startIndex,int pageSize);
	public String getPageByHql(String hql,Object value,int startIndex);
	public String getPageByHql(String hql,Object value,int startIndex,int pageSize);
	public String getPageByHql(String hql,Object[] values,int startIndex);
	public String getPageByHql(String hql,Object[] values ,int startIndex,int pageSize);
	
}
