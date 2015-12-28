package com.faceye.components.navigation.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.faceye.components.navigation.dao.model.Category;
import com.faceye.components.navigation.dao.model.Tradition;
import com.faceye.components.navigation.service.iface.ITraditionService;
import com.faceye.core.service.security.model.Column;
import com.faceye.core.web.controller.ExtTemplateAction;

public class TraditionAction extends ExtTemplateAction {
	private ITraditionService traditionService = null;

	public ITraditionService getTraditionService() {
		return traditionService;
	}

	public void setTraditionService(ITraditionService traditionService) {
		this.traditionService = traditionService;
	}

	@Override
	protected void onInitEntity(HttpServletRequest request, Object o,
			ActionForm form) {
		// TODO Auto-generated method stub
		// 取得本导航所属栏目ID
		String columnId = this.get("columnId", request);
		// 取得本导向所属分类ID
		String categoryId = this.get("categoryId", request);
		if (StringUtils.isNotEmpty(columnId)) {
			Column column = (Column) this.getEntity(Column.class, columnId);
			((Tradition) o).setColumn(column);
		}
		if (StringUtils.isNotEmpty(categoryId)) {
			Category category = (Category) this.getEntity(Category.class,
					categoryId);
			((Tradition) o).setCategory(category);
		}

	}

	@Override
	protected void onInitForm(HttpServletRequest request, ActionForm form,
			Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Class getEntityClass() {
		// TODO Auto-generated method stub
		return Tradition.class;
	}

	/**
	 * 根据栏目取得传统导航分页数据
	 */
	protected String getPageJson(HttpServletRequest request) {
		Map params = this.getRequestParameterMap(request);
		if (params.containsKey("node")
				&& StringUtils.isNotEmpty(params.get("node").toString())) {
			return this.getTraditionService().getTraditions(
					params.get("node").toString(),
					this.getHttp().getCurrentIndex(request));
		} else {
			return this.getTraditionService().getTraditions(
					this.getHttp().getCurrentIndex(request));
		}
	}
	
	/**
	 * 根据导航分类（如门户,军事等取得导航数据）
	 * 
	 */
	public ActionForward getTraditions(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String categoryId=this.getHttp().getParameter(request, "categoryId");
		int startIndex=this.getHttp().getCurrentIndex(request);
		String json=this.getTraditionService().getPageTraditionsByCategory(categoryId, startIndex);
		this.jsonPrint(response, json);
		return null;
	}

}
