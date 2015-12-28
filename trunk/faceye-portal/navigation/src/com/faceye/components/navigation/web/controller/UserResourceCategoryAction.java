package com.faceye.components.navigation.web.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.faceye.components.navigation.dao.model.UserResourceCategory;
import com.faceye.components.navigation.service.iface.IUserResourceCategoryService;
import com.faceye.core.service.security.model.User;
import com.faceye.core.service.security.service.iface.ITreeService;
import com.faceye.core.util.helper.StringPool;
import com.faceye.core.web.controller.ExtTemplateAction;

public class UserResourceCategoryAction extends ExtTemplateAction {
	private IUserResourceCategoryService userResourceCategoryService = null;
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

	}

	@Override
	protected void onInitForm(HttpServletRequest request, ActionForm form,
			Object o) {
		// TODO Auto-generated method stub

	}

	/**
	 * 保存用户feed 资源分类
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String userId = ((User) this.getHttp().getObjectFormSession(
				StringPool.USER_IN_SESSION, request)).getId();
		User user = (User) this.getEntity(User.class, userId);
		Map params = this.getRequestParameterMap(request);
		UserResourceCategory uc = null;
		UserResourceCategory parentUc = null;
		if (params.containsKey("id") && null != params.get("id")
				&& StringUtils.isNotEmpty(params.get("id").toString())) {
			uc = (UserResourceCategory) this.getEntity(
					UserResourceCategory.class, params.get("id").toString());
		} else {
			uc = new UserResourceCategory();
		}
		uc.setName(params.get("name").toString());
		uc.setDescription(params.get("description").toString());
		if (params.get("parentId").toString().equals(StringPool.TREE_ROOT_ID)) {
			parentUc = null;
		} else {
			parentUc = (UserResourceCategory) this.getEntity(
					UserResourceCategory.class, params.get("parentId")
							.toString());
		}
		this.getUserResourceCategoryService().saveOrUpdateUserResourceCategory(
				user, parentUc, uc);
		return null;
	}

	/**
	 * 构造用户的feed目录结构，本树中包括分类节点，以及feed节点。
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward tree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String userId = "";
		
		Object user = this.getHttp().getObjectFormSession(
				StringPool.USER_IN_SESSION, request);
		if (null != user) {
			userId = ((User) user).getId();
		} else {
			userId = StringPool.USER_GUEST_ID;
		}
		// List
		// categorys=this.getUserResourceCategoryService().getTransferUserResourceCategoryByUser(userId);
		List categorysAndFeeds = this.getUserResourceCategoryService()
				.getUserResourceCategoryAndFeedsTree(userId);
		String json = "";
		String node = super.getHttp().getParameter(request, "node");
		if (node.equals("source")) {
			json = this.getTreeService().treeJSON(categorysAndFeeds);
		} else if (node.startsWith("ynode")) {
			UserResourceCategory uc = this.getUserResourceCategoryService()
					.getRootUserResourceCategory(userId);
			json = this.getTreeService()
					.treeJSON(categorysAndFeeds, uc.getId());
		} else {
			json = this.getTreeService().treeJSON(categorysAndFeeds, node);
		}
		this.jsonPrint(response, json);
		return null;
	}

	/**
	 * 用户资源分类tree.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward userResourceCategoryOnlyTree(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String userId = "";
		if (null != this.getHttp().getObjectFormSession(
				StringPool.USER_IN_SESSION, request)) {
			userId = ((User) this.getHttp().getObjectFormSession(
					StringPool.USER_IN_SESSION, request)).getId();
		} else {
			userId = StringPool.USER_GUEST_ID;
		}
		List categoryes = this.getUserResourceCategoryService()
				.getTransferUserResourceCategoryByUser(userId);
		String json = "";
		String node = super.getHttp().getParameter(request, "node");
		if (node.equals("source")) {
			json = this.getTreeService().treeJSON(categoryes);
		} else {
			json = this.getTreeService().treeJSON(categoryes, node);
		}
		this.jsonPrint(response, json);
		return null;
	}

	/**
	 * 转向个人feed导航面板
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward myFeedHome(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String userId = "";
		Object user = this.getHttp().getObjectFormSession(
				StringPool.USER_IN_SESSION, request);
		if (null != user) {
			userId = ((User) user).getId();
		} else {
			userId = StringPool.USER_GUEST_ID;
		}
		if (!this.getUserResourceCategoryService()
				.isExistsAtLeastUserResourceCategory(userId)) {
			this.getUserResourceCategoryService()
					.saveOrUpdateDefaultUserResourceCategory(userId);
		}
		return mapping.findForward("feedReader");
	}

	/**
	 * 取得用户的默认分类或根分类
	 */

	public ActionForward getUserDefaultRoot(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String userId = "";
		if (null != this.getHttp().getObjectFormSession(
				StringPool.USER_IN_SESSION, request)) {
			userId = ((User) this.getHttp().getObjectFormSession(
					StringPool.USER_IN_SESSION, request)).getId();
		} else {
			userId = StringPool.USER_GUEST_ID;
		}
		UserResourceCategory uc = this.getUserResourceCategoryService()
				.getRootUserResourceCategory(userId);
		StringBuffer sb = new StringBuffer();
		sb.append("[{");
		sb.append("\"id\"");
		sb.append(":");
		sb.append("\"");
		sb.append(uc.getId());
		sb.append("\"");
		sb.append(",");
		sb.append("\"text\"");
		sb.append(":");
		sb.append("\"");
		sb.append(uc.getName());
		sb.append("\"");
		sb.append(",");
		sb.append("\"");
		sb.append("draggable");
		sb.append("\"");
		sb.append(":");
		sb.append("false");
		sb.append("}]");
		this.jsonPrint(response, sb.toString());
		return null;
	}

	/**
	 * 节点顺序重新排列
	 */
	public ActionForward order(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Serializable userId = super.getHttp().getUserId(request);
		Map params = this.getRequestParameterMap(request);
		String nodeId = params.get("nodeId").toString();
		String oldParentId = params.get("oldParentId").toString();
		String newParentId = params.get("newParentId").toString();
		if(oldParentId.startsWith("ynode")){
			oldParentId=this.getUserResourceCategoryService().getRootUserResourceCategory(this.getHttp().getUserId(request)).getId();
		}
		if(newParentId.startsWith("ynode")){
			newParentId=this.getUserResourceCategoryService().getRootUserResourceCategory(this.getHttp().getUserId(request)).getId();
		}
		Integer index = StringUtils.isEmpty(params.get("index").toString()) ? new Integer(
				0)
				: Integer.parseInt(params.get("index").toString());
		this.getUserResourceCategoryService()
				.saveUserResourceCategoryAndFeedOrder(nodeId, oldParentId,
						newParentId, index, userId);
		return null;
	}

	public ActionForward remove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Serializable id = this.getHttp().getEntityId(request);
		this.getUserResourceCategoryService().removeUserResourceCategory(id);
		return null;
	}

	public ActionForward removeRelationShipBettonFeedAndUserResourceCategory(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Map params = this.getRequestParameterMap(request);
		Serializable userResourceCategoryId = params.get("categoryId")
				.toString();
		if (userResourceCategoryId.toString().startsWith("ynode")) {
			userResourceCategoryId = this.getUserResourceCategoryService()
					.getRootUserResourceCategory(
							this.getHttp().getUserId(request)).getId();
		}
		Serializable feedId = params.get("feedId").toString();
		this.getUserResourceCategoryService()
				.removeRelationShipBetweenFeedAndUserResourceCategory(feedId,
						userResourceCategoryId);
		return null;
	}

	public IUserResourceCategoryService getUserResourceCategoryService() {
		return userResourceCategoryService;
	}

	public void setUserResourceCategoryService(
			IUserResourceCategoryService userResourceCategoryService) {
		this.userResourceCategoryService = userResourceCategoryService;
	}

}
