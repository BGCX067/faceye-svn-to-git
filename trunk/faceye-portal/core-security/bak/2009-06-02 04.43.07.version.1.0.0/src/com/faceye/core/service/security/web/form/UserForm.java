package com.faceye.core.service.security.web.form;

import com.faceye.core.web.form.BaseForm;

public class UserForm extends BaseForm {
	  private String password;
	  private String rePassword;
	  private String email;
	  private String username;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	  
}
