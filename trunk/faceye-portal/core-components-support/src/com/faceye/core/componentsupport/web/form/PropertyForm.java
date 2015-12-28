package com.faceye.core.componentsupport.web.form;

import com.faceye.core.componentsupport.dao.model.Domain;
import com.faceye.core.dao.hibernate.model.BaseObject;
/**
 * 
 * @author：宋海鹏
　* @Copy Right:早点网络
 * @System:早点网络支持系统
　* @Create Time:2007-9-22
 *　@Package And File Name:com.faceye.core.componentsupport.dao.model.Column.java
 * @Description:对象属性
 */
public class PropertyForm extends BaseObject {
/**
 * id 是为本列生成的唯一标识
 * name　是本属性的中文别名
 */
/**
 *   在数据库中对应表的列名。
 */
private String cloumnName;
/**
 *  属性名
 */
private String propertyName;

/**
 * 属性类型
 */
private String propertyType;

/**
 * 本属性是否在查询列表中显示
 */
private boolean isShow;
/**
 * 使用的数据校验类型
 */
private String validatorType;

/**
 * 在网页中显示的录入框类型，如text,textarae,radiobox,checkbox,select等
 */
private String pageInputType;

/**
 *在网页中的显示顺序，如是不填写本顺序，则按录入顺序在网页中显示
 */

private Integer orderIndexOfInput;
/**
 * 领域对像
 */
private Domain doamin;
public String getCloumnName() {
	return cloumnName;
}
public void setCloumnName(String cloumnName) {
	this.cloumnName = cloumnName;
}
public Domain getDoamin() {
	return doamin;
}
public void setDoamin(Domain doamin) {
	this.doamin = doamin;
}
public boolean isShow() {
	return isShow;
}
public void setShow(boolean isShow) {
	this.isShow = isShow;
}
public Integer getOrderIndexOfInput() {
	return orderIndexOfInput;
}
public void setOrderIndexOfInput(Integer orderIndexOfInput) {
	this.orderIndexOfInput = orderIndexOfInput;
}
public String getPageInputType() {
	return pageInputType;
}
public void setPageInputType(String pageInputType) {
	this.pageInputType = pageInputType;
}
public String getPropertyName() {
	return propertyName;
}
public void setPropertyName(String propertyName) {
	this.propertyName = propertyName;
}
public String getPropertyType() {
	return propertyType;
}
public void setPropertyType(String propertyType) {
	this.propertyType = propertyType;
}
public String getValidatorType() {
	return validatorType;
}
public void setValidatorType(String validatorType) {
	this.validatorType = validatorType;
}





}
