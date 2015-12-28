package com.faceye.components.blog.web.controller;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.faceye.components.blog.dao.model.Article;
import com.faceye.components.blog.dao.model.ArticleCategory;
import com.faceye.components.blog.dao.model.ArticleClickCount;
import com.faceye.components.blog.service.iface.IArticleClickCountService;
import com.faceye.components.blog.service.iface.IArticleService;
import com.faceye.core.service.security.model.User;
import com.faceye.core.util.helper.CollectionUtil;
import com.faceye.core.util.helper.JSONUtil;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.util.helper.StringPool;
import com.faceye.core.web.controller.ExtTemplateAction;

public class ArticleAction extends ExtTemplateAction {
	private IArticleService articleService = null;
	private IArticleClickCountService articleClickCountService = null;

	public IArticleClickCountService getArticleClickCountService() {
		return articleClickCountService;
	}

	public void setArticleClickCountService(
			IArticleClickCountService articleClickCountService) {
		this.articleClickCountService = articleClickCountService;
	}

	public IArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(IArticleService articleService) {
		this.articleService = articleService;
	}

	@Override
	protected void onInitEntity(HttpServletRequest request, Object o,
			ActionForm form) {
		// TODO Auto-generated method stub
		Article article = (Article) o;
		Map map = this.getRequestParameterMap(request);
		if (null == article.getArticleCategory()) {
			Object categoryId = CollectionUtil.getCollectionUtil()
					.getMapValueByKey(map, "categoryId");
			if (null != categoryId) {
				ArticleCategory articleCategory = (ArticleCategory) this
						.getArticleService().getObject(ArticleCategory.class,
								categoryId.toString());
				article.setArticleCategory(articleCategory);
			}
		}
		if (StringUtils.isNotEmpty(article.getContent())) {
			String content = article.getContent();
			article.setContent(content.replaceAll("\n", "")
					.replaceAll("\r", ""));
		}
	}

	@Override
	protected void onInitForm(HttpServletRequest request, ActionForm form,
			Object o) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return取得用户的所有article,如果分类不为空,则根据用户ID,分类进行查询.
	 */
	public ActionForward getArticles(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map map = this.getRequestParameterMap(request);
		PaginationSupport ps = null;
		User user = super.getUser(request);
		Serializable userId = user.getId();
		Object categoryId = CollectionUtil.getCollectionUtil()
				.getMapValueByKey(map, "categoryId");
		int startIndex = this.getHttp().getCurrentIndex(request);
		int pageSize = this.getHttp().getPageSize(request);
		if (null != categoryId) {
			ps = this.getArticleService()
					.getArticlesByUserIdAndArticleCategoryId(userId,
							categoryId.toString(), startIndex, pageSize);
		} else {
			ps = this.getArticleService().getArticlesByUserId(userId,
					startIndex, pageSize);
		}
		this.jsonPrint(response, ps.json());
		return null;
	}

	/**
	 * 取得系统中新加入文章排行榜
	 * 
	 * @return
	 */
	public ActionForward getSystemNewerArticles(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		PaginationSupport ps = this.getArticleService()
				.getNewerArticleOrderList(
						this.getHttp().getCurrentIndex(request),
						this.getHttp().getPageSize(request));
		if (null != ps) {
			this.jsonPrint(response, ps.json());
		}
		return null;
	}
	
	/**
	 * 博文检索
	 */
	public ActionForward search(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	    String searchMarker=this.get("searchMarker", request);
	    
		return null;
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = super.getRequestParameterMap(request);
		String json = "";
		try {
			Class clazz = Class.forName(params.get(StringPool.ENTITY_CLASS)
					.toString());
			Object o = super.getEntity(clazz, request);
			ArticleClickCount ac = new ArticleClickCount();
			User user = this.getUser(request);
			if (null == user) {
				user = (User) super.getEntity(User.class,
						StringPool.USER_GUEST_ID);
			}
			Article article = (Article) o;
			ac.setArticle(article);
			ac.setUser(user);
			ac.setIp(request.getRemoteHost());
			this.getArticleClickCountService().save(ac);
			json = this.object2Json(o);
			this.jsonPrint(response, JSONUtil.rowJson(json));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward remove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Serializable ids = this.get("ids", request);
		if (null != ids) {
			String[] id = ids.toString().split("_");
			for (int i = 0; i < id.length; i++) {
				this.getArticleService().remove(id[i]);
			}
		}
		return null;
	}
}
