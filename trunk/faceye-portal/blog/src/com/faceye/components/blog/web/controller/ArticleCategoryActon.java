package com.faceye.components.blog.web.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.faceye.components.blog.dao.model.ArticleCategory;
import com.faceye.components.blog.service.iface.IArticleCategoryService;
import com.faceye.components.portal.dao.model.PortalContainer;
import com.faceye.components.portal.service.iface.IPortalContainerService;
import com.faceye.core.service.security.service.iface.ITreeService;
import com.faceye.core.util.helper.CollectionUtil;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.web.controller.ExtTemplateAction;

public class ArticleCategoryActon extends ExtTemplateAction {
	private IArticleCategoryService articleCategoryService = null;
	private IPortalContainerService portalContainerService = null;
	private ITreeService treeService = null;

	public ITreeService getTreeService() {
		return treeService;
	}

	public void setTreeService(ITreeService treeService) {
		this.treeService = treeService;
	}

	@Override
	protected void onInitEntity(HttpServletRequest request, Object o,
			ActionForm form) {
		// TODO Auto-generated method stub
		Map map = this.getRequestParameterMap(request);
		ArticleCategory articleCategory = (ArticleCategory) o;
		Object obj = CollectionUtil.getCollectionUtil().getMapValueByKey(map,
				"parentCategoryId");
		if (null == articleCategory.getPortalContainer()) {
			PortalContainer portalContainer = this.getPortalContainerService()
					.getPortalContainerByUserId(
							this.getHttp().getUserId(request));
			articleCategory.setPortalContainer(portalContainer);
		}
		if (null != obj && StringUtils.isNotEmpty(obj.toString())
				&& !obj.toString().equals("source")) {
			ArticleCategory parentArticleCategory = (ArticleCategory) this
					.getArticleCategoryService().getObject(
							ArticleCategory.class, obj.toString());
			articleCategory.setParentArticleCategory(parentArticleCategory);
		}
		if (null == articleCategory.getId()
				|| StringUtils.isEmpty(articleCategory.getId().toString())) {
			Integer order;
			if (null == articleCategory.getParentArticleCategory()) {
				order = this.getArticleCategoryService()
						.getNextArticleCategoryOrder(
								this.getHttp().getUserId(request), null);
			} else {
				order = this.getArticleCategoryService()
						.getNextArticleCategoryOrder(
								this.getHttp().getUserId(request),
								articleCategory.getParentArticleCategory()
										.getId());
			}
			articleCategory.setNodeOrder(order);
		}
	}

	@Override
	protected void onInitForm(HttpServletRequest request, ActionForm form,
			Object o) {
		// TODO Auto-generated method stub

	}

	public IArticleCategoryService getArticleCategoryService() {
		return articleCategoryService;
	}

	public void setArticleCategoryService(
			IArticleCategoryService articleCategoryService) {
		this.articleCategoryService = articleCategoryService;
	}

	/**
	 * 生成用户文章分类树结构
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward tree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Serializable userId = this.getUser(request).getId();
		String node = this.getHttp().getParameter(request, "node");
		String json = "";
		PaginationSupport ps = this.getArticleCategoryService()
				.getAllArticleCategoriesByUserId(userId);
		List treeItems = this.getArticleCategoryService().toTreeStructItems(
				ps.getItems());
		if (null == ps.getItems() || ps.getItems().isEmpty()) {
			this.getArticleCategoryService()
					.saveInitUserArticleCategory(userId);
			ps = this.getArticleCategoryService()
					.getAllArticleCategoriesByUserId(userId);
			treeItems = this.getArticleCategoryService().toTreeStructItems(
					ps.getItems());
		}
		if (node.equals("source") || node.startsWith("ynode")) {
			json = this.getTreeService().treeJSON(treeItems);
		} else {
			json = this.getTreeService().treeJSON(treeItems, node);
		}
		this.jsonPrint(response, json);
		return null;
	}

	/**
	 * 取得用户所有的文章分类 用于首页非树型结构的显示,以及分类的列表信息维护.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getAllUserArticleCategories(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Serializable userId = this.getHttp().getUserId(request);
		PaginationSupport ps = this.getArticleCategoryService()
				.getAllArticleCategoriesByUserId(userId);
		this.jsonPrint(response, ps.json());
		return null;
	}

	public ActionForward order(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = this.getRequestParameterMap(request);
		Serializable userId = this.getHttp().getUserId(request);
		Integer index = null;
		Serializable articleCategoryId = CollectionUtil.getCollectionUtil()
				.getMapValueByKey(params, "articleCategoryId").toString();
		if (StringUtils.isNotEmpty(CollectionUtil.getCollectionUtil()
				.getMapValueByKey(params, "index").toString())) {
			index = Integer.parseInt(CollectionUtil.getCollectionUtil()
					.getMapValueByKey(params, "index").toString());
		} else {
			index = new Integer(0);
		}
		Serializable oldParentId = CollectionUtil.getCollectionUtil()
				.getMapValueByKey(params, "oldParentId").toString();
		if (oldParentId.toString().startsWith("source")) {
			oldParentId = null;
		}
		Serializable newParentId = CollectionUtil.getCollectionUtil()
				.getMapValueByKey(params, "newParentId").toString();
		if (newParentId.toString().startsWith("source")) {
			newParentId = null;
		}
		this.getArticleCategoryService().saveArticleCategoryOrder(userId,
				articleCategoryId, index, oldParentId, newParentId);
		return null;
	}

	public IPortalContainerService getPortalContainerService() {
		return portalContainerService;
	}

	public void setPortalContainerService(
			IPortalContainerService portalContainerService) {
		this.portalContainerService = portalContainerService;
	}

}
