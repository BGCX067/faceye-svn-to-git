package com.faceye.components.portal.dao.model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.faceye.core.dao.hibernate.model.BaseObject;
import com.faceye.core.service.security.model.User;
import com.faceye.core.util.helper.DateUtil;

/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.portal.dao.model.PortalContainer.java
 * @Description:容纳所有protal的容器 可以容纳不同的面板页,每个面板页由不同的porlet组成. 所有的面板页共同组成portal
 */
public class PortalContainer extends BaseObject {
	//portal所属用户
	private User user;
	//portal所使用的样式表
    private PortalStyle portalStyle;
    private Date createTime=new Date();
    private Set portals=new HashSet(0);
	public PortalStyle getPortalStyle() {
		return portalStyle;
	}

	public void setPortalStyle(PortalStyle portalStyle) {
		this.portalStyle = portalStyle;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public Set getPortals() {
		return portals;
	}

	public void setPortals(Set portals) {
		this.portals = portals;
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
	public Map map(){
		Map map=new HashMap();
		map.put("id", this.getId());
		map.put("name", this.getName());
		map.put("username", this.getUser().getUsername());
		map.put("user_id", this.getUser().getId());
		map.put("createTime", DateUtil.parseDate(this.getCreateTime(), "yyyy-MM-dd"));
		return map;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
