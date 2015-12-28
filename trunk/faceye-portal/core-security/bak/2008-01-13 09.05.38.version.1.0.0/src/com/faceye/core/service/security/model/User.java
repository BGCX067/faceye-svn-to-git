package com.faceye.core.service.security.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.acegisecurity.GrantedAuthority;
import org.apache.commons.lang.ArrayUtils;

import com.faceye.core.dao.hibernate.model.BaseObject;


public class User extends BaseObject {
  private Set roles=new HashSet(0);
  private String password;
  private String email;
  private String username;
  protected boolean enabled;
  protected boolean accountExpired;
  protected boolean accountLocked;
  protected boolean credentialsExpired;
public boolean isAccountExpired() {
	return accountExpired;
}
public void setAccountExpired(boolean accountExpired) {
	this.accountExpired = accountExpired;
}
public boolean isAccountLocked() {
	return accountLocked;
}
public void setAccountLocked(boolean accountLocked) {
	this.accountLocked = accountLocked;
}
public boolean isCredentialsExpired() {
	return credentialsExpired;
}
public void setCredentialsExpired(boolean credentialsExpired) {
	this.credentialsExpired = credentialsExpired;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public boolean isEnabled() {
	return enabled;
}
public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
/**
 * Acegi UserDetail Object Required Method:
 */
public boolean isAccountNonExpired(){
	return !this.isAccountExpired();
}
public boolean isAccountNonLocked(){
	return !this.isAccountLocked();
}
public boolean isCredentialsNonExpired(){
	return !this.isCredentialsExpired();
}
public GrantedAuthority[] getAuthorities(){
	Set items = this.getRoles();
	Iterator it=items.iterator();
	GrantedAuthority[] o=null;
	while(it.hasNext()){
		Role item=(Role) it.next();
		o=(GrantedAuthority[]) ArrayUtils.addAll(o,item.permissions2GrantedAuthority());
	}
		return o;
//	return this.getRoles.;
}
public Set getRoles() {
	return roles;
}
public void setRoles(Set roles) {
	this.roles = roles;
}

}
