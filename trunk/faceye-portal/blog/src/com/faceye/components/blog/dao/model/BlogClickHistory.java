package com.faceye.components.blog.dao.model;

import java.util.Date;

import com.faceye.components.portal.dao.model.PortalContainer;
import com.faceye.core.dao.hibernate.model.BaseObject;
import com.faceye.core.service.security.model.User;

/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.blog.dao.model.BlogClickHistory.java
 * @Description:博客点击历史记录
 */
public class BlogClickHistory extends BaseObject {
	// 当前访问用户的IP
	private String ip;
	// 当前哪个用户访问。
	private User user;
	// 记录访问时间
	private Date createTime = new Date();
	// 记录访问的是哪个博客。
	private PortalContainer portalContainer;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public PortalContainer getPortalContainer() {
		return portalContainer;
	}

	public void setPortalContainer(PortalContainer portalContainer) {
		this.portalContainer = portalContainer;
	}
}
