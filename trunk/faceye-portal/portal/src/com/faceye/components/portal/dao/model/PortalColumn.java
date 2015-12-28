package com.faceye.components.portal.dao.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.faceye.core.dao.hibernate.model.BaseObject;

/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.portal.dao.model.PortalTable.java
 * @Description:容纳多个portlet的标签页.
 */
public class PortalColumn extends BaseObject {
	
	//本标签页(Ext.Panel)使用的icon
    private String icon;
  //本标签页(Ext.Panel)使用的iconCls
    private String iconCls;
    private Portal portal;
    private Date createTime=new Date();
    private String singleColumnScale;
    private Set portletSubscribes=new HashSet(0);
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public Set getPortletSubscribes() {
		return portletSubscribes;
	}
	public void setPortletSubscribes(Set portletSubscribes) {
		this.portletSubscribes = portletSubscribes;
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
		sb.append("\"icon\":");
		sb.append("\"");
		sb.append(this.getIcon());
		sb.append("\"");
		sb.append(",");
		sb.append("\"iconCls\":");
		sb.append("\"");
		sb.append(this.getIconCls());
		sb.append("\"");
		sb.append(",");
		sb.append("\"singleColumnScale\":");
		sb.append("\"");
		sb.append(this.getSingleColumnScale());
		sb.append("\"");
		sb.append(",");
		sb.append("\"createTime\":");
		sb.append("\"");
		sb.append(this.getCreateTime());
		sb.append("\"");
		sb.append("}");
		return sb.toString();
	}
	public Portal getPortal() {
		return portal;
	}
	public void setPortal(Portal portal) {
		this.portal = portal;
	}
	public String getSingleColumnScale() {
		return singleColumnScale;
	}
	public void setSingleColumnScale(String singleColumnScale) {
		this.singleColumnScale = singleColumnScale;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
