package com.faceye.core.service.security.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;

import com.faceye.core.dao.hibernate.model.BaseObject;


public class Permission extends BaseObject {
   private Set roles=new HashSet(0);
   private Set resources=new HashSet(0);
public Set getRoles() {
	return roles;
}

public void setRoles(Set roles) {
	this.roles = roles;
}

public Set getResources() {
	return resources;
}

public void setResources(Set resources) {
	this.resources = resources;
}

public GrantedAuthority permission2GrantedAuthority(){
	GrantedAuthority o = new GrantedAuthorityImpl(this.getName());
	return o;
}

//public GrantedAuthority [] permission2GrantedAuthority(Set permissions){
//	GrantedAuthority[] result=null;
//	if (permissions.size() > 0) {
//		Iterator it = permissions.iterator();
//		result = new GrantedAuthority[permissions.size()];
//		int next=0;
//		while (it.hasNext()) {
//			Permission item = (Permission) it.next();
//			GrantedAuthority o = item.permission2GrantedAuthority();
//			result[next]=o;
//			next++;
//		}
//	}
//	return result;
//}
}
