package com.faceye.components.blog.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.faceye.components.blog.service.iface.IBlogService;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.web.controller.ExtTemplateAction;

public class BlogManagerAction extends ExtTemplateAction {
    private IBlogService blogService=null;
	public IBlogService getBlogService() {
		return blogService;
	}

	public void setBlogService(IBlogService blogService) {
		this.blogService = blogService;
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
	
	public ActionForward blogManagerTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String json=this.getService().getSystemConfig().getValueByKey("faceye.user.blog.manager.tree.struct");
		this.jsonPrint(response, json);
		return null;
	}
	/**
	 * 博客点击排行榜
	 */
	
	public ActionForward blogClickOrderList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String timePeriod=this.get("timePeriod", request);
		PaginationSupport ps=this.getBlogService().getPageOfBlogClickHistoryOrderList(timePeriod, this.getHttp().getCurrentIndex(request), this.getHttp().getCurrentPageSize(request));
		String json=ps.json();
		this.jsonPrint(response, json);
		return null;
	}
  
}
