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
 * @Package com.faceye.components.portal.dao.model.PortalTableColumnTemplate.java
 * @Description:portal标签页使用列模式模板
 */
public class PortalColumnTemplate extends BaseObject {
	// 模板预览图图片路径.
	private String imageSrc;
	// 列与列之间的比例,以下格式表示：33:67 或 25:40:15:20
	private String columnScale;
	private Date createTime=new Date();
	/**
	 * 标识是否为默认版式.
	 */
	private String marker;
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public String getColumnScale() {
		return columnScale;
	}

	public void setColumnScale(String columnScale) {
		this.columnScale = columnScale;
	}

	public String json() {
		StringBuffer sb = new StringBuffer("{");
		sb.append("\"id\":");
		sb.append("\"");
		sb.append(this.getId());
		sb.append("\"");
		sb.append(",");
		sb.append("\"name\":");
		sb.append("\"");
		sb.append(this.getName());
		sb.append("\"");
		sb.append(",");
		sb.append("\"imageSrc\":");
		sb.append("\"");
		sb.append(this.getImageSrc());
		sb.append("\"");
		sb.append(",");
		sb.append("\"columnScale\":");
		sb.append("\"");
		sb.append(this.getColumnScale());
		sb.append("\"");
		sb.append("}");
		return sb.toString();
	}

	public String getMarker() {
		return marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}
}
