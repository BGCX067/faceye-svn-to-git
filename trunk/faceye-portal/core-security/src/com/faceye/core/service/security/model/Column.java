package com.faceye.core.service.security.model;

import java.util.HashSet;
import java.util.Set;

import com.faceye.core.dao.hibernate.model.BaseObject;

public class Column extends BaseObject {
	private String description;
	private Column parentColumn;
	private Set childrenColumns = new HashSet(0);
	private String url;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Column getParentColumn() {
		return parentColumn;
	}

	public void setParentColumn(Column parentColumn) {
		this.parentColumn = parentColumn;
	}

	public Set getChildrenColumns() {
		return childrenColumns;
	}

	public void setChildrenColumns(Set childrenColumns) {
		this.childrenColumns = childrenColumns;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String json() {
		String json = "{\"id\":\"" + this.getId() + "\",\"name\":\""
				+ this.getName() + "\",\"description\":\""
				+ this.getDescription() + "\",\"url\":\"" + this.getUrl()
				+ "\"";
		// +o.getParentTree().getId()+"\"}]"
		if (this.getParentColumn() != null) {
			json += ",\"parentId\":\"";
			json += this.getParentColumn().getId();
			json += "\",\"parentName\":\"";
			json += this.getParentColumn().getName();
			json += "\"}";
		} else {
			json += ",\"parentId\":\"";
			json += "source";
			json += "\",\"parentName\":\"";
			json += "Common Platform";
			json += "\"}";
		}
		
		return json;
	}
}
