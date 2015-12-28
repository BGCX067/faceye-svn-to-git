package com.faceye.components.navigation.dao.controller;

import java.util.List;

import com.faceye.components.navigation.dao.iface.ICategoryDao;
import com.faceye.components.navigation.dao.model.Category;
import com.faceye.core.componentsupport.dao.controller.DomainDao;

public class CategoryDao extends DomainDao implements ICategoryDao {

	public List getCategories() {
		
		return this.loadAllObjects(Category.class);
	}

}
