package com.faceye.core.service.security.model;

import java.util.HashSet;
import java.util.Set;

import com.faceye.core.componentsupport.dao.model.Domain;
import com.faceye.core.dao.hibernate.model.BaseObject;

public class Tree extends BaseObject {

  private String action;
  private Tree parentTree;
  private Set<Tree> childrenTrees=new HashSet(0);
  private Domain domain;
 
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
}
