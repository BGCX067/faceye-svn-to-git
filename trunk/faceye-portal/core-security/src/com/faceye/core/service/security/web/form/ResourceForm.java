package com.faceye.core.service.security.web.form;

import com.faceye.core.web.form.BaseForm;
/**
 * 
 * @author���κ���
��* @Copy Right:�������
 * @System:�������֧��ϵͳ
��* @Create Time:2007-8-19
 *��@Package And File Name:com.faceye.core.service.security.web.form.ResourceForm.java
 * @Description:ϵͳ��Դ����
 */
public class ResourceForm extends BaseForm {
private String id;
private String name;
private String resourceStr;
private String resourceType;
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

public String getResourceStr() {
	return resourceStr;
}
public void setResourceStr(String resourceStr) {
	this.resourceStr = resourceStr;
}
public String getResourceType() {
	return resourceType;
}
public void setResourceType(String resourceType) {
	this.resourceType = resourceType;
}
}
