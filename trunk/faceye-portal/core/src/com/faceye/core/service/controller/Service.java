package com.faceye.core.service.controller;
import com.faceye.core.service.iface.IBaseService;
import com.faceye.core.service.iface.IService;
import com.faceye.core.util.helper.SystemConfig;
public class Service implements IService {
    private IBaseService baseService;
    private SystemConfig systemConfig;
	public IBaseService getBaseService() {
		// TODO Auto-generated method stub
		return baseService;
	}
	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}
//	 private ITreeService treeService=null;
//
//	   public ITreeService getTreeService() {
//	   	return treeService;
//	   }
//
//	   public void setTreeService(ITreeService treeService) {
//	   	this.treeService = treeService;
//	   }
	public SystemConfig getSystemConfig() {
		return systemConfig;
	}
	public void setSystemConfig(SystemConfig systemConfig) {
		this.systemConfig = systemConfig;
	}

}
