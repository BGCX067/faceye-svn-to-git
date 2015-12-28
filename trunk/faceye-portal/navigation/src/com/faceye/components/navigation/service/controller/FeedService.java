package com.faceye.components.navigation.service.controller;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.faceye.components.navigation.dao.iface.IFeedDao;
import com.faceye.components.navigation.dao.model.Feed;
import com.faceye.components.navigation.service.iface.IFeedService;
import com.faceye.core.service.controller.BaseHibernateService;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.util.helper.StringPool;

public class FeedService extends BaseHibernateService implements IFeedService {
	private IFeedDao feedDao = null;

	public IFeedDao getFeedDao() {
		return feedDao;
	}

	public void setFeedDao(IFeedDao feedDao) {
		this.feedDao = feedDao;
	}

	public String getFeeds(int startIndex) {
		// TODO Auto-generated method stub
		return this.getFeeds(null, startIndex);
	}

	public String getFeeds(Serializable columnId, int startIndex) {
		// TODO Auto-generated method stub
		PaginationSupport ps = this.getFeedDao().getFeeds(columnId, startIndex);
		StringBuffer json = new StringBuffer("[");
		if (ps != null && !ps.getItems().isEmpty()) {
			List items = ps.getItems();
			Iterator it = items.iterator();
			while (it.hasNext()) {
				Feed item = (Feed) it.next();
				json.append(item.json());
				json.append(StringPool.CHARACTER_COMMA);
			}
			json.deleteCharAt(json.lastIndexOf(StringPool.CHARACTER_COMMA));
		}
		json.append("]");
		return "{\"total\":" + ps.getTotalCount() + ",\"root\":"
				+ json.toString() + "}";
	}

	public String getFeeds(Serializable columnId, Serializable categoryId,
			Serializable userId, int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		return this.getFeedDao().getFeeds(columnId, categoryId, userId,
				startIndex, pageSize).json();
	}

	public void removeFeed(Serializable feedId) {
		// TODO Auto-generated method stub
		Feed feed = (Feed) this.loadObject(Feed.class, feedId);
		Set feedSubscribes = feed.getFeedSubscribes();
		if (null != feedSubscribes && !feedSubscribes.isEmpty()) {
			this.removeObjects(feedSubscribes);
		}
		Set feedSubscribeCount = feed.getFeedSubscribeCount();
		if (null != feedSubscribeCount && !feedSubscribeCount.isEmpty()) {
			this.removeObjects(feedSubscribeCount);
		}
		this.removeObject(feed);

	}

	public PaginationSupport getFeedsByCategory(Serializable categoryId,
			int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		return this.getFeedDao().getFeedsByCategory(categoryId, startIndex,
				pageSize);
	}

}
