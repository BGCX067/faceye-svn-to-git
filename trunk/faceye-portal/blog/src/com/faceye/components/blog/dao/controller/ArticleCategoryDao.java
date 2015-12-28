package com.faceye.components.blog.dao.controller;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.faceye.components.blog.dao.iface.IArticleCategoryDao;
import com.faceye.components.blog.dao.model.ArticleCategory;
import com.faceye.core.componentsupport.dao.controller.DomainDao;
import com.faceye.core.util.helper.PaginationSupport;

public class ArticleCategoryDao extends DomainDao implements
		IArticleCategoryDao {

	public PaginationSupport getAllArticleCategoriesByUserId(Serializable userId) {
		// TODO Auto-generated method stub
		String hql = "from "
				+ ArticleCategory.class.getName()
				+ " a where a.portalContainer.user.id=:userId order by a.nodeOrder asc";
		List items = this.getAllByHQL(hql, "userId", userId);
		return new PaginationSupport(items);
	}

	public Integer getNextNodeOrder(Serializable userId,
			Serializable parentCategoryId) {
		// TODO Auto-generated method stub
		Integer  o = new Integer(0);
		String hql = "select max(a.nodeOrder) from "
				+ ArticleCategory.class.getName()
				+ " a where a.portalContainer.user.id=:userId";
		if (null != parentCategoryId
				&& StringUtils.isNotEmpty(parentCategoryId.toString())) {
			hql += " and a.parentArticleCategory.id=:parentCategoryId";
			List items = this.getAllByHQL(hql, new String[] { "userId",
					"parentCategoryId" }, new Object[] { userId,
					parentCategoryId });
			if (null != items && items.size() > 0) {
				if (items.get(0) != null) {
					o = (Integer) items.get(o) + 1;
				}
			}
			return o;
		} else {
			List items = this.getAllByHQL(hql, "userId", userId);
			if (null != items && items.size() > 0) {
				if (items.get(0) != null) {
					o = (Integer) items.get(o) + 1;
				}
			}
			return o;
		}
	}

}
