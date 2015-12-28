package com.faceye.core.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import com.faceye.core.web.iface.ICommonActionMethod;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.core.web.controller.BaseAction.java
 * @Description:
 */
public abstract class BaseAction extends BaseDispatchAction implements ICommonActionMethod {
	/**
	 * ׼����form ���
	 * @param request
	 * @param form
	 */
	protected abstract void onInitForm(HttpServletRequest request,ActionForm form,Object o);
	
	protected abstract void onInitEntity(HttpServletRequest request,Object o,ActionForm form);
	
	/**
	 * 取得本Aciotn管理的实体
	 * 可以通过反射取得
	 */
	protected Class clasz;
	protected Class getEntityClass() {
		// TODO Auto-generated method stub
		return this.clasz;
	}
	protected void setEntityClass(Class clasz){
		this.clasz=clasz;
	}
	protected void setEntityClass(Object o){
		this.clasz=o.getClass();
	}
	
	
}
