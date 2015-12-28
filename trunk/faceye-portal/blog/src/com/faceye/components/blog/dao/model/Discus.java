package com.faceye.components.blog.dao.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.faceye.core.dao.hibernate.model.BaseObject;
import com.faceye.core.service.security.model.User;

public class Discus extends BaseObject {
  private Date createTime=new Date();
  private String content;
  private Article article;
  private User user;
public Date getCreateTime() {
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Article getArticle() {
	return article;
}
public void setArticle(Article article) {
	this.article = article;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
  public Map map(){
	  Map map=new HashMap();
	  map.put("id", this.getId());
	  map.put("name", this.getName());
	  SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	  map.put("createTime", dateFormat.format(this.getCreateTime()));
	  map.put("content", this.getContent());
	  map.put("articleId", this.getArticle().getId());
	  map.put("userId", this.getUser().getId());
	  map.put("username", this.getUser().getUsername());
	  return map;
  }
}
