package com.faceye.components.navigation.service.iface;

import java.io.Serializable;

import com.faceye.core.service.iface.IBaseHibernateService;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.navigation.service.iface.ITraditionService.java
 * @Description:传统导航服务接口
 */
public interface ITraditionService extends IBaseHibernateService {
	
	public String getTraditions(int startIndex);
	/**
	 * 根据栏目ID取得导航数据
	 * 目前导航栏目分：传统导航和Feed导航
	 * @param columnId
	 * @param startIndex
	 * @return
	 */
	public String getTraditions(Serializable columnId,int startIndex);
	
    /**
     * 根据分类及索引取一页数据
     * @param categoryId
     * @param startIndex
     * @return
     */
	public String getPageTraditionsByCategory(Serializable categoryId,int startIndex);
    /**
     * 根据分类取得所有导航
     * @param categoryId
     * @return
     */
	public String getAllTraditionsByCategory(Serializable categoryId);
	/**
	 * 取得所有导航
	 * @return
	 */
	public String getAllTraditions();

}
