package com.faceye.components.portal.dao.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.faceye.core.dao.hibernate.model.BaseObject;

public class Portal extends BaseObject {
	private Set portalColumns = new HashSet(0);
	private PortalContainer portalContainer;
	private Date createTime=new Date();
	//本标签页使用的格式模板,既分多少列显示.
	private PortalColumnTemplate portalColumnTemplate;

	public PortalColumnTemplate getPortalColumnTemplate() {
		return portalColumnTemplate;
	}

	public void setPortalColumnTemplate(PortalColumnTemplate portalColumnTemplate) {
		this.portalColumnTemplate = portalColumnTemplate;
	}

	public Set getPortalColumns() {
		return portalColumns;
	}

	public void setPortalColumns(Set portalColumns) {
		this.portalColumns = portalColumns;
	}

	public PortalContainer getPortalContainer() {
		return portalContainer;
	}

	public void setPortalContainer(PortalContainer portalContainer) {
		this.portalContainer = portalContainer;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
		sb.append("}");
		return sb.toString();
	}
}
