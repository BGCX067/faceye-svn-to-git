package com.faceye.core.componentsupport.web.form;

import java.util.HashSet;
import java.util.Set;

import com.faceye.core.dao.hibernate.model.BaseObject;
/**
 * 
 * @author：宋海鹏
　* @Copy Right:早点网络
 * @System:早点网络支持系统
　* @Create Time:2007-9-21
 *　@Package And File Name:com.faceye.core.componentsupport.dao.model.Table.java
 * @Description：数据库基类操作类，用于系统中的基础数据整合
 */
public class DomainForm extends BaseObject {
/**
 *ID为表自动生成的ID。
  *NAME为实体的中文别名。
 */	
  /**
   * 实体名称，本处的名称为实体的全名，包括包名
   */
	private String domainName;
	/**
	 * 实体对应的表名
	 */
	private String tableName;
	/**
	 *　实体的属性集合
	 */
	
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
