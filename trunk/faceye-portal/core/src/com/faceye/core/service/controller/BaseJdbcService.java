package com.faceye.core.service.controller;

import java.util.List;

import com.faceye.core.dao.jdbc.iface.IBaseJdbcDao;
import com.faceye.core.service.iface.IBaseJdbcQueryService;
import com.faceye.core.service.iface.IBaseJdbcService;
import com.faceye.core.util.exception.DBException;
import com.faceye.core.util.exception.ServiceException;
import com.faceye.core.util.helper.PaginationSupport;

public class BaseJdbcService implements IBaseJdbcService,IBaseJdbcQueryService {
	private IBaseJdbcDao baseJdbcDao;

	public void removeObject(String tablename, Long idValue){
		getBaseJdbcDao().removeObject(tablename, idValue);
	}

	
	public void removeObject(String tablename, String idValue){
		getBaseJdbcDao().removeObject(tablename, idValue);
		
	}

	
	public void removeObject(String tablename, String delIdentifer,
			String identiferValue){
				getBaseJdbcDao().removeObject(tablename, delIdentifer,
			identiferValue);
			}

	
	public void removeObject(String tablename, String delIdentifer,
			Long identiferValue){
				getBaseJdbcDao().removeObject(tablename,delIdentifer,
			identiferValue);
			}

	/* (non-Javadoc)
	 * @see com.faceye.core.service.controller.IBaseJdbcQueryService#getAll(java.lang.String)
	 */
	public List getAll(String sql) {
		// TODO Auto-generated method stub
		return this.getAll(sql, (Object[])null, (int[])null);
	}
	/* (non-Javadoc)
	 * @see com.faceye.core.service.controller.IBaseJdbcQueryService#getAll(java.lang.String, java.lang.Object, int)
	 */
	public List getAll(String sql,Object value,int paramType){
		return this.getAll(sql, new Object[]{value}, new int[]{paramType});
	}
	/* (non-Javadoc)
	 * @see com.faceye.core.service.controller.IBaseJdbcQueryService#getAll(java.lang.String, java.lang.Object[], int[])
	 */
	public List getAll(String sql,Object [] values,int[] paramTypes){
		return getBaseJdbcDao().getAll(sql, values, paramTypes);
	}
	/* (non-Javadoc)
	 * @see com.faceye.core.service.controller.IBaseJdbcQueryService#getPage(java.lang.String, int)
	 */
	public PaginationSupport getPage(String sql,int startIndex){
		return this.getPage(sql, (Object[])null, (int[]) null, PaginationSupport.PAGESIZE, startIndex);
	}
    /* (non-Javadoc)
	 * @see com.faceye.core.service.controller.IBaseJdbcQueryService#getPage(java.lang.String, int, int)
	 */
	public PaginationSupport getPage(String sql,int pageSize,int startIndex){
		return this.getPage(sql, (Object[])null, (int[]) null, pageSize, startIndex);
	}
	 /* (non-Javadoc)
	 * @see com.faceye.core.service.controller.IBaseJdbcQueryService#getPage(java.lang.String, java.lang.Object, int, int)
	 */
	
	public PaginationSupport getPage(String sql,Object value,int paramType,int startIndex){
		return this.getPage(sql, new Object[]{value}, new int[]{paramType}, PaginationSupport.PAGESIZE, startIndex);
	}
	/* (non-Javadoc)
	 * @see com.faceye.core.service.controller.IBaseJdbcQueryService#getPage(java.lang.String, java.lang.Object, int, int, int)
	 */
	public PaginationSupport getPage(String sql,Object value,int paramType,int pageSize,int startIndex){
		return this.getPage(sql, new Object []{value}, new int []{paramType}, pageSize, startIndex);
	}
	/* (non-Javadoc)
	 * @see com.faceye.core.service.controller.IBaseJdbcQueryService#getPage(java.lang.String, java.lang.Object[], int[], int, int)
	 */
	public PaginationSupport getPage(String sql,final Object[] values,int [] paramTypes ,final int pageSize,
			final int startIndex) {
		return getBaseJdbcDao().getPage(sql, values, paramTypes, pageSize, startIndex);
		
	}
	/* (non-Javadoc)
	 * @see com.faceye.core.service.controller.IBaseJdbcQueryService#getCount(java.lang.String)
	 */
	public int getCount(String sql) {
		return this.getCount(sql, (Object[]) null, (int[]) null);
	}

	/* (non-Javadoc)
	 * @see com.faceye.core.service.controller.IBaseJdbcQueryService#getCount(java.lang.String, java.lang.Object, int)
	 */
	public int getCount(String sql, Object value, int paramType) {
		return this.getCount(sql, new Object[] { value },
				new int[] { paramType });
	}

	/* (non-Javadoc)
	 * @see com.faceye.core.service.controller.IBaseJdbcQueryService#getCount(java.lang.String, java.lang.Object[], int[])
	 */
	public int getCount(String sql, Object[] values, int[] paramTypes) {
		if (values != null && paramTypes != null
				&& values.length != paramTypes.length) {
			return 0;
		}
		return getBaseJdbcDao().getCount(sql, values, paramTypes);
	}


	
	public void saveOrUpdate(String sql){
		getBaseJdbcDao().saveOrUpdate(sql);
	}

	
	public void saveOrUpdate(String sql, Object[] values, int[] valuesTypes){
		getBaseJdbcDao().saveOrUpdate(sql,values,valuesTypes);
	}
	public void removeObject(String sql) {
		// TODO Auto-generated method stub
		getBaseJdbcDao().removeObject(sql);
	}
	/* (non-Javadoc)
	 * @see com.faceye.core.service.controller.IBaseJdbcQueryService#getCountByNamedQuery(java.lang.String)
	 */
	public int getCountByNamedQuery(String queryName) throws DBException {
		// TODO Auto-generated method stub
		return getBaseJdbcDao().getCountByNamedQuery(queryName);
	}
	/* (non-Javadoc)
	 * @see com.faceye.core.service.controller.IBaseJdbcQueryService#getCountByNamedQuery(java.lang.String, java.lang.Object, int)
	 */
	public int getCountByNamedQuery(String queryName, Object value, int paramType) throws DBException {
		// TODO Auto-generated method stub
		return getBaseJdbcDao().getCountByNamedQuery(queryName, value, paramType);
	}
	/* (non-Javadoc)
	 * @see com.faceye.core.service.controller.IBaseJdbcQueryService#getCountByNamedQuery(java.lang.String, java.lang.Object[], int[])
	 */
	public int getCountByNamedQuery(String queryName, Object[] values, int[] paramTypes) throws DBException {
		// TODO Auto-generated method stub
		return getBaseJdbcDao().getCountByNamedQuery(queryName, values, paramTypes);
	}
	/* (non-Javadoc)
	 * @see com.faceye.core.service.controller.IBaseJdbcQueryService#getPageByNamedQuery(java.lang.String, int)
	 */
	public PaginationSupport getPageByNamedQuery(String queryName, int startIndex) throws DBException {
		// TODO Auto-generated method stub
		return getBaseJdbcDao().getPageByNamedQuery(queryName, startIndex);
	}
	/* (non-Javadoc)
	 * @see com.faceye.core.service.controller.IBaseJdbcQueryService#getPageByNamedQuery(java.lang.String, int, int)
	 */
	public PaginationSupport getPageByNamedQuery(String queryName, int pageSize, int startIndex) throws DBException {
		// TODO Auto-generated method stub
		return getBaseJdbcDao().getPageByNamedQuery(queryName, pageSize, startIndex);
	}
	/* (non-Javadoc)
	 * @see com.faceye.core.service.controller.IBaseJdbcQueryService#getPageByNamedQuery(java.lang.String, java.lang.Object, int, int)
	 */
	public PaginationSupport getPageByNamedQuery(String queryName, Object value, int paramType, int startIndex) throws DBException {
		// TODO Auto-generated method stub
		return getBaseJdbcDao().getPageByNamedQuery(queryName, value, paramType, startIndex);
	}
	/* (non-Javadoc)
	 * @see com.faceye.core.service.controller.IBaseJdbcQueryService#getPageByNamedQuery(java.lang.String, java.lang.Object, int, int, int)
	 */
	public PaginationSupport getPageByNamedQuery(String queryName, Object value, int paramType, int pageSize, int startIndex) throws DBException {
		// TODO Auto-generated method stub
		return getBaseJdbcDao().getPageByNamedQuery(queryName, value, paramType,pageSize, startIndex);
	}
	/* (non-Javadoc)
	 * @see com.faceye.core.service.controller.IBaseJdbcQueryService#getPageByNamedQuery(java.lang.String, java.lang.Object[], int[], int)
	 */
	public PaginationSupport getPageByNamedQuery(String queryName, Object[] values, int[] paramTypes, int startIndex) throws DBException {
		// TODO Auto-generated method stub
		return getBaseJdbcDao().getPageByNamedQuery(queryName, values, paramTypes, startIndex);
	}
	/* (non-Javadoc)
	 * @see com.faceye.core.service.controller.IBaseJdbcQueryService#getPageByNamedQuery(java.lang.String, java.lang.Object[], int[], int, int)
	 */
	public PaginationSupport getPageByNamedQuery(String queryName, Object[] values, int[] paramTypes, int pageSize, int startIndex) throws DBException {
		// TODO Auto-generated method stub
		return getBaseJdbcDao().getPageByNamedQuery(queryName, values, paramTypes, pageSize, startIndex);
	}

	public IBaseJdbcDao getBaseJdbcDao() {
		return baseJdbcDao;
	}

	public void setBaseJdbcDao(IBaseJdbcDao baseJdbcDao) {
		this.baseJdbcDao = baseJdbcDao;
	}
}
