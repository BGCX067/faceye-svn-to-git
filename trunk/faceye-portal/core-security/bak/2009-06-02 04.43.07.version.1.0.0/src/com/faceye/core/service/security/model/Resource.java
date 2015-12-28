package com.faceye.core.service.security.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.acegisecurity.GrantedAuthority;

import com.faceye.core.dao.hibernate.model.BaseObject;



public class Resource extends BaseObject {
  private String resourceStr;
  private String resourceType;
  private Set permissions=new HashSet(0);

public Set getPermissions() {
	return permissions;
}

public String getResourceType() {
	return resourceType;
}

public void setResourceType(String resourceType) {
	this.resourceType = resourceType;
}

public void setPermissions(Set permissions) {
	this.permissions = permissions;
}

public GrantedAuthority [] permission2GrantedAuthority(){
  Set permissions=this.getPermissions();
	GrantedAuthority[] result=null;
  if (permissions.size() > 0) {
		Iterator it = permissions.iterator();
		result = new GrantedAuthority[permissions.size()];
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



public String getResourceStr() {
	return resourceStr;
}

public void setResourceStr(String resourceStr) {
	this.resourceStr = resourceStr;
}

public String json(){
	StringBuffer json=new StringBuffer("{");
	json.append("\"id\":");
	json.append("\"");
	json.append(this.getId());
	json.append("\",");
	
	json.append("\"name\":");
	json.append("\"");
	json.append(this.getName());
	json.append("\",");
	
	json.append("\"resourceType\":");
	json.append("\"");
	json.append(this.getResourceType());
	json.append("\",");
	
	json.append("\"resourceStr\":");
	json.append("\"");
	json.append(this.getResourceStr());
	json.append("\"");
	
	json.append("}");
	return json.toString();
}
}
