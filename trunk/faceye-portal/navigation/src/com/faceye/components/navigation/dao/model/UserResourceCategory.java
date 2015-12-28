package com.faceye.components.navigation.dao.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.faceye.core.dao.hibernate.model.BaseObject;
import com.faceye.core.service.security.model.User;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.navigation.dao.model.UserResourceCategory.java
 * @Description:用户资源分类，用于当前的feed分类
 */
public class UserResourceCategory extends BaseObject {
	private String description;
    private UserResourceCategory parentUserResourceCategory;
    private Set childrenUserResourceCategories=new HashSet(0);
    private User user;
    private Set feedSubscribes=new HashSet(0);
    private Integer nodeOrder=new Integer(0);
    
	
	public Integer getNodeOrder() {
		return nodeOrder;
	}
	public void setNodeOrder(Integer nodeOrder) {
		this.nodeOrder = nodeOrder;
	}
	public Set getFeedSubscribes() {
		return feedSubscribes;
	}
	public void setFeedSubscribes(Set feedSubscribes) {
		this.feedSubscribes = feedSubscribes;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserResourceCategory getParentUserResourceCategory() {
		return parentUserResourceCategory;
	}
	public void setParentUserResourceCategory(
			UserResourceCategory parentUserResourceCategory) {
		this.parentUserResourceCategory = parentUserResourceCategory;
	}
	public Set getChildrenUserResourceCategories() {
		return childrenUserResourceCategories;
	}
	public void setChildrenUserResourceCategories(Set childrenUserResourceCategories) {
		this.childrenUserResourceCategories = childrenUserResourceCategories;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Map map(){
		Map map=new HashMap();
		map.put("id", this.getId());
		map.put("name", this.getName());
		map.put("description",this.getDescription());
		map.put("parentId", this.getParentUserResourceCategory().getId());
		map.put("parentName", this.getParentUserResourceCategory().getName());
		return map;
	}
    
}
