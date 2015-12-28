package com.faceye.core.service.security.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.acegisecurity.GrantedAuthority;

import com.faceye.core.dao.hibernate.model.BaseObject;

public class Role extends BaseObject {
	private Set users = new HashSet(0);
	
	private Set trees=new HashSet(0);

	private Set permissions = new HashSet(0);

	public Set getUsers() {
		return users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

	public Set getPermissions() {
		return permissions;
	}

	public void setPermissions(Set permissions) {
		this.permissions = permissions;
	}

	public GrantedAuthority[] permissions2GrantedAuthority() {
		Set items = this.getPermissions();
		GrantedAuthority[] result=null;
		if (items.size() > 0) {
			Iterator it = items.iterator();
			result = new GrantedAuthority[items.size()];
			int next=0;
			while (it.hasNext()) {
				Permission item = (Permission) it.next();
				GrantedAuthority o = item.permission2GrantedAuthority();
				result[next]=o;
				next++;
			}
		}
		return result;
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

	public Set getTrees() {
		return trees;
	}

	public void setTrees(Set trees) {
		this.trees = trees;
	}
}
