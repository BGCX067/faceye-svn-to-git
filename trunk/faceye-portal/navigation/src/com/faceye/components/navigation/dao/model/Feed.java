package com.faceye.components.navigation.dao.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.faceye.core.dao.hibernate.model.BaseObject;
import com.faceye.core.service.security.model.Column;

public class Feed extends BaseObject {
	//feed链接
  private String url;
  //feed描述
  private String description;
  //feed加入日期
  private Date createDate;
  //feed所属栏目
  private Column column;
  //feed 所属分类
  private Category category;
  
  //本feed是否已被当前用户订阅
  private boolean isSubscribed;

  private Set feedSubscribes=new HashSet(0);
  private Set feedSubscribeCount=new HashSet(0);
public Set getFeedSubscribes() {
	return feedSubscribes;
}
public void setFeedSubscribes(Set feedSubscribes) {
	this.feedSubscribes = feedSubscribes;
}
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
public Date getCreateDate() {
	return createDate;
}
public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}
public Column getColumn() {
	return column;
}
public void setColumn(Column column) {
	this.column = column;
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
	
	json.append("\"categoryId\":");
	json.append("\"");
	json.append(this.getCategory().getId());
	json.append("\",");
	
	json.append("\"categoryName\":");
	json.append("\"");
	json.append(this.getCategory().getName());
	json.append("\",");
	json.append("\"columnId\":");
	json.append("\"");
	json.append(this.getColumn().getId());
	json.append("\",");
	
	json.append("\"columnName\":");
	json.append("\"");
	json.append(this.getColumn().getName());
	json.append("\",");
	
	json.append("\"isSubscribed\":");
	json.append("\"");
	json.append(this.isSubscribed());
	json.append("\"");
	json.append("}");
	return json.toString();
	
}
public Set getFeedSubscribeCount() {
	return feedSubscribeCount;
}
public void setFeedSubscribeCount(Set feedSubscribeCount) {
	this.feedSubscribeCount = feedSubscribeCount;
}
public boolean isSubscribed() {
	return isSubscribed;
}
public void setSubscribed(boolean isSubscribed) {
	this.isSubscribed = isSubscribed;
}
}
