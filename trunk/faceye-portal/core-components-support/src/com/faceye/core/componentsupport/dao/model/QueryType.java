package com.faceye.core.componentsupport.dao.model;

import com.faceye.core.dao.hibernate.model.BaseObject;

public class QueryType extends BaseObject {
 

	private String typeName;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
