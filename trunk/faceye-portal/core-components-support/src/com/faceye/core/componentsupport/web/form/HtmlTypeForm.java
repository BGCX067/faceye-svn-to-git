package com.faceye.core.componentsupport.web.form;

import com.faceye.core.dao.hibernate.model.BaseObject;
/**
 * 
 * @author���κ���
��* @Copy Right:�������
 * @System:�������֧��ϵͳ
��* @Create Time:2007-9-22
 *��@Package And File Name:com.faceye.core.componentsupport.dao.model.HtmlType.java
 * @Description:���Զ�Ӧ��HTML����
 */
public class HtmlTypeForm extends BaseObject {
	/**
	 *  id:Ϊ��HTMLԪ�����ɵ�Ψһ��ʶ
	 *  name��Ϊ��HTMLԪ�ص����ı�����
	 */
	/**
	 *��HTML���ͣ���text,textarea,radiobox,checkbox,select
	 */
	private String type;
	/**
	 *  �����Ͷ�Ӧ��HTMLԪ��
	 *  ��text��Ӧ����<input type=\"text\" size=\"50\"></input>
	 */
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
