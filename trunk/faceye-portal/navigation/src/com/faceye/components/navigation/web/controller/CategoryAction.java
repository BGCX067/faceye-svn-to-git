package com.faceye.components.navigation.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.faceye.components.navigation.dao.model.Category;
import com.faceye.components.navigation.service.iface.ICategoryService;
import com.faceye.core.service.security.service.iface.ITreeService;
import com.faceye.core.util.helper.JSONUtil;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.util.helper.StringPool;
import com.faceye.core.web.controller.ExtTemplateAction;

public class CategoryAction extends ExtTemplateAction {
	private ICategoryService categoryService = null;
	private ITreeService treeService = null;

	public ICategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	protected void onInitEntity(HttpServletRequest request, Object o,
			ActionForm form) {
		// TODO Auto-generated method stub
		String parentId = get("parentId", request);

		if (parentId != null) {
			if (StringUtils.equals(parentId, "source")) {
				((Category) o).setParentCategory(null);
			} else {
				((Category) o).setParentCategory((Category) this.getEntity(
						Category.class, parentId));
			}
		} else {
			((Category) o).setParentCategory(null);
		}

	}

	@Override
	protected void onInitForm(HttpServletRequest request, ActionForm form,
			Object o) {
		// TODO Auto-generated method stub

	}

	/**
	 * 生成分类树结构,包括传统导航和feed导航
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward tree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List categorys = this.getCategoryService().transferCategories();
		String json = "";
		if (super.getHttp().getParameter(request, "node").equals("source")) {
			json = this.getTreeService().treeJSON(categorys);
		} else {
			json = this.getTreeService().treeJSON(categorys,
					super.getHttp().getParameter(request, "node").toString());
		}
		this.jsonPrint(response, json);
		return null;
	}

	/**
	 * 取得 feed category 的tree
	 */
	public ActionForward getFeedCategoryTree(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		PaginationSupport ps = this.getCategoryService()
				.getAllChildrenCategories(StringPool.CATEGORY_FEED_ID);
		List items = ps.getItems();
		String json = this.getTreeService().treeJSON(items);
		this.jsonPrint(response, json);
		return null;
	}
	/**
	 * 
	 * 取得tradition category tree
	 */
	public ActionForward getTraditionCategoryTree(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		PaginationSupport ps = this.getCategoryService()
				.getAllChildrenCategories(StringPool.CATEGORY_TRADITION_ID);
		List items = ps.getItems();
		String json = this.getTreeService().treeJSON(items);
		this.jsonPrint(response, json);
		return null;
	}
	/**
	 * 生成用于单个网站分类维护的树开结构.
	 * 主要用于分类维护中,节点的明细显示,节点编辑,为节点添加子分类(默认为添加当前分类的子分类,也可以添加为其它分类的子分类
	 */
	public ActionForward node(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		StringBuffer json = new StringBuffer();
		json.append("[");
		// 添加"栏目明细"节点
		json.append("{");
		json.append("\"text\":\"Detail OF Category\"");
		json.append(",");
		json.append("\"id\":\"1\"");
		json.append(",");
		json.append("\"cls\":\"file\"");
		json.append(",");
		json.append("\"leaf\":true");
		json.append(",");
		json.append("\"link\":\"columnAciton.do\"");
		json.append("}");
		// 添加"添加子栏目"节点
		json.append(",");
		json.append("{");
		json.append("\"text\":\"Add Sub Category\"");
		json.append(",");
		json.append("\"id\":\"2\"");
		json.append(",");
		json.append("\"cls\":\"file\"");
		json.append(",");
		json.append("\"leaf\":true");
		json.append(",");
		json.append("\"link\":\"#\"");
		json.append("}");
		// 添加"编辑栏目"节点
		json.append(",");
		json.append("{");
		json.append("\"text\":\"Edit Category\"");
		json.append(",");
		json.append("\"id\":\"3\"");
		json.append(",");
		json.append("\"cls\":\"file\"");
		json.append(",");
		json.append("\"leaf\":true");
		json.append(",");
		json.append("\"link\":\"#\"");
		json.append("}");
		// 添加"删除栏目"节点
		json.append(",");
		json.append("{");
		json.append("\"text\":\"Remove\"");
		json.append(",");
		json.append("\"id\":\"4\"");
		json.append(",");
		json.append("\"cls\":\"file\"");
		json.append(",");
		json.append("\"leaf\":true");
		json.append(",");
		json.append("\"link\":\"columnAciton.do\"");
		json.append("}");
		json.append("]");
		this.jsonPrint(response, json.toString());
		return null;
	}

	public ITreeService getTreeService() {
		return treeService;
	}

	public void setTreeService(ITreeService treeService) {
		this.treeService = treeService;
	}

	/**
	 * 取得所有feed分类
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getFeedCategorise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		PaginationSupport ps = this.getCategoryService()
				.getAllChildrenCategories(StringPool.CATEGORY_FEED_ID);
		List items = ps.getItems();
		String json = this.getTreeService().treeJSON(items);
		this.jsonPrint(response, JSONUtil.pageJson(items.size(), json));
		return null;
	}

	/**
	 * 取得传统导航的分类.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getTraditionCategorise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		PaginationSupport ps = this.getCategoryService()
				.getAllChildrenCategories(StringPool.CATEGORY_TRADITION_ID);
		List items = ps.getItems();
		String json = this.getTreeService().treeJSON(items);
		this.jsonPrint(response, JSONUtil.pageJson(items.size(), json));
		return null;
	}
}
