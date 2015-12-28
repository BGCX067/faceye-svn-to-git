package com.faceye.components.navigation.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.faceye.components.navigation.dao.model.Category;
import com.faceye.components.navigation.dao.model.Feed;
import com.faceye.components.navigation.service.iface.IFeedParseService;
import com.faceye.components.navigation.service.iface.IFeedService;
import com.faceye.components.navigation.service.iface.IUserResourceCategoryService;
import com.faceye.core.service.security.model.Column;
import com.faceye.core.util.helper.StringPool;
import com.faceye.core.web.controller.ExtTemplateAction;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

public class FeedAction extends ExtTemplateAction {
	private IFeedService feedService = null;
	private IFeedParseService feedParseService = null;
	private IUserResourceCategoryService userResourceCategoryService=null;

	public IFeedService getFeedService() {
		return feedService;
	}

	public IFeedParseService getFeedParseService() {
		return feedParseService;
	}

	public void setFeedParseService(IFeedParseService feedParseService) {
		this.feedParseService = feedParseService;
	}

	public void setFeedService(IFeedService feedService) {
		this.feedService = feedService;
	}

	@Override
	protected void onInitEntity(HttpServletRequest request, Object o,
			ActionForm form) {
		// TODO Auto-generated method stub
		Map params = this.getRequestParameterMap(request);
		String columnId = "";
		String categoryId = "";
		if (params.containsKey("columnId")) {
			if (StringUtils.isNotEmpty(params.get("columnId").toString())) {
				columnId = params.get("columnId").toString();
			}
		}
		if (params.containsKey("categoryId")) {
			if (StringUtils.isNotEmpty(params.get("categoryId").toString())) {
				categoryId = params.get("categoryId").toString();
			}
		}
		if (StringUtils.isNotEmpty(columnId)) {
			Column column = (Column) this.getEntity(Column.class, columnId);
			((Feed) o).setColumn(column);
		}
		if (StringUtils.isNotEmpty(categoryId)) {
			Category category = (Category) this.getEntity(Category.class,
					categoryId);
			((Feed) o).setCategory(category);
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
		return Feed.class;
	}

	protected String getPageJson(HttpServletRequest request) {
		String json = "";
		String node = this.getHttp()
				.getParameter(request, StringPool.TREE_NODE);
		int startIndex = this.getHttp().getCurrentIndex(request);
		if (StringUtils.isNotEmpty(node)) {
			json = this.getFeedService().getFeeds(node, startIndex);
		} else {
			json = this.getFeedService().getFeeds(startIndex);
		}
		return json;
	}

	/**
	 * 读取Feed数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward parseFeed(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = super.getRequestParameterMap(request);
		String feedUrl = "";
		String json = "";
		if (params.containsKey(StringPool.FEED_FEED)) {
			if (params.get(StringPool.FEED_FEED) != null) {
				feedUrl = params.get(StringPool.FEED_FEED).toString();
			}
		}
		if (StringUtils.isEmpty(feedUrl)) {
			String feedId = "";
			if (params.containsKey(StringPool.ENTITY_ID)) {
				feedId = params.get(StringPool.ENTITY_ID).toString();
			}
			if (StringUtils.isNotEmpty(feedId)) {
				Feed feed = (Feed) this.getEntity(Feed.class, feedId);
				feedUrl = feed.getUrl();
			}
		}
		if (StringUtils.isNotEmpty(feedUrl)) {
			SyndFeedOutput output = new SyndFeedOutput();
			SyndFeed feed = this.getFeedParseService().getSyndFeed(feedUrl);
			response.setCharacterEncoding("UTF-8");
			try {
				response.setContentType("text/xml ");
				PrintWriter out = (PrintWriter) response.getWriter();
				String feedContent = output.outputString(feed).toString();
				out.print(feedContent);
				// 发布为feed
				// output.output(feed, response.getWriter());
			} catch (FeedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}
/*
 * 取得feed，供用户使用。
 */
	public ActionForward getFeeds(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = this.getRequestParameterMap(request);
		String columnId = "";
		String categoryId = "";
		String json="";
//		String userId=this.getHttp().getUserId(request).toString();
		String userId=this.getUser(request).getId();
		int startIndex=this.getHttp().getCurrentIndex(request);
		int pageSize=this.getHttp().getCurrentPageSize(request);
		if (!params.containsKey("columnId")) {
			columnId = "402881ce1752f82401175300d84f0003";
		} else {
			if (null != params.get("columnId")) {
				columnId = params.get("columnId").toString();
			} else {
				columnId = "402881ce1752f82401175300d84f0003";
			}
		}
		if (params.containsKey("categoryId")) {
			if (null != params.get("categoryId")) {
				categoryId = params.get("categoryId").toString();
			}
		}
		json=this.getFeedService().getFeeds(columnId, categoryId, userId, startIndex, pageSize);
		this.jsonPrint(response, json);
		return null;
	}
	
	
	public ActionForward remove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Serializable [] ids=this.getHttp().getParameter(request, StringPool.ENTITY_IDS).split(StringPool.ENTITY_IDS_SPLIT_WITH);
		if(null!=ids){
			for(int i=0;i<ids.length;i++){
			this.getFeedService().removeFeed(ids[i]);
			}
		}
		return null;
	}
	/**
	 * 用户订阅feed
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward subscribe(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
        Map params=this.getRequestParameterMap(request);
        String feedId=params.get("feedId").toString();
        String userResourceCategoryId=params.get("categoryId").toString();
        this.getUserResourceCategoryService().saveOrUpdateBuildRelationShipBetweenFeedAndUserResourceCategory(feedId, userResourceCategoryId);
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
