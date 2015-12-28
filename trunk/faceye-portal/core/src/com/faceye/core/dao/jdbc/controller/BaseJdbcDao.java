package com.faceye.core.dao.jdbc.controller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.faceye.core.dao.jdbc.iface.IBaseJdbcDao;
import com.faceye.core.util.exception.DBException;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.util.helper.QuerySqlConfig;
import com.faceye.core.util.helper.StringUtil;

public class BaseJdbcDao extends JdbcDaoSupport implements IBaseJdbcDao {
	private QuerySqlConfig querySqlConfig;
	public void removeObject(String sql) {
		// TODO Auto-generated method stub
	  this.getJdbcTemplate().execute(sql);
	}
	public void removeObject(String tablename, Long idValue) {
		// TODO Auto-generated method stub
		this.removeObject(tablename, "id", idValue);
	}

	public void removeObject(String tablename, String idValue) {
		// TODO Auto-generated method stub
		this.removeObject(tablename, "id", idValue);

	}

	public void removeObject(String tablename, String delIdentifer,
			String identiferValue) {
		// TODO Auto-generated method stub
		String sql = StringUtil.createDelSQL(tablename, delIdentifer);
		if (sql == null) {
			throw new IllegalArgumentException(
					"find a Error where create Del SQL,the \"tablename\" or \"identiferValue\" or \"delIdentifer\" is Null,Please Check it");
		}
		Object[] o = new Object[] { identiferValue };
		int[] objectType = new int[] { Types.VARCHAR };
		this.getJdbcTemplate().update(sql, o, objectType);
	}

	public void removeObject(String tablename, String delIdentifer,
			Long identiferValue) {
		// TODO Auto-generated method stub
		String sql = StringUtil.createDelSQL(tablename, delIdentifer);
		if (sql == null) {
			throw new IllegalArgumentException(
					"find a Error where create Del SQL,the \"tablename\" or \"identiferValue\" or \"delIdentifer\" is Null,Please Check it");
		}
		Object[] o = new Object[] { identiferValue };
		int[] objectType = new int[] { Types.NUMERIC };
		this.getJdbcTemplate().update(sql, o, objectType);
	}

	
	private class CustomerRowMapper implements RowMapper {
		/**
		 * �������׼��
		 */
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			ResultSetMetaData rsMetaData = arg0.getMetaData();
			String columnName;
			// int columnType;
			HashMap result = new HashMap();
			for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
				columnName = rsMetaData.getColumnName(i).toLowerCase();
				// columnType=rsMetaData.getColumnType(i);
				result.put(columnName, arg0.getObject(i));
			}
			return result;
		}
	}

	
	private class CustomerMappingSqlQuery extends MappingSqlQuery {

		protected Object mapRow(ResultSet arg0, int arg1)
				throws SQLException {
			ResultSetMetaData rsMetaData = arg0.getMetaData();
			String columnName;
			HashMap result = new HashMap();
			for (int i = 1; i <=rsMetaData.getColumnCount(); i++) {
				columnName = rsMetaData.getColumnName(i);
				result.put(columnName, arg0.getObject(i));
			}
			return result;
		}

	}
	/* (non-Javadoc)
	 * @see com.huasoft.misdframework.dbengine.controller.IJDBCQueryUtils#getAll(java.lang.String)
	 */
	public List getAll(String sql){
		
		return this.getAll(sql, (Object[])null,(int [])null);
	}
	/* (non-Javadoc)
	 * @see com.huasoft.misdframework.dbengine.controller.IJDBCQueryUtils#getAll(java.lang.String, java.lang.Object, int)
	 */
	public List getAll(String sql,Object value,int paramType){
		return this.getAll(sql,new Object [] {value}, new int[] {paramType});
	}
	/* (non-Javadoc)
	 * @see com.huasoft.misdframework.dbengine.controller.IJDBCQueryUtils#getAll(java.lang.String, java.lang.Object[], int[])
	 */
	public List getAll(String sql,Object [] values,int[] paramTypes){
		if (values != null && paramTypes != null
				&& values.length != paramTypes.length) {
		}
	   List items=null;
//		List items=this.getJdbcTemplate().query(sql, values, paramTypes, new SqlRowSetResultSetExtractor(new CustomerRowMapper()));
		return items;
	}
	/* (non-Javadoc)
	 * @see com.huasoft.misdframework.dbengine.controller.IJDBCQueryUtils#getPage(java.lang.String, int)
	 */
	public PaginationSupport getPage(String sql,int startIndex){
		return this.getPage(sql, (Object[])null, (int[]) null, PaginationSupport.PAGESIZE, startIndex);
	}
    /* (non-Javadoc)
	 * @see com.huasoft.misdframework.dbengine.controller.IJDBCQueryUtils#getPage(java.lang.String, int, int)
	 */
	public PaginationSupport getPage(String sql,int pageSize,int startIndex){
		return this.getPage(sql, (Object[])null, (int[]) null, pageSize, startIndex);
	}
	 /* (non-Javadoc)
	 * @see com.huasoft.misdframework.dbengine.controller.IJDBCQueryUtils#getPage(java.lang.String, java.lang.Object, int, int)
	 */
	
	public PaginationSupport getPage(String sql,Object value,int paramType,int startIndex){
		return this.getPage(sql, new Object[]{value}, new int[]{paramType}, PaginationSupport.PAGESIZE, startIndex);
	}
	/* (non-Javadoc)
	 * @see com.huasoft.misdframework.dbengine.controller.IJDBCQueryUtils#getPage(java.lang.String, java.lang.Object, int, int, int)
	 */
	public PaginationSupport getPage(String sql,Object value,int paramType,int pageSize,int startIndex){
		return this.getPage(sql, new Object []{value}, new int []{paramType}, pageSize, startIndex);
	}
	/* (non-Javadoc)
	 * @see com.huasoft.misdframework.dbengine.controller.IJDBCQueryUtils#getPage(java.lang.String, java.lang.Object[], int[], int, int)
	 */
	public PaginationSupport getPage(String sql,final Object[] values,int [] paramTypes ,final int pageSize,
			final int startIndex) {
		if (values != null && paramTypes != null
				&& values.length != paramTypes.length) {
			
		}
        String endSql=StringUtil.createPageSQL(sql, pageSize, startIndex);
		int totalCount = this.getCount(sql,values,paramTypes);
		List items=null;
		items = this.getJdbcTemplate().query(endSql,values,paramTypes,
				new CustomerRowMapper());
		return new PaginationSupport(items,
				totalCount, pageSize, startIndex);
	}
	/* (non-Javadoc)
	 * @see com.huasoft.misdframework.dbengine.controller.IJDBCQueryUtils#getCount(java.lang.String)
	 */
	public int getCount(String sql) {
		return this.getCount(sql, (Object[]) null, (int[]) null);
	}

	/* (non-Javadoc)
	 * @see com.huasoft.misdframework.dbengine.controller.IJDBCQueryUtils#getCount(java.lang.String, java.lang.Object, int)
	 */
	public int getCount(String sql, Object value, int paramType) {
		return this.getCount(sql, new Object[] { value },
				new int[] { paramType });
	}

	/* (non-Javadoc)
	 * @see com.huasoft.misdframework.dbengine.controller.IJDBCQueryUtils#getCount(java.lang.String, java.lang.Object[], int[])
	 */
	public int getCount(String sql, Object[] values, int[] paramTypes) {
		if (values != null && paramTypes != null
				&& values.length != paramTypes.length) {
			
		}
		String countSQL=StringUtil.createCountSQL(sql);
	    int count=this.getJdbcTemplate().queryForInt(countSQL, values, paramTypes);
//		RowCountCallbackHandler countCallback = new RowCountCallbackHandler();
//		this.getJdbcTemplate()
//				.query(countSQL, values, paramTypes, countCallback);
		return count;
	}


	/**
	 * ==========================================================================
	 * ��ݱ������
	 * ===========================================================================
	 */
	public void saveOrUpdate(String sql) {
		getJdbcTemplate().execute(sql);
	}

	public void saveOrUpdate(String sql, Object[] values, int[] valuesTypes) {
		getJdbcTemplate().update(sql, values, valuesTypes);
	}
	
	private String getSQLByQueryName(String queryName){
		return this.getQuerySqlConfig().transPropertiesToMapWhitContainerOfMap().get(queryName).toString();
	}
	public int getCountByNamedQuery(String queryName) throws DBException {
		// TODO Auto-generated method stub
		String sql=this.getSQLByQueryName(queryName);
		return this.getCount(sql);
	}
	public int getCountByNamedQuery(String queryName, Object value, int paramType) throws DBException {
		// TODO Auto-generated method stub
		String sql=this.getSQLByQueryName(queryName);
		return this.getCount(sql, value, paramType);
	}
	public int getCountByNamedQuery(String queryName, Object[] values, int[] paramTypes) throws DBException {
		// TODO Auto-generated method stub
		String sql=this.getSQLByQueryName(queryName);
		return this.getCount(sql, values, paramTypes);
	}
	public PaginationSupport getPageByNamedQuery(String queryName, int startIndex) throws DBException {
		// TODO Auto-generated method stub
		String sql=this.getSQLByQueryName(queryName);
		return this.getPage(sql, startIndex);
	}
	public PaginationSupport getPageByNamedQuery(String queryName, int pageSize, int startIndex) throws DBException {
		// TODO Auto-generated method stub
		String sql=this.getSQLByQueryName(queryName);
		return this.getPage(sql, pageSize, startIndex);
	}
	
	public PaginationSupport getPageByNamedQuery(String queryName, Object value, int paramType, int startIndex) throws DBException {
		// TODO Auto-generated method stub
		String sql=this.getSQLByQueryName(queryName);
		return this.getPage(sql, value, paramType, startIndex);
	}
	public PaginationSupport getPageByNamedQuery(String queryName, Object value, int paramType, int pageSize, int startIndex) throws DBException {
		// TODO Auto-generated method stub
		String sql=this.getSQLByQueryName(queryName);
		return this.getPage(sql, value, paramType, pageSize, startIndex);
	}
	public PaginationSupport getPageByNamedQuery(String queryName, Object[] values, int[] paramTypes, int startIndex) throws DBException {
		// TODO Auto-generated method stub
		String sql=this.getSQLByQueryName(queryName);
		return this.getPage(sql, values, paramTypes,PaginationSupport.PAGESIZE, startIndex);
	}
	public PaginationSupport getPageByNamedQuery(String queryName, Object[] values, int[] paramTypes, int pageSize, int startIndex) throws DBException {
		// TODO Auto-generated method stub
		String sql=this.getSQLByQueryName(queryName);
		return this.getPage(sql, values, paramTypes, pageSize, startIndex);
	}
	public QuerySqlConfig getQuerySqlConfig() {
		return querySqlConfig;
	}
	public void setQuerySqlConfig(QuerySqlConfig querySqlConfig) {
		this.querySqlConfig = querySqlConfig;
	}
	
}
