package com.faceye.components.opensource.dao.model;

import com.faceye.core.dao.hibernate.model.BaseObject;
import com.faceye.core.service.security.model.Column;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.opensource.dao.model.OpenSource.java
 * @Description:开源项目
 */
public class OpenSource extends BaseObject {
	//对开源项目的描述.
  private String content;
  //开源项目的URL链接,即项目主页
  private String url;
  //开源项目所属的上级栏目.
  private Column column;
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public Column getColumn() {
	return column;
}
public void setColumn(Column column) {
	this.column = column;
}
}
