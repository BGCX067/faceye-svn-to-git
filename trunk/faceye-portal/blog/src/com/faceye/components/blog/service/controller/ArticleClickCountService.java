package com.faceye.components.blog.service.controller;

import java.io.Serializable;

import com.faceye.components.blog.dao.iface.IArticleClickCountDao;
import com.faceye.components.blog.dao.model.ArticleClickCount;
import com.faceye.components.blog.service.iface.IArticleClickCountService;
import com.faceye.core.componentsupport.service.controller.DomainService;
import com.faceye.core.util.helper.PaginationSupport;

public class ArticleClickCountService extends DomainService implements
		IArticleClickCountService {
    private IArticleClickCountDao articleClickCountDao=null;
	public IArticleClickCountDao getArticleClickCountDao() {
		return articleClickCountDao;
	}

	public void setArticleClickCountDao(IArticleClickCountDao articleClickCountDao) {
		this.articleClickCountDao = articleClickCountDao;
	}

	public PaginationSupport getPageArticleClickHistory(Serializable articleId,
			Serializable userId, int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		return this.getArticleClickCountDao().getPageArticleClickHistory(articleId, userId, startIndex, pageSize);
	}

	public void remove(Serializable id) {
		// TODO Auto-generated method stub
		this.getArticleClickCountDao().remove(id);
	}

	public void save(ArticleClickCount entity) {
		// TODO Auto-generated method stub
		this.getArticleClickCountDao().save(entity);
	}

}
