package com.faceye.components.navigation.service.iface;

import com.faceye.core.util.helper.PaginationSupport;
import com.sun.syndication.feed.synd.SyndFeed;

public interface IFeedParseService {

	/**
	 * 取得feed分页数据
	 * @param url
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public abstract PaginationSupport getFeeds(String url, int start,
			int pageSize);

	public abstract String getAllFeeds(String url);
	
	public SyndFeed getSyndFeed(String url);

}