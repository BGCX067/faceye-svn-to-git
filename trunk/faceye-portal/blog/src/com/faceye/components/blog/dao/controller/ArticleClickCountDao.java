package com.faceye.components.blog.dao.controller;

import java.io.Serializable;

import com.faceye.components.blog.dao.iface.IArticleClickCountDao;
import com.faceye.components.blog.dao.model.ArticleClickCount;
import com.faceye.core.componentsupport.dao.controller.DomainDao;
import com.faceye.core.util.helper.PaginationSupport;

public class ArticleClickCountDao extends DomainDao implements IArticleClickCountDao {

	public PaginationSupport getPageArticleClickHistory(Serializable articleId,
			Serializable userId, int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		String hql=" from "+ArticleClickCount.class.getName()+" a where 1=1 ";
		if(null!=articleId){
			hql+=" and a.article.id=\'"+articleId+"\'";
		}
		if(null!=userId){
			hql+=" and a.user.id=\'"+userId+"\'";
		}
		return this.getPageByHQL(hql, pageSize, startIndex);
	}

	public void remove(Serializable id) {
		// TODO Auto-generated method stub
		super.removeObject(ArticleClickCount.class, id);
	}

	public void save(ArticleClickCount entity) {
		// TODO Auto-generated method stub
		super.saveOrUpdateObject(entity);
	}

}
