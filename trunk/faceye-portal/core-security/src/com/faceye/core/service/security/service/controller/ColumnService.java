package com.faceye.core.service.security.service.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.faceye.core.service.controller.BaseHibernateService;
import com.faceye.core.service.security.dao.iface.IColumnDao;
import com.faceye.core.service.security.model.Column;
import com.faceye.core.service.security.service.iface.IColumnService;
import com.faceye.core.util.helper.StringPool;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.core.service.security.service.controller.ColumnService.java
 * @Description:栏目服务
 */
public class ColumnService extends BaseHibernateService implements
		IColumnService {
    private static List columns=null;
	private IColumnDao columnDao;

	public IColumnDao getColumnDao() {
		return columnDao;
	}

	public void setColumnDao(IColumnDao columnDao) {
		this.columnDao = columnDao;
	}
 
	/**
	 * 将节点进行统一转换，转换为一致的Map结构
	 * @param column
	 * @return
	 */
	private Map transferColumn(Column column){
		if(column==null){
			return null;
		}
		Map result=new HashMap();
		result.put(StringPool.TREE_ID, column.getId());
		result.put(StringPool.TREE_NAME, column.getName());
		if (column.getParentColumn() != null) {
			result.put(StringPool.TREE_PARENTID, column.getParentColumn().getId());
		} else {
			result.put(StringPool.TREE_PARENTID, null);
		}
//		if(StringUtils.isNotEmpty(column.getAction())){
//			result.put(StringPool.TREE_ACTION, column.getAction());
//		}else{
//			result.put(StringPool.TREE_ACTION, null);
//		}
		if(StringUtils.isNotEmpty(column.getUrl())){
			result.put(StringPool.TREE_ACTION, column.getUrl());
		}else{
			result.put(StringPool.TREE_ACTION, null);
		}
		return result;
	}
	
	public List transferColumns(List columns){
		if(columns==null||columns.isEmpty()){
			columns=this.getColumns(null);
		}
		List result=new ArrayList();
		Iterator it=columns.iterator();
		while(it.hasNext()){
			Column item=(Column) it.next();
			Map map=this.transferColumn(item);
			result.add(map);
		}
		return result;
	}
/**
 * 将节点数据转化为JSON格式的
 * @param source
 * @return
 */
	private String json(Map source){
		if(source==null){
			return null;
		}
		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append("\"text\":");
		json.append("\"");
		json.append(source.get(StringPool.TREE_NAME).toString());
		json.append("\"");
		json.append(",");
		json.append("\"id\":");
		json.append("\"");
		json.append(source.get(StringPool.TREE_ID).toString());
		json.append("\"");
		json.append(",");
		json.append("\"leaf\":");
		if (source.get(StringPool.TREE_PARENTID)!=null) {
			json.append("false");
		} else {
			json.append("true");
		}
		json.append(",");
		json.append("\"cls\":");
		json.append("\"file\"");
		if(source.get(StringPool.TREE_ACTION)!=null){
			json.append(",");
			json.append("\"link\":");
			json.append("\"");
			json.append(source.get(StringPool.TREE_ACTION).toString());
			json.append("\"");
		}else{
			json.append(",");
			json.append("\"link\":");
			json.append("\"");
			json.append("\"\"");
			json.append("\"");
		}
		
		json.append("}");
		return json.toString();
	}

public String columns2json(List columns) {
	if(columns==null||columns.isEmpty()){
		columns=this.transferColumns(null);
	}
	if(columns==null||columns.isEmpty()){
		return null;
	}
	StringBuffer json=new StringBuffer("[");
	
	for(int i=0;i<columns.size();i++){
		Object item=columns.get(i);
		if(item instanceof Column){
			item=this.transferColumn((Column)item);
		}
		json.append(this.json((Map)item));
		json.append(StringPool.CHARACTER_COMMA);
	}
	json.deleteCharAt(json.lastIndexOf(StringPool.CHARACTER_COMMA));
	json.append("]");
	// TODO Auto-generated method stub
	return json.toString();
}

public List getColumns(Serializable columnId) {
	// TODO Auto-generated method stub
	if(null==columnId){
		return this.getColumnDao().getColumns();
	}else{
		return this.getColumnDao().getColumns(columnId);
	}
}


}
