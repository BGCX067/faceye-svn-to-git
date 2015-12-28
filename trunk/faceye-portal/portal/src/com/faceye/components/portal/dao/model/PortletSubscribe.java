package com.faceye.components.portal.dao.model;

import java.util.Date;

import com.faceye.core.dao.hibernate.model.BaseObject;

/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.portal.dao.model.PortletSubscribe.java
 * @Description:用户征订portlet
 */
public class PortletSubscribe extends BaseObject {
	// 正在征订的portlet
	private Portlet protlet;
	// 将portlet征订到哪一个portalColumn
	private PortalColumn portalColumn;
	/**
	 * 本portlet显示在portalColumn中的坐标,x.y均从0开始,并不代表
	 * Ext.getCmpgetX只代表portlet之间的相对位置,左上角为坐标原点
	 * 
	 */
	private String x;
	private String y;
	private Integer order;
	private Date createTime=new Date();
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Portlet getProtlet() {
		return protlet;
	}
	public void setProtlet(Portlet protlet) {
		this.protlet = protlet;
	}
	public PortalColumn getPortalColumn() {
		return portalColumn;
	}
	public void setPortalColumn(PortalColumn portalColumn) {
		this.portalColumn = portalColumn;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}

}
