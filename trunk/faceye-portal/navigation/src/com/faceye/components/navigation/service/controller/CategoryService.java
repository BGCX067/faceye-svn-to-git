package com.faceye.components.navigation.service.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.faceye.components.navigation.dao.iface.ICategoryDao;
import com.faceye.components.navigation.dao.model.Category;
import com.faceye.components.navigation.service.iface.ICategoryService;
import com.faceye.core.componentsupport.service.controller.DomainService;
import com.faceye.core.service.security.service.iface.ITreeService;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.util.helper.StringPool;

public class CategoryService extends DomainService implements
		ICategoryService {
    private ICategoryDao categoryDao=null;
    private ITreeService treeService=null;
	public ITreeService getTreeService() {
		return treeService;
	}
	public void setTreeService(ITreeService treeService) {
		this.treeService = treeService;
	}
	public String getCategories() {
		// TODO Auto-generated method stub
		List categories=this.getCategoryDao().getCategories();
		StringBuffer json=new StringBuffer("[");
		Iterator it=categories.iterator();
		while(it.hasNext()){
			Category item=(Category)it.next();
			json.append(item.json());
			json.append(StringPool.CHARACTER_COMMA);
		}
		json.deleteCharAt(json.lastIndexOf(StringPool.CHARACTER_COMMA));
		json.append("]");
		return "{\"total\":"+categories.size()+",\"root\":"+json.toString()+"\"}";
	}
	public ICategoryDao getCategoryDao() {
		return categoryDao;
	}
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	public List transferCategories() {
		// TODO Auto-generated method stub
		List result=new ArrayList();
		Iterator it=this.getCategoryDao().getCategories().iterator();
		while(it.hasNext()){
			Category item=(Category) it.next();
			result.add(this.transferCategory(item));
		}
		return result;
	}
	
	private Map transferCategory(Category category){
		Map result=new HashMap();
		result.put(StringPool.TREE_ID, category.getId());
		result.put(StringPool.TREE_NAME, category.getName());
		result.put(StringPool.TREE_PARENTID, category.getParentCategory()==null?null:category.getParentCategory().getId());
		return result;
	}
	public PaginationSupport getAllChildrenCategories(Serializable parentId) {
		// TODO Auto-generated method stub
		List source=this.transferCategories();
		List result=new ArrayList();
		List children= this.getTreeService().getAllChildrenTrees(source, parentId.toString(), result);
		Map parent=this.getTreeService().getTree(source, parentId.toString());
		children.remove(parent);
		return new PaginationSupport(children);
	}

}
