package com.faceye.components.portal.dao.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.faceye.core.dao.hibernate.model.BaseObject;

public class PortalStyle extends BaseObject {
  private Set portalContainers=new HashSet(0);
  private Date createTime=new Date();
public Date getCreateTime() {
	return createTime;
}

public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}

public Set getPortalContainers() {
	return portalContainers;
}

public void setPortalContainers(Set portalContainers) {
	this.portalContainers = portalContainers;
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
