package com.faceye.core.dao.hibernate.model;

import java.util.Date;

public class BaseObject implements java.io.Serializable {
  private String id;
  private String name;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

}
