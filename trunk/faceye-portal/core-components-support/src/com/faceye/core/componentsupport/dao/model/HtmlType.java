package com.faceye.core.componentsupport.dao.model;

import com.faceye.core.dao.hibernate.model.BaseObject;

public class HtmlType extends BaseObject {
	
	private String type;
	
	private String html;
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
