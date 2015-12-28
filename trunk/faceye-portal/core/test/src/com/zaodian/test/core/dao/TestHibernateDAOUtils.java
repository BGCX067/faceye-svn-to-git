package com.faceye.test.core.dao;

//import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.faceye.core.dao.iface.IBaseDaoSupport;
import com.faceye.core.service.security.model.User;

public class TestHibernateDAOUtils extends
		AbstractTransactionalDataSourceSpringContextTests {
	protected final Log logger = LogFactory.getLog(this.getClass());
	protected String[] getConfigLocations() {
		// TODO Auto-generated method stub
		setAutowireMode(AUTOWIRE_BY_NAME);
		// return new String [] {"classpath*:/**/dao/applicationContext-*.xml",
		// "classpath*:META-INF/applicationContext-*.xml"};
//		super.setDefaultRollback(false);
		return new String[] { "classpath*:/com/faceye/core/config/applicationContext-resources.xml","classpath:/com/faceye/core/config/applicationContext-dao.xml" };
	}
    IBaseDaoSupport baseDaoSupport;
	public IBaseDaoSupport getBaseDaoSupport() {
		return baseDaoSupport;
	}
	public void setBaseDaoSupport(IBaseDaoSupport baseDaoSupport) {
		this.baseDaoSupport = baseDaoSupport;
	}
	
	public void testSaveUser(){
		User user=new User();
	}
    
	
}
