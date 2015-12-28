package com.faceye.core.componentsupport.dao.model;

import com.faceye.core.dao.hibernate.model.BaseObject;

public class DataType extends BaseObject {
 private String typeName;
 private Integer typeNumInSql;
 private String typeOfClass;
public String getTypeName() {
	return typeName;
}
public void setTypeName(String typeName) {
	this.typeName = typeName;
}

public Integer getTypeNumInSql() {
	return typeNumInSql;
}
public void setTypeNumInSql(Integer typeNumInSql) {
	this.typeNumInSql = typeNumInSql;
}
public String getTypeOfClass() {
	return typeOfClass;
}
public void setTypeOfClass(String typeOfClass) {
	this.typeOfClass = typeOfClass;
}
}
