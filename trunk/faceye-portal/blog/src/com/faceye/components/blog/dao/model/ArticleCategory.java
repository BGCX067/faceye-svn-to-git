package com.faceye.components.blog.dao.model;

import java.util.HashSet;
import java.util.Set;

import com.faceye.components.portal.dao.model.PortalContainer;
import com.faceye.core.dao.hibernate.model.BaseObject;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.blog.dao.model.ArticleCategory.java
 * @Description:用户的文章分类,支持用户文章无限级分类
 */
public class ArticleCategory extends BaseObject {
	//本分类所属的blog
    private PortalContainer portalContainer;
    private Integer nodeOrder;
	private ArticleCategory parentArticleCategory;
	private Set articles=new HashSet(0);
	private Set childrenArticleCategories=new HashSet(0);
	public ArticleCategory getParentArticleCategory() {
		return parentArticleCategory;
	}
	public void setParentArticleCategory(ArticleCategory parentArticleCategory) {
		this.parentArticleCategory = parentArticleCategory;
	}
	public Set getChildrenArticleCategories() {
		return childrenArticleCategories;
	}
	public void setChildrenArticleCategories(Set childrenArticleCategories) {
		this.childrenArticleCategories = childrenArticleCategories;
	}
	public PortalContainer getPortalContainer() {
		return portalContainer;
	}
	public void setPortalContainer(PortalContainer portalContainer) {
		this.portalContainer = portalContainer;
	}
	public Integer getNodeOrder() {
		return nodeOrder;
	}
	public void setNodeOrder(Integer nodeOrder) {
		this.nodeOrder = nodeOrder;
	}
	public Set getArticles() {
		return articles;
	}
	public void setArticles(Set articles) {
		this.articles = articles;
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
		sb.append(",");
		sb.append("\"nodeOrder\":");
		sb.append("\"");
		sb.append(this.getNodeOrder());
		sb.append("\"");
		sb.append(",");
		sb.append("\"portalContainerId\":");
		sb.append("\"");
		sb.append(this.getPortalContainer().getId());
		sb.append("\"");
		if(null!=this.getParentArticleCategory()){
			sb.append(",");
			sb.append("\"parentCategoryId\":");
			sb.append("\"");
			sb.append(this.getParentArticleCategory().getId());
			sb.append("\"");
			sb.append(",");
			sb.append("\"parentCategoryName\":");
			sb.append("\"");
			sb.append(this.getParentArticleCategory().getName());
			sb.append("\"");
		}
		sb.append("}");
		return sb.toString();
	}
}
