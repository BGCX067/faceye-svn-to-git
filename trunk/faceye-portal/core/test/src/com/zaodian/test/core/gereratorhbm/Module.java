package com.faceye.test.core.gereratorhbm;

import java.util.Date;
/**
 * @hibernate.class table="test"
 */
public class Module implements java.io.Serializable {
  private String id;
  private String name;
  private int age;
  private Date birthday;
  /**
   * @hibernate.id column="id" generator-class="uuid.hex" length="32" unsaved-value=""
   */
  public String getId() {
  	return id;
  }
  /**
   * @hibernate.property not-null="true"
   */
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
/**
 * @hibernate.property not-null="true"
 */
public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}

public void setId(String id) {
	this.id = id;
}
/**
 * @hibernate.property column="user_name" length="55" not-null="true"
 */
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
}
