package com.faceye.components.portal.dao.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.faceye.core.dao.hibernate.model.BaseObject;

public class Portlet extends BaseObject {
	//portlet将要显示位置的x坐标
//   private String x;
//   private String y;
//   //本portlet所属portalColumn
//   private PortalColumn protalColumn;
	//生成本portlet所需要的javascript代码
	private String source;
	private Date createTime=new Date();
	/**
	 * 源码路径
	 */
	private String url;
	private String imageSrc;
	/**
	 * 本js的入口方法
	 */
	private String init;
	private Set portletSubscribes=new HashSet(0);
	/**
	 * 本portlet是否已经被当前用户订阅
	 * true/false=已订阅/未订阅
	 */
	private String flag="false";

	public Set getPortletSubscribes() {
		return portletSubscribes;
	}

	public void setPortletSubscribes(Set portletSubscribes) {
		this.portletSubscribes = portletSubscribes;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public String json(){
		StringBuffer sb=new StringBuffer("{");
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
		sb.append("\"source\":");
		sb.append("\"");
		sb.append(this.getSource());
		sb.append("\"");
		sb.append(",");
		sb.append("\"url\":");
		sb.append("\"");
		sb.append(this.getUrl());
		sb.append("\"");
		sb.append(",");
		sb.append("\"imageSrc\":");
		sb.append("\"");
		sb.append(this.getImageSrc());
		sb.append("\"");
		sb.append(",");
		sb.append("\"createTime\":");
		sb.append("\"");
		sb.append(this.getCreateTime());
		sb.append("\"");
		sb.append(",");
		sb.append("\"init\":");
		sb.append("\"");
		sb.append(this.getInit());
		sb.append("\"");
		sb.append(",");
		sb.append("\"flag\":");
		sb.append("\"");
		sb.append(this.getFlag());
		sb.append("\"");
		sb.append("}");
		return sb.toString();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public String getInit() {
		return init;
	}

	public void setInit(String init) {
		this.init = init;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
