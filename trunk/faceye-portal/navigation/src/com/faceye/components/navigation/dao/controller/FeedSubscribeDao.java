package com.faceye.components.navigation.dao.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.faceye.components.navigation.dao.iface.IFeedSubscribeDao;
import com.faceye.components.navigation.dao.model.FeedSubscribe;
import com.faceye.components.navigation.dao.model.FeedSubscribeCount;
import com.faceye.core.componentsupport.dao.controller.DomainDao;

public class FeedSubscribeDao extends DomainDao implements IFeedSubscribeDao {

	public FeedSubscribe getFeedSubcribeByFeedIdAndUserResourceCategoryId(
			Serializable feedId, Serializable userResourceCategoryId) {
		// TODO Auto-generated method stub
		try {
			String hql = "from "
					+ FeedSubscribe.class.getName()
					+ " f where f.feed.id=:feedId and f.userResourceCategory.id=:userResourceCategoryId";
			List list = this.getAllByHQL(hql, new String[] { "feedId",
					"userResourceCategoryId" }, new Object[] { feedId,
					userResourceCategoryId });
			if (null != list && list.size() > 0) {
				return (FeedSubscribe) list.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}

	}

	public FeedSubscribeCount getFeedSubscribeCountByFeedId(Serializable feedId) {
		// TODO Auto-generated method stub
		String hql = "from " + FeedSubscribeCount.class.getName()
				+ " f where f.feed.id=:feedId";
		try {
			List list = this.getAllByHQL(hql, "feedId", feedId);
			if (null != list && list.size() > 0) {
				return (FeedSubscribeCount) list.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public Integer getNextFeedSubscribeOrder(Serializable feedId,
			Serializable userResourceCategoryId) {
		// TODO Auto-generated method stub
		Integer o = new Integer(0);
		
		int count=this.getCountByHQL("select count(*) from "+FeedSubscribe.class.getName()+" f where  f.userResourceCategory.id=?", userResourceCategoryId);
		if(count==0){
			return o;
		}
		String hql = "select max(f.feedOrder) from "
			+ FeedSubscribe.class.getName()
			+ " f where f.userResourceCategory.id=:uId";
		List list = this.getAllByHQL(hql, new String[] { "uId" },
				new Object[] { userResourceCategoryId });
		if (list != null && list.size() != 0 && !CollectionUtils.isEmpty(list)) {
			if (list.get(0) != null) {
				o = (Integer) list.get(0) + 1;
			}
		}
		return o;
	}

	public List getFeedSubscribesByUserResourceCategory(
			Serializable userResourceCategoryId) {
		// TODO Auto-generated method stub
		String hql = "from "
				+ FeedSubscribe.class.getName()
				+ " f where f.userResourceCategory.id=:uId order by f.feedOrder asc";
		return this.getAllByHQL(hql, "uId", userResourceCategoryId);
	}

}
