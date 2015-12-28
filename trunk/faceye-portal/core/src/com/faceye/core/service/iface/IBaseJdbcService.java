package com.faceye.core.service.iface;

import java.util.List;

import com.faceye.core.util.exception.DBException;
import com.faceye.core.util.exception.ServiceException;
import com.faceye.core.util.helper.PaginationSupport;

public interface IBaseJdbcService {
	
	public void removeObject(String sql);
   	
	public void removeObject(String tablename, Long idValue);

	
	public void removeObject(String tablename, String idValue);

	
	public void removeObject(String tablename, String delIdentifer,
			String identiferValue);

	
	public void removeObject(String tablename, String delIdentifer,
			Long identiferValue);

	
	public List getAll(String sql);
//	public List getAll(String sql,Object [] paramsValues);
	public List getAll(String sql,Object value,int paramType);
	
	public List getAll(String sql,Object [] values,int[] paramTypes);
	
	public PaginationSupport getPage(String sql,int startIndex);
	
	public PaginationSupport getPage(String sql,int pageSize,int startIndex);
	 
	
	public PaginationSupport getPage(String sql,Object value,int paramType,int startIndex);
	
	public PaginationSupport getPage(String sql,Object value,int paramType,int pageSize,int startIndex);
	
	public PaginationSupport getPage(String sql,final Object[] values,int [] paramTypes ,final int pageSize,
			final int startIndex);
	
	public int getCount(String sql) ;
	
	public int getCount(String sql, Object value, int paramType);
	
	public int getCount(String sql, Object[] values, int[] paramTypes);

	
	public void saveOrUpdate(String sql);

	
	public void saveOrUpdate(String sql, Object[] values, int[] valuesTypes);
	
	
	
	public int getCountByNamedQuery(String queryName) throws DBException;
	
	public int getCountByNamedQuery(String queryName,Object value,int paramType) throws DBException;
	
	public int getCountByNamedQuery(String queryName,Object[] values ,int [] paramTypes) throws DBException;

	public PaginationSupport getPageByNamedQuery(String queryName,int startIndex) throws ServiceException;
	
	public PaginationSupport getPageByNamedQuery(String queryName,int pageSize,int startIndex) throws ServiceException;
	
	public PaginationSupport getPageByNamedQuery(String queryName,Object value,int paramType,int startIndex) throws ServiceException;
	
	public PaginationSupport getPageByNamedQuery(String queryName,Object value,int paramType,int pageSize,int startIndex) throws ServiceException;
	
	public PaginationSupport getPageByNamedQuery(String queryName,Object[] values,int [] paramTypes,int startIndex) throws ServiceException;
	
	public PaginationSupport getPageByNamedQuery(String queryName,Object[] values,int [] paramTypes, int pageSize,int startIndex) throws ServiceException;
}
