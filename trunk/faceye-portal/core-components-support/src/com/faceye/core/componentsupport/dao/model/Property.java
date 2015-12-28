package com.faceye.core.componentsupport.dao.model;

import java.util.HashSet;
import java.util.Set;

import com.faceye.core.dao.hibernate.model.BaseObject;

public class Property extends BaseObject {

private String cloumnName;

private String propertyName;




private boolean show;





private Integer orderIndexOfInput;

private Domain domain;

private HtmlType htmlType;

private DataType dataType;

private Set validatorTypies=new HashSet(0);

private Set domainQuerise=new HashSet(0);
public Set getDomainQuerise() {
	return domainQuerise;
}
public void setDomainQuerise(Set domainQuerise) {
	this.domainQuerise = domainQuerise;
}
public Set getValidatorTypies() {
	return validatorTypies;
}
public void setValidatorTypies(Set validatorTypies) {
	this.validatorTypies = validatorTypies;
}
public String getCloumnName() {
	return cloumnName;
}
public void setCloumnName(String cloumnName) {
	this.cloumnName = cloumnName;
}


public Domain getDomain() {
	return domain;
}
public void setDomain(Domain domain) {
	this.domain = domain;
}
public boolean getShow() {
	return show;
}
public void setShow(boolean show) {
	this.show = show;
}
public Integer getOrderIndexOfInput() {
	return orderIndexOfInput;
}
public void setOrderIndexOfInput(Integer orderIndexOfInput) {
	this.orderIndexOfInput = orderIndexOfInput;
}

public String getPropertyName() {
	return propertyName;
}
public void setPropertyName(String propertyName) {
	this.propertyName = propertyName;
}

public HtmlType getHtmlType() {
	return htmlType;
}
public void setHtmlType(HtmlType htmlType) {
	this.htmlType = htmlType;
}
public DataType getDataType() {
	return dataType;
}
public void setDataType(DataType dataType) {
	this.dataType = dataType;
}

}
