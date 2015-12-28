package com.faceye.components.navigation.dao.controller;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.faceye.components.navigation.dao.iface.IFeedDao;
import com.faceye.components.navigation.dao.model.Feed;
import com.faceye.components.navigation.dao.model.FeedSubscribe;
import com.faceye.core.componentsupport.dao.controller.DomainDao;
import com.faceye.core.util.helper.PaginationSupport;

public class FeedDao extends DomainDao implements IFeedDao {

	public PaginationSupport getFeeds(int startIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public PaginationSupport getFeeds(Serializable columnId, int startIndex) {
		// TODO Auto-generated method stub
		String hql = "from " + Feed.class.getName() + " f";
		if (columnId != null) {
			hql += " where f.column.id=:columnId order by f.createDate desc";
			return this.getPageByHQL(hql, "columnId", columnId, startIndex);
		} else {
			return this.getPageByHQL(hql + " order by f.createDate desc",
					startIndex);
		}
	}

	public PaginationSupport getFeeds(Serializable columnId,
			Serializable categoryId, Serializable userId, int startIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(
				Feed.class, "f");
		if (null != columnId) {
			if (StringUtils.isNotEmpty(columnId.toString())) {
				detachedCriteria.add(Restrictions.eq("f.column.id", columnId));
			}
		}
		if (null != categoryId) {
			if (StringUtils.isNotEmpty(categoryId.toString())) {
				detachedCriteria.add(Restrictions.eq("f.category.id",
						categoryId));
			}
		}
		PaginationSupport ps = this.getPage(detachedCriteria, pageSize,
				startIndex);
		if (null != ps.getItems() && !ps.getItems().isEmpty()) {
			for (int i = 0; i < ps.getItems().size(); i++) {
				String fHql="from "+FeedSubscribe.class.getName() +" f where f.userResourceCategory.user.id=:userId and f.feed.id=:feedId";
				Feed item = (Feed) ps.getItems().get(i);
//				DetachedCriteria feedSubscrieDC = DetachedCriteria.forClass(
//						FeedSubscribe.class, "fs");
//				feedSubscrieDC.add(Restrictions.eq(
//						"fs.userResourceCategory.user.id", userId));
				List feedSubscribes = this.getAllByHQL(fHql, new String[]{"userId","feedId"}, new Object[]{userId,item.getId()});
				if (CollectionUtils.isNotEmpty(feedSubscribes)) {
					item.setSubscribed(true);
					ps.getItems().set(i,item);
				}
				
			}
		}
		return ps;
	}

	public PaginationSupport getFeedsByCategory(Serializable categoryId,
			int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		String hql = "from " + Feed.class.getName()
				+ " f where f.category.id=:categoryId";
		PaginationSupport ps = this.getPageByHQL(hql, "categoryId", categoryId,
				pageSize, startIndex);
		return ps;
	}

}
