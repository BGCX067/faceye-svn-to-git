package com.faceye.core.dao.controller;
import com.faceye.core.dao.hibernate.iface.IBaseHibernateDao;
import com.faceye.core.dao.iface.IBaseDaoSupport;
import com.faceye.core.dao.jdbc.iface.IBaseJdbcDao;

/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.core.dao.controller.BaseDaoSupport.java
 * @Description:
 */
public class BaseDaoSupport implements IBaseDaoSupport{
   private IBaseHibernateDao baseDao;
   private IBaseJdbcDao baseJdbcDao;

public IBaseHibernateDao getBaseDao() {
	return baseDao;
}
public void setBaseDao(IBaseHibernateDao baseDao) {
	this.baseDao = baseDao;
}
public IBaseJdbcDao getBaseJdbcDao() {
	return baseJdbcDao;
}
public void setBaseJdbcDao(IBaseJdbcDao baseJdbcDao) {
	this.baseJdbcDao = baseJdbcDao;
}
}
