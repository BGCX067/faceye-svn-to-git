package com.faceye.components.navigation.service.controller;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import com.faceye.components.navigation.dao.iface.ITraditionDao;
import com.faceye.components.navigation.dao.model.Tradition;
import com.faceye.components.navigation.service.iface.ITraditionService;
import com.faceye.core.service.controller.BaseHibernateService;
import com.faceye.core.util.helper.JSONUtil;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.util.helper.StringPool;

public class TraditionService extends BaseHibernateService implements
		ITraditionService {
	private ITraditionDao traditionDao = null;
    
	public ITraditionDao getTraditionDao() {
		return traditionDao;
	}

	public void setTraditionDao(ITraditionDao traditionDao) {
		this.traditionDao = traditionDao;
	}

	public String getTraditions(int startIndex) {
		// TODO Auto-generated method stub
		return this.getTraditions(null, startIndex);
	}

	public String getTraditions(Serializable columnId, int startIndex) {
		// TODO Auto-generated method stub
		PaginationSupport ps=this.getTraditionDao().getTraditions(columnId, startIndex);
		StringBuffer root=new StringBuffer("[");
		if(ps!=null&&!ps.getItems().isEmpty()){
			List items=ps.getItems();
			Iterator it=items.iterator();
			while(it.hasNext()){
				Tradition item=(Tradition) it.next();
				root.append(item.json());
				root.append(StringPool.CHARACTER_COMMA);
			}
			root.deleteCharAt(root.lastIndexOf(StringPool.CHARACTER_COMMA));
		}
		root.append("]");
		String json="{\"total\":"+ps.getTotalCount()+",\"root\":"+root.toString()+"}";
		return json;
	}

	public String getAllTraditions() {
		// TODO Auto-generated method stub
//		return this.getTraditionDao().getAllTraditions();
		return this.getPageTraditionsByCategory(null, -1);
	}

	public String getAllTraditionsByCategory(Serializable categoryId) {
		// TODO Auto-generated method stub
		return this.getPageTraditionsByCategory(categoryId, -1);
	}

	public String getPageTraditionsByCategory(Serializable categoryId,
			int startIndex) {
		// TODO Auto-generated method stub
		PaginationSupport ps=this.getTraditionDao().getTraditionsByCategory(categoryId, startIndex);
		StringBuffer root=new StringBuffer("[");
		if(ps!=null&&!ps.getItems().isEmpty()){
			List items=ps.getItems();
			Iterator it=items.iterator();
			while(it.hasNext()){
				Tradition item=(Tradition) it.next();
				root.append(item.json());
				root.append(StringPool.CHARACTER_COMMA);
			}
			root.deleteCharAt(root.lastIndexOf(StringPool.CHARACTER_COMMA));
		}
		root.append("]");
//		String json="{\"total\":"+ps.getTotalCount()+",\"root\":"+root.toString()+"}";
		String json=JSONUtil.pageJson(ps.getTotalCount(), root.toString());
		return json;
	}

	
	
}
