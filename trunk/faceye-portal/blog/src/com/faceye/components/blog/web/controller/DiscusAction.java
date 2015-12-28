package com.faceye.components.blog.web.controller;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.faceye.components.blog.dao.model.Article;
import com.faceye.components.blog.dao.model.Discus;
import com.faceye.components.blog.service.iface.IDiscusService;
import com.faceye.core.service.security.model.User;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.web.controller.ExtTemplateAction;

public class DiscusAction extends ExtTemplateAction {
	private IDiscusService discusService = null;

	@Override
	protected void onInitEntity(HttpServletRequest request, Object o,
			ActionForm form) {
		// TODO Auto-generated method stub
		Discus discus = (Discus) o;
		Map params = this.getRequestParameterMap(request);
		Serializable articleId = this.get("articleId", request);
		if (null != articleId) {
			Article article = (Article) this
					.getEntity(Article.class, articleId);

			discus.setArticle(article);
		}
		User user = this.getUser(request);
		if (null != user) {
			discus.setUser(user);
		}

	}

	@Override
	protected void onInitForm(HttpServletRequest request, ActionForm form,
			Object o) {
		// TODO Auto-generated method stub

	}

	public ActionForward getDiscusesOfArticle(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Serializable articleId = this.get("articleId", request);
		if (null != articleId) {
			PaginationSupport ps = this.getDiscusService()
					.getDiscusByArticleId(articleId,
							this.getHttp().getCurrentIndex(request),
							this.getHttp().getPageSize(request));
			this.jsonPrint(response, ps.json());
		}
		return null;
	}

	public IDiscusService getDiscusService() {
		return discusService;
	}

	public void setDiscusService(IDiscusService discusService) {
		this.discusService = discusService;
	}

}
