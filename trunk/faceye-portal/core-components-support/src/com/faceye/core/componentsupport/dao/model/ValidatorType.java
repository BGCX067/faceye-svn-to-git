package com.faceye.core.componentsupport.dao.model;

import java.util.HashSet;
import java.util.Set;

import com.faceye.core.dao.hibernate.model.BaseObject;

public class ValidatorType extends BaseObject {

	private String functionName;

	private String validatorMessage;
	
	private Set properties=new HashSet(0);

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	

	public Set getProperties() {
		return properties;
	}

	public void setProperties(Set properties) {
		this.properties = properties;
	}

	public String getValidatorMessage() {
		return validatorMessage;
	}

	public void setValidatorMessage(String validatorMessage) {
		this.validatorMessage = validatorMessage;
	}
}
