package com.faceye.core.service.iface;

import java.util.List;

import com.faceye.core.util.exception.DBException;
import com.faceye.core.util.helper.PaginationSupport;

public interface IBaseJdbcQueryService {

	
	public abstract List getAll(String sql);

	
	public abstract List getAll(String sql, Object value, int paramType);

	
	public abstract List getAll(String sql, Object[] values, int[] paramTypes);

	
	public abstract PaginationSupport getPage(String sql, int startIndex);

	
	public abstract PaginationSupport getPage(String sql, int pageSize,
			int startIndex);

	

	public abstract PaginationSupport getPage(String sql, Object value,
			int paramType, int startIndex);

	
	public abstract PaginationSupport getPage(String sql, Object value,
			int paramType, int pageSize, int startIndex);

	
	public abstract PaginationSupport getPage(String sql,
			final Object[] values, int[] paramTypes, final int pageSize,
			final int startIndex);

	
	public abstract int getCount(String sql);

	
	public abstract int getCount(String sql, Object value, int paramType);

	
	public abstract int getCount(String sql, Object[] values, int[] paramTypes);

	public abstract int getCountByNamedQuery(String queryName)
			throws DBException;

	public abstract int getCountByNamedQuery(String queryName, Object value,
			int paramType) throws DBException;

	public abstract int getCountByNamedQuery(String queryName, Object[] values,
			int[] paramTypes) throws DBException;

	public abstract PaginationSupport getPageByNamedQuery(String queryName,
			int startIndex) throws DBException;

	public abstract PaginationSupport getPageByNamedQuery(String queryName,
			int pageSize, int startIndex) throws DBException;

	public abstract PaginationSupport getPageByNamedQuery(String queryName,
			Object value, int paramType, int startIndex) throws DBException;

	public abstract PaginationSupport getPageByNamedQuery(String queryName,
			Object value, int paramType, int pageSize, int startIndex)
			throws DBException;

	public abstract PaginationSupport getPageByNamedQuery(String queryName,
			Object[] values, int[] paramTypes, int startIndex)
			throws DBException;

	public abstract PaginationSupport getPageByNamedQuery(String queryName,
			Object[] values, int[] paramTypes, int pageSize, int startIndex)
			throws DBException;

}