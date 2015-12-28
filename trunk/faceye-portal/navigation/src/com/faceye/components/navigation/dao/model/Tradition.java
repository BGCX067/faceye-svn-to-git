package com.faceye.components.navigation.dao.model;

import java.util.Date;

import com.faceye.core.dao.hibernate.model.BaseObject;
import com.faceye.core.service.security.model.Column;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.navigation.dao.model.Tradition.java
 * @Description:传统导航
 */
public class Tradition extends BaseObject {
	//导航URL
	private String url;
	//导航说明
	private String description;
	//导航所属栏目
	private Column column;
	//导航加入日期
	private Date createDate;
	//tradition所属分类
	private Category category;
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Column getColumn() {
		return column;
	}
	public void setColumn(Column column) {
		this.column = column;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String json(){
		StringBuffer json=new StringBuffer();
		json.append("{");
		json.append("\"id\":");
		json.append("\"");
		json.append(this.getId());
		json.append("\",");
		
		json.append("\"name\":");
		json.append("\"");
		json.append(this.getName());
		json.append("\",");
		
		json.append("\"url\":");
		json.append("\"");
		json.append(this.getUrl());
		json.append("\",");
		
		json.append("\"description\":");
		json.append("\"");
		json.append(this.getDescription());
		json.append("\",");
		
		json.append("\"createDate\":");
		json.append("\"");
		json.append(this.getCreateDate());
		json.append("\",");
		
		json.append("\"columnId\":");
		json.append("\"");
		json.append(this.getColumn().getId());
		json.append("\",");
		
		json.append("\"categoryId\":");
		json.append("\"");
		json.append(this.getCategory().getId());
		json.append("\",");
		
		json.append("\"categoryName\":");
		json.append("\"");
		json.append(this.getCategory().getName());
		json.append("\",");
		
		json.append("\"columnName\":");
		json.append("\"");
		json.append(this.getColumn().getName());
		json.append("\"}");
		return json.toString();
		
	}

}
