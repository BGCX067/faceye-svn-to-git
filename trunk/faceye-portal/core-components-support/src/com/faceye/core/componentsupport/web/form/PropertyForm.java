package com.faceye.core.componentsupport.web.form;

import com.faceye.core.componentsupport.dao.model.Domain;
import com.faceye.core.dao.hibernate.model.BaseObject;
/**
 * 
 * @author���κ���
��* @Copy Right:�������
 * @System:�������֧��ϵͳ
��* @Create Time:2007-9-22
 *��@Package And File Name:com.faceye.core.componentsupport.dao.model.Column.java
 * @Description:��������
 */
public class PropertyForm extends BaseObject {
/**
 * id ��Ϊ�������ɵ�Ψһ��ʶ
 * name���Ǳ����Ե����ı���
 */
/**
 *   �����ݿ��ж�Ӧ���������
 */
private String cloumnName;
/**
 *  ������
 */
private String propertyName;

/**
 * ��������
 */
private String propertyType;

/**
 * �������Ƿ��ڲ�ѯ�б�����ʾ
 */
private boolean isShow;
/**
 * ʹ�õ�����У������
 */
private String validatorType;

/**
 * ����ҳ����ʾ��¼������ͣ���text,textarae,radiobox,checkbox,select��
 */
private String pageInputType;

/**
 *����ҳ�е���ʾ˳�����ǲ���д��˳����¼��˳������ҳ����ʾ
 */

private Integer orderIndexOfInput;
/**
 * �������
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
