package com.faceye.components.opensource.service.controller;

import java.io.Serializable;

import net.sf.json.JSONArray;

import com.faceye.components.opensource.dao.iface.IOpenSourceDao;
import com.faceye.components.opensource.service.iface.IOpenSourceService;
import com.faceye.core.service.controller.BaseHibernateService;
import com.faceye.core.util.helper.PaginationSupport;

/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.opensource.service.controller.OpenSourceService.java
 * @Description:开源栏目服务类
 */
public class OpenSourceService extends BaseHibernateService implements
		IOpenSourceService {
	private IOpenSourceDao openSourceDao = null;

	public IOpenSourceDao getOpenSourceDao() {
		return openSourceDao;
	}

	public void setOpenSourceDao(IOpenSourceDao openSourceDao) {
		this.openSourceDao = openSourceDao;
	}

	public String getOpenSource(int startIndex) {
		// TODO Auto-generated method stub
		return this.getOpenSource(null, startIndex);
	}

	public String getOpenSource(Serializable columnId, int startIndex) {
		// TODO Auto-generated method stub
		PaginationSupport ps=this.getOpenSourceDao().getOpenSource(columnId,startIndex);
		String list=JSONArray.fromObject(ps.transItems()).toString();
		String json="{\"total\":"+ps.getTotalCount()+",\"root\":"+list+"}";
		return json;
	}

}
