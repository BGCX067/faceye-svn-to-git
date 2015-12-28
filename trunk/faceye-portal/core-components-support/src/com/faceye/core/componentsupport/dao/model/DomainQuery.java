package com.faceye.core.componentsupport.dao.model;

import java.util.HashSet;
import java.util.Set;

import com.faceye.core.dao.hibernate.model.BaseObject;

public class DomainQuery extends BaseObject {

	private QueryType queryType;
	
	private Domain domain;
	
	private Set properties=new HashSet(0);
	
	public Domain getDomain() {
		return domain;
	}
	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	public Set getProperties() {
		return properties;
	}
	public void setProperties(Set properties) {
		this.properties = properties;
	}
	public QueryType getQueryType() {
		return queryType;
	}
	public void setQueryType(QueryType queryType) {
		this.queryType = queryType;
	}
	
}
