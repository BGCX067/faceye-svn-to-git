package com.faceye.core.service.security.web.form;

import com.faceye.core.web.form.BaseForm;

public class TreeForm extends BaseForm {
  private String action;
  private String parentId;
  private String parentName;
public String getAction() {
	return action;
}
public void setAction(String action) {
	this.action = action;
}
public String getParentId() {
	return parentId;
}
public void setParentId(String parentId) {
	this.parentId = parentId;
}
public String getParentName() {
	return parentName;
}
public void setParentName(String parentName) {
	this.parentName = parentName;
}
  
}
