package com.faceye.core.dao.iface;

import com.faceye.core.dao.hibernate.iface.IBaseHibernateDao;
import com.faceye.core.dao.jdbc.iface.IBaseJdbcDao;

public interface IBaseDaoSupport {

	public IBaseHibernateDao getBaseDao();
  
	public IBaseJdbcDao getBaseJdbcDao();
}
