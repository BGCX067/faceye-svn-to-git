package com.faceye.components.blog.web.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import com.faceye.components.blog.service.iface.IArticleClickCountService;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.web.controller.ExtTemplateAction;

public class ArticleClickAction extends ExtTemplateAction {
    private IArticleClickCountService articleClickCountService=null;
	@Override
	protected void onInitEntity(HttpServletRequest request, Object o,
			ActionForm form) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onInitForm(HttpServletRequest request, ActionForm form,
			Object o) {
		// TODO Auto-generated method stub

	}
	
	protected String getPageJson(HttpServletRequest request) {
		Serializable articleId=this.get("articleId", request);
		PaginationSupport ps=this.getArticleClickCountService().getPageArticleClickHistory(articleId, null, this.getHttp().getCurrentIndex(request), this.getHttp().getPageSize(request));
		return ps.json();
	}

	public IArticleClickCountService getArticleClickCountService() {
		return articleClickCountService;
	}

	public void setArticleClickCountService(
			IArticleClickCountService articleClickCountService) {
		this.articleClickCountService = articleClickCountService;
	}


}
