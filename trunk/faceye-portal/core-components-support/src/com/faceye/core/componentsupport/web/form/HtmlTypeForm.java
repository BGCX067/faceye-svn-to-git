package com.faceye.core.componentsupport.web.form;

import com.faceye.core.dao.hibernate.model.BaseObject;
/**
 * 
 * @author：宋海鹏
　* @Copy Right:早点网络
 * @System:早点网络支持系统
　* @Create Time:2007-9-22
 *　@Package And File Name:com.faceye.core.componentsupport.dao.model.HtmlType.java
 * @Description:属性对应的HTML类型
 */
public class HtmlTypeForm extends BaseObject {
	/**
	 *  id:为本HTML元素生成的唯一标识
	 *  name：为本HTML元素的中文别名。
	 */
	/**
	 *　HTML类型，如text,textarea,radiobox,checkbox,select
	 */
	private String type;
	/**
	 *  本类型对应的HTML元素
	 *  如text对应　：<input type=\"text\" size=\"50\"></input>
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
