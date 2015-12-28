package com.faceye.core.componentsupport.dao.model;

import java.util.HashSet;
import java.util.Set;

import com.faceye.core.dao.hibernate.model.BaseObject;


public class Domain extends BaseObject {
  
    private String domainName;
  
    private String tableName;
  
    private Set properties = new HashSet(0);

    private Set domainQueries;

    private Set trees = new HashSet(0);

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Set getTrees() {
        return trees;
    }

    public void setTrees(Set trees) {
        this.trees = trees;
    }

    public Set getProperties() {
        return properties;
    }

    public void setProperties(Set properties) {
        this.properties = properties;
    }

    public Set getDomainQueries() {
        return domainQueries;
    }

    public void setDomainQueries(Set domainQueries) {
        this.domainQueries = domainQueries;
    }

}
