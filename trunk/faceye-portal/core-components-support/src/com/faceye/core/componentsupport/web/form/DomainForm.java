package com.faceye.core.componentsupport.web.form;

import java.util.HashSet;
import java.util.Set;

import com.faceye.core.dao.hibernate.model.BaseObject;
/**
 * 
 * @author���κ���
��* @Copy Right:�������
 * @System:�������֧��ϵͳ
��* @Create Time:2007-9-21
 *��@Package And File Name:com.faceye.core.componentsupport.dao.model.Table.java
 * @Description�����ݿ��������࣬����ϵͳ�еĻ�����������
 */
public class DomainForm extends BaseObject {
/**
 *IDΪ���Զ����ɵ�ID��
  *NAMEΪʵ������ı�����
 */	
  /**
   * ʵ�����ƣ�����������Ϊʵ���ȫ������������
   */
	private String domainName;
	/**
	 * ʵ���Ӧ�ı���
	 */
	private String tableName;
	/**
	 *��ʵ������Լ���
	 */
	
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
