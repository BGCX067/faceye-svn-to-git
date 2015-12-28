package com.faceye.core.service.security.model;

import java.util.HashSet;
import java.util.Set;

import com.faceye.core.componentsupport.dao.model.Domain;
import com.faceye.core.dao.hibernate.model.BaseObject;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.core.service.security.model.Tree.java
 * @Description:树形结构实体类
 */
public class Tree extends BaseObject {

  private String action;
  private Tree parentTree;
  private Set<Tree> childrenTrees=new HashSet(0);
  private Domain domain;
  
  private Set roles=new HashSet(0);
 
  private String url;
public Set<Tree> getChildrenTrees() {
	return childrenTrees;
}

public void setChildrenTrees(Set<Tree> childrenTrees) {
	this.childrenTrees = childrenTrees;
}

public Tree getParentTree() {
	return parentTree;
}

public void setParentTree(Tree parentTree) {
	this.parentTree = parentTree;
}

public String getAction() {
	return action;
}

public void setAction(String action) {
	this.action = action;
}

public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}

public Domain getDomain() {
	return domain;
}

public void setDomain(Domain domain) {
	this.domain = domain;
}

public Set getRoles() {
	return roles;
}

public void setRoles(Set roles) {
	this.roles = roles;
}
}
