package com.faceye.components.portal.web.controller;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.faceye.components.portal.dao.model.PortalContainer;
import com.faceye.components.portal.service.iface.IPortalContainerService;
import com.faceye.core.service.security.model.User;
import com.faceye.core.util.helper.JSONUtil;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.util.helper.StringPool;
import com.faceye.core.web.controller.ExtTemplateAction;

public class PortalContainerAction extends ExtTemplateAction {
	private IPortalContainerService portalContainerService = null;

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

	public IPortalContainerService getPortalContainerService() {
		return portalContainerService;
	}

	public void setPortalContainerService(
			IPortalContainerService portalContainerService) {
		this.portalContainerService = portalContainerService;
	}

	/**
	 * 判断用户是否在自己的地盘
	 */
	public ActionForward isAtMyPlace(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Serializable userId = super.getHttp().getUserId(request);
		String username = this.get("user", request);
		StringBuffer json = new StringBuffer("{\"success\":true,\"rows\":[{\"");
		if (userId.equals(StringPool.USER_GUEST_ID)) {
			json.append("login\":\"no\"}]}");
		} else if (StringUtils.isNotEmpty(username)) {
			User user = (User) this.getService().getBaseService()
					.getBaseHibernateService().getObject(User.class,
							"username", username);
			if (user.getId().equals(userId)) {
				json.append("login\":\"yes\"}]}");
			} else {
				json.append("login\":\"no\"}]}");
			}
		} else {
			json.append("login\":\"yes\"}]}");
		}
		jsonPrint(response, json.toString());
		return null;
	}

	/*
	 * 进入用户自己的portal
	 */
	public ActionForward my(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Serializable userId = super.getHttp().getUserId(request);
		String username = this.get("user", request);
		if (StringUtils.isNotEmpty(username)) {
			request.getSession().setAttribute(StringPool.BLOG_USER, username);
		} else {
			if (request.getSession().getAttribute(StringPool.BLOG_USER) != null) {
				request.getSession().removeAttribute(StringPool.BLOG_USER);
			}
		}
		if (!this.getPortalContainerService().isExistsUserPortalContainer(
				userId)) {
			this.getPortalContainerService().saveDefaultUserPortalContainer(
					userId);
		}
		return mapping.findForward("portal");
	}

	/**
	 * 添加用户自定义的portal
	 */
	public ActionForward saveUserDefinePortal(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Serializable userId = super.getHttp().getUserId(request);
		this.getPortalContainerService().saveDefaultUserDefinePortalContainer(
				userId);
		return null;
	}

	/**
	 * 取得用户的portal容器
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getPortalContainer(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Serializable userId = super.getHttp().getUserId(request);
		String username = this.get("user", request);
		if (StringUtils.isNotEmpty(username)) {
			User user = (User) this.getService().getBaseService()
					.getBaseHibernateService().getObject(User.class,
							"username", username);
			if (null != user) {
				userId = user.getId();
			}
		}

		PortalContainer pc = this.getPortalContainerService()
				.getPortalContainerByUserId(userId);
		this.jsonPrint(response, JSONUtil.rowJson(pc.json()));
		return null;
	}

	public ActionForward getNewerPortalContainer(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		PaginationSupport ps = this.getPortalContainerService()
				.getNewerPortalContainers(
						this.getHttp().getCurrentIndex(request),
						this.getHttp().getPageSize(request));
		if (null != ps) {
			this.jsonPrint(response, ps.json());
		}
		return null;
	}

	/**
	 * portlet订阅
	 * 
	 * @return
	 */
	public ActionForward portletSubscribe(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Map params = this.getRequestParameterMap(request);
		String portalId = params.get("portalId").toString();
		String portletId = params.get("portletId").toString();
		this.getPortalContainerService().saveUserPortletSubscribe(portalId,
				portletId);
		return null;
	}

	public ActionForward removeUserPortletSubscribe(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Map params = this.getRequestParameterMap(request);
		String portletId = params.get("portletId").toString();
		Serializable portalId = params.get("portalId").toString();
		this.getPortalContainerService().removeUserPorletSubscribe(portletId,
				portalId);
		return null;
	}

	/**
	 * 移动 portlet
	 */
	public ActionForward movePortlet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = this.getRequestParameterMap(request);
		String portalId = params.get("portalId").toString();
		String portletId = params.get("portletId").toString();
		String portalColumnId = params.get("portalColumnId").toString();
		int index = Integer.parseInt(params.get("index").toString());
		this.getPortalContainerService().saveChangePortletPosition(portletId,
				portalColumnId, portalId, index);
		return null;
	}

}
