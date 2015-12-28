package com.faceye.core.service.controller;



import com.faceye.core.service.iface.IBaseHibernateService;
import com.faceye.core.service.iface.IBaseJdbcService;
import com.faceye.core.service.iface.IBaseService;

public class BaseService implements IBaseService {
    private IBaseHibernateService baseHibernateService;
    private IBaseJdbcService baseJdbcService;
	public IBaseHibernateService getBaseHibernateService() {
		return baseHibernateService;
	}
	public void setBaseHibernateService(IBaseHibernateService baseHibernateService) {
		this.baseHibernateService = baseHibernateService;
	}
	public IBaseJdbcService getBaseJdbcService() {
		return baseJdbcService;
	}
	public void setBaseJdbcService(IBaseJdbcService baseJdbcService) {
		this.baseJdbcService = baseJdbcService;
	}
}
