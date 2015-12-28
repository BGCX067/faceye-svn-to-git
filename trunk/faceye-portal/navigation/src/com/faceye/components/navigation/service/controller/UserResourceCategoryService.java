package com.faceye.components.navigation.service.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.faceye.components.navigation.dao.iface.IFeedDao;
import com.faceye.components.navigation.dao.iface.IFeedSubscribeDao;
import com.faceye.components.navigation.dao.iface.IUserResourceCategoryDao;
import com.faceye.components.navigation.dao.model.Feed;
import com.faceye.components.navigation.dao.model.FeedSubscribe;
import com.faceye.components.navigation.dao.model.FeedSubscribeCount;
import com.faceye.components.navigation.dao.model.UserResourceCategory;
import com.faceye.components.navigation.service.iface.IUserResourceCategoryService;
import com.faceye.core.componentsupport.service.controller.DomainService;
import com.faceye.core.service.security.model.User;
import com.faceye.core.service.security.service.iface.ITreeService;
import com.faceye.core.util.helper.StringPool;

public class UserResourceCategoryService extends DomainService implements
		IUserResourceCategoryService {
	private IUserResourceCategoryDao userResourceCategoryDao = null;

	private ITreeService treeService = null;

	private IFeedSubscribeDao feedSubscribeDao = null;

	private IFeedDao feedDao = null;

	public IFeedDao getFeedDao() {
		return feedDao;
	}

	public void setFeedDao(IFeedDao feedDao) {
		this.feedDao = feedDao;
	}

	public IFeedSubscribeDao getFeedSubscribeDao() {
		return feedSubscribeDao;
	}

	public void setFeedSubscribeDao(IFeedSubscribeDao feedSubscribeDao) {
		this.feedSubscribeDao = feedSubscribeDao;
	}

	public List getUserResourceCategoryByUser(Serializable userId) {
		// TODO Auto-generated method stub
		return this.getUserResourceCategoryDao().getUserResourceCategoryByUser(
				userId);
	}

	public void removeUserResourceCategory(Serializable userResourceCategoryId) {
		// TODO Auto-generated method stub
		if (null == userResourceCategoryId) {
			return;
		}
		UserResourceCategory uc = (UserResourceCategory) this.loadObject(
				UserResourceCategory.class, userResourceCategoryId);
		this.removeObject(uc);
	}

	public void saveOrUpdateUserResourceCategory(User user,
			UserResourceCategory parentUserResourceCategory,
			UserResourceCategory nowSavedUserResourceCategory) {
		// TODO Auto-generated method stub
		Integer nodeOrder = this.getUserResourceCategoryDao()
				.getNextNodeOrderOfUserResourceCategory(user.getId(),
						null==parentUserResourceCategory?null:parentUserResourceCategory.getId());
		nowSavedUserResourceCategory.setNodeOrder(nodeOrder);
		this.getUserResourceCategoryDao().saveOrUpdateUserResourceCategory(
				user, parentUserResourceCategory, nowSavedUserResourceCategory);
	}

	public IUserResourceCategoryDao getUserResourceCategoryDao() {
		return userResourceCategoryDao;
	}

	public void setUserResourceCategoryDao(
			IUserResourceCategoryDao userResourceCategoryDao) {
		this.userResourceCategoryDao = userResourceCategoryDao;
	}

	public UserResourceCategory getRootUserResourceCategory(Serializable userId) {
		// TODO Auto-generated method stub
		return this.getUserResourceCategoryDao().getRootUserResourceCategory(
				userId);
	}

	public List getTransferUserResourceCategoryByUser(Serializable userId) {
		// TODO Auto-generated method stub
		List ucs = this.getUserResourceCategoryByUser(userId);
		List o = new ArrayList();
		if (null != ucs && ucs.size() > 0) {
			Iterator it = ucs.iterator();
			while (it.hasNext()) {
				UserResourceCategory item = (UserResourceCategory) it.next();
				o.add(this.transferUserResourceCategory(item));
			}
		}
		return o;
	}

	private Map transferUserResourceCategory(
			UserResourceCategory userResourceCategory) {
		Map o = new HashMap();
		o.put(StringPool.TREE_ID, userResourceCategory.getId());
		o.put(StringPool.TREE_NAME, userResourceCategory.getName());
		o.put(StringPool.TREE_ICON_Cls, "icon-feed-parent");
		o.put(StringPool.TREE_CLS, "feed-parent");
		o.put(StringPool.TREE_ORDER, userResourceCategory.getNodeOrder());
		o.put(StringPool.TREE_PARENTID, null == userResourceCategory
				.getParentUserResourceCategory() ? null : userResourceCategory
				.getParentUserResourceCategory().getId());
		return o;
	}

	public void removeRelationShipBetweenFeedAndUserResourceCategory(
			Serializable feedId, Serializable userResourceCategoryId) {
		FeedSubscribe fs = this.getFeedSubscribeDao()
				.getFeedSubcribeByFeedIdAndUserResourceCategoryId(feedId,
						userResourceCategoryId);
		FeedSubscribeCount fc = this.getFeedSubscribeDao()
				.getFeedSubscribeCountByFeedId(feedId);
		if (fc.getTotalCount() > 0) {
			fc.setTotalCount(fc.getTotalCount() - 1);
		}
		UserResourceCategory uc = (UserResourceCategory) this.loadObject(
				UserResourceCategory.class, userResourceCategoryId);
		Serializable userId = uc.getUser().getId();
		List trees = this.getUserResourceCategoryAndFeedsTree(userId);
		List children = this.getTreeService().getDirectChildrenTrees(trees,
				userResourceCategoryId.toString());
		Integer currentOrder = fs.getFeedOrder();
		if (currentOrder < children.size() - 1) {
			for (int i = currentOrder + 1; i < children.size(); i++) {
				Serializable id = ((Map) children.get(i)).get(
						StringPool.TREE_ID).toString();
				Feed inFeed = null;
				UserResourceCategory inUc = null;
				inFeed = (Feed) this.getObject(Feed.class, id);

				if (null == inFeed) {
					inUc = (UserResourceCategory) this.getObject(
							UserResourceCategory.class, id);
					inUc.setNodeOrder(i - 1);
					this.getUserResourceCategoryDao().saveOrUpdateObject(inUc);
				} else {
					FeedSubscribe inFs = this.getFeedSubscribeDao()
							.getFeedSubcribeByFeedIdAndUserResourceCategoryId(
									inFeed.getId(), userResourceCategoryId);
					if (null != inFs) {
						inFs.setFeedOrder(i - 1);
						this.getFeedSubscribeDao().saveOrUpdateObject(inFs);
					}
				}
			}
		}
		this.removeObject(fs);
		this.saveOrUpdateObject(fc);
	}

	public void removeRelationShipBetweenFeedsAndUserResourceCategory(
			Serializable[] feedIds, Serializable userResourceCategoryId) {
		// TODO Auto-generated method stub
		if (null != feedIds && feedIds.length > 0) {
			for (int i = 0; i < feedIds.length; i++) {
				this.removeRelationShipBetweenFeedAndUserResourceCategory(
						feedIds[i], userResourceCategoryId);
			}
		}
	}

	public void saveOrUpdateBuildRelationShipBetweenFeedAndUserResourceCategory(
			Serializable feedId, Serializable userResourceCategoryId) {
		// TODO Auto-generated method stub
		Feed feed = (Feed) this.loadObject(Feed.class, feedId);
		UserResourceCategory uc = (UserResourceCategory) this.loadObject(
				UserResourceCategory.class, userResourceCategoryId);
		FeedSubscribe fs = this.buildFeedSubscribe(feed, uc);
		FeedSubscribeCount fc = this.getFeedSubscribeDao()
				.getFeedSubscribeCountByFeedId(feedId);
		if (null == fc) {
			fc = new FeedSubscribeCount();
			fc.setTotalCount(new Integer(1));
			fc.setFeed(feed);
		} else {
			fc.setTotalCount(fc.getTotalCount() + 1);
		}
		this.getFeedSubscribeDao().saveOrUpdateObject(fc);
		this.saveOrUpdateObject(fs);
	}

	public void saveOrUpdateBuildRelationShipBetweenFeedsAndUserResourceCategory(
			Serializable[] feedIds, Serializable userResourceCategoryId) {
		// TODO Auto-generated method stub
		if (null != feedIds && feedIds.length > 0) {
			for (int i = 0; i < feedIds.length; i++) {
				this
						.saveOrUpdateBuildRelationShipBetweenFeedAndUserResourceCategory(
								feedIds[i], userResourceCategoryId);
			}
		}

	}

	public void saveOrUpdateDefaultUserResourceCategory(Serializable userId) {
		// TODO Auto-generated method stub
		UserResourceCategory uc = null;
		uc = this.getRootUserResourceCategory(userId);
		if (null == uc) {
			User user = (User) this.loadObject(User.class, userId);
			uc = this.buildDefaultUserResourceCategory();
			uc.setUser(user);
			this.saveOrUpdateObject(uc);
		}
		List feeds = this.getFeedDao().loadAllObjects(Feed.class);
		if (null != feeds && feeds.size() > 0) {
			for (int i = 0; i < feeds.size(); i++) {
				Feed feed = (Feed) feeds.get(i);
				this
						.saveOrUpdateBuildRelationShipBetweenFeedAndUserResourceCategory(
								feed.getId(), uc.getId());
			}
		}
	}

	private UserResourceCategory buildDefaultUserResourceCategory() {
		UserResourceCategory uc = new UserResourceCategory();
		uc.setName(StringPool.USER_RESORUCE_CATEGORY_ROOT_NAME);
		uc.setDescription("create By FaceYe.com");
		return uc;
	}

	/**
	 * 创建订阅关系
	 * 
	 * @param feed
	 * @param uc
	 * @return
	 */
	private FeedSubscribe buildFeedSubscribe(Feed feed, UserResourceCategory uc) {
		FeedSubscribe fs = new FeedSubscribe();
		fs.setFeed(feed);
		fs.setUserResourceCategory(uc);
		Integer feedOrder = this.getFeedSubscribeDao()
				.getNextFeedSubscribeOrder(feed.getId(), uc.getId());
		fs.setFeedOrder(feedOrder);
		return fs;
	}

	private FeedSubscribe buildFeedSubscribe(Feed feed,
			UserResourceCategory uc, Integer index) {
		FeedSubscribe fs = new FeedSubscribe();
		fs.setFeed(feed);
		fs.setUserResourceCategory(uc);
		fs.setFeedOrder(index);
		return fs;
	}

	/*
	 * 取得一个用户的feed tree结构(non-Javadoc)
	 * 
	 * @see com.faceye.components.navigation.service.iface.IUserResourceCategoryService#getUserResourceCategoryAndFeedsTree(java.io.Serializable)
	 */
	public List getUserResourceCategoryAndFeedsTree(Serializable userId) {
		// TODO Auto-generated method stub
		List ucs = this.getUserResourceCategoryByUser(userId);
		List result = new ArrayList();
		if (null != ucs) {
			if (!ucs.isEmpty() && ucs.size() > 0) {
				for (int i = 0; i < ucs.size(); i++) {
					UserResourceCategory uc = (UserResourceCategory) ucs.get(i);
					result.add(this.transferUserResourceCategory(uc));
				}
				for (int i = 0; i < ucs.size(); i++) {
					UserResourceCategory uc = (UserResourceCategory) ucs.get(i);
					// result.add(this.transferUserResourceCategory(uc));
					List feedSubscribes = this
							.getFeedSubscribeDao()
							.getFeedSubscribesByUserResourceCategory(uc.getId());
					if (null != feedSubscribes && !feedSubscribes.isEmpty()) {
						Iterator it = feedSubscribes.iterator();
						while (it.hasNext()) {
							FeedSubscribe fs = (FeedSubscribe) it.next();
							result.add(this.transferFeed(fs));
						}
					}

				}
			}
		}
		return result;
	}

	/**
	 * 将一个feed转化为tree结构
	 */
	private Map transferFeed(FeedSubscribe feedSubscribe) {
		Map o = new HashMap();
		Feed feed = feedSubscribe.getFeed();
		o.put(StringPool.TREE_ID, feed.getId());
		o.put(StringPool.TREE_ACTION, feed.getUrl());
		o.put(StringPool.TREE_NAME, feed.getName());
		o.put(StringPool.TREE_CLS, "feed");
		o.put(StringPool.TREE_ICON_Cls, "feed-icon");
		o.put(StringPool.TREE_ORDER, feedSubscribe.getFeedOrder());
		o.put(StringPool.TREE_PARENTID, feedSubscribe.getUserResourceCategory()
				.getId());
		return o;
	}

	public boolean isExistsAtLeastUserResourceCategory(Serializable userId) {
		// TODO Auto-generated method stub
		boolean result = false;
		List o = this.getUserResourceCategoryByUser(userId);
		if (null != o && !o.isEmpty() && o.size() > 0) {
			result = true;
		}
		return result;
	}

	/**
	 * 取得用户资源分类的下一个排序值
	 */

	// private Integer getNextNodeOrderOfUserResourceCategory(User user) {
	// return this.getUserResourceCategoryDao()
	// .getNextNodeOrderOfUserResourceCategory(user.getId());
	// }
	public void saveUserResourceCategoryAndFeedOrder(String nodeId,
			String oldParentId, String newParentId, Integer index,
			Serializable userId) {
		// TODO Auto-generated method stub
		Object node = null;
		UserResourceCategory parentNode = null;
		UserResourceCategory newParentNode = null;
		if (StringUtils.isNotEmpty(nodeId)) {
			node = this.getUserResourceCategoryDao().getObject(
					UserResourceCategory.class, nodeId);
			if (null == node) {
				node = this.getFeedDao().getObject(Feed.class, nodeId);
			}
		}
		if (node instanceof Feed) {
			Feed fNode = (Feed) node;
			FeedSubscribe fs = this.getFeedSubscribeDao()
					.getFeedSubcribeByFeedIdAndUserResourceCategoryId(
							fNode.getId(), oldParentId);
			this.saveFeedMoveOrder(fs, index, oldParentId, newParentId, userId);
		} else if (node instanceof UserResourceCategory) {
			UserResourceCategory uNode = (UserResourceCategory) node;
			this.saveUserResourceCategoryMoveOrder(uNode, index, oldParentId,
					newParentId, userId);
		}
	}

	/**
	 * 带索引的feed保存,用于拖动feed时.
	 */
	public void saveOrUpdateBuildRelationShipBetweenFeedAndUserResourceCategory(
			Serializable userId, Serializable feedId,
			Serializable userResourceCategoryId, Integer index) {
		// TODO Auto-generated method stub
		Integer trueIndex = new Integer(index);
		List trees = this.getUserResourceCategoryAndFeedsTree(userId);
		List children = this.getTreeService().getDirectChildrenTrees(trees,
				userResourceCategoryId.toString());
		Feed feed = (Feed) this.loadObject(Feed.class, feedId);
		UserResourceCategory uc = (UserResourceCategory) this.loadObject(
				UserResourceCategory.class, userResourceCategoryId);
		FeedSubscribe fs = this.buildFeedSubscribe(feed, uc);
		fs.setFeedOrder(trueIndex);
		FeedSubscribeCount fc = this.getFeedSubscribeDao()
				.getFeedSubscribeCountByFeedId(feedId);
		if (null == fc) {
			fc = new FeedSubscribeCount();
			fc.setTotalCount(new Integer(1));
			fc.setFeed(feed);
		} else {
			fc.setTotalCount(fc.getTotalCount() + 1);
		}
		this.getFeedSubscribeDao().saveOrUpdateObject(fc);
		this.saveOrUpdateObject(fs);
		if (index < children.size()) {
			for (int i = index; i < children.size(); i++) {
				String id = ((Map) children.get(i)).get(StringPool.TREE_ID)
						.toString();
				// 排除当前
				if (id.equals(feed.getId())) {
					continue;
				}
				Feed inFeed = (Feed) this.loadObject(Feed.class, id);
				UserResourceCategory inUc = null;
				if (null == inFeed || StringUtils.isEmpty(inFeed.getId())) {
					inUc = (UserResourceCategory) this.loadObject(
							UserResourceCategory.class, id);
					if (null != inUc && StringUtils.isNotEmpty(inUc.getId())) {
						inUc.setNodeOrder(i + 1);
						this.getUserResourceCategoryDao().saveOrUpdateObject(
								inUc);
					}
				} else {
					FeedSubscribe inFs = this.getFeedSubscribeDao()
							.getFeedSubcribeByFeedIdAndUserResourceCategoryId(
									inFeed.getId(), userResourceCategoryId);
					if (null != inFs && StringUtils.isNotEmpty(inFs.getId())) {
						inFs.setFeedOrder(i + 1);
						this.getFeedSubscribeDao().saveOrUpdateObject(inFs);
					}
				}

			}
		}

	}

	/**
	 * 保存用户分类的拖动信息
	 * 
	 * @param node
	 * @param index
	 * @param newParentId
	 * @param userId
	 */
	private void saveUserResourceCategoryMoveOrder(UserResourceCategory node,
			Integer index, Serializable oldParentId, Serializable newParentId,
			Serializable userId) {
		// 原节点的索引值
		Integer oldIndex = node.getNodeOrder();
		// 取得本用户的所有自定义分类
		List userResourceCategories = this
				.getTransferUserResourceCategoryByUser(userId);
		// 取得原父节点的所有子节点
		List directChildren = this.getTreeService().getDirectChildrenTrees(
				userResourceCategories, oldParentId.toString());
		// 如果同级拖动.
		if (oldParentId.equals(newParentId)) {
			// 对index进行校正
			if (index > directChildren.size() - 1) {
				index = directChildren.size() - 1;
			}
			// 如果向上移动
			if (oldIndex > index) {
				for (int i = index; i < oldIndex; i++) {
					UserResourceCategory item = (UserResourceCategory) this
							.loadObject(UserResourceCategory.class,
									((Map) directChildren.get(i)).get("id")
											.toString());
					item.setNodeOrder(i + 1);
					this.getUserResourceCategoryDao().saveOrUpdateObject(item);
				}
			} else {
				// 如果向下移动
				for (int i = oldIndex + 1; i <= index; i++) {
					UserResourceCategory item = (UserResourceCategory) this
							.loadObject(UserResourceCategory.class,
									((Map) directChildren.get(i)).get("id")
											.toString());
					item.setNodeOrder(i - 1);
					this.getUserResourceCategoryDao().saveOrUpdateObject(item);
				}
			}
		} else {
			// 如果是跃级拖动.
			// 取得新父节点的所有子节点
			List newChildren = this.getTreeService().getDirectChildrenTrees(
					userResourceCategories, newParentId.toString());
			UserResourceCategory newParent = (UserResourceCategory) this
					.getUserResourceCategoryDao().getObject(
							UserResourceCategory.class.getName(), newParentId);
			if (CollectionUtils.isEmpty(newChildren)) {
                index=0;
			} else {
				if (index > newChildren.size() - 1) {
					index = newChildren.size() - 1;
				}
			}
			// 处理原父节点下的子节点,填补当前节点移走后留下的空缺
			if (oldIndex + 1 <= directChildren.size() - 1) {
				for (int i = oldIndex + 1; i < directChildren.size(); i++) {
					UserResourceCategory item = (UserResourceCategory) this
							.loadObject(UserResourceCategory.class,
									((Map) directChildren.get(i)).get("id")
											.toString());
					item.setNodeOrder(i - 1);
					this.getUserResourceCategoryDao().saveOrUpdateObject(item);
				}
			}
			// 处理新插入的父节点下的子节点
			if (index <= newChildren.size() - 1) {
				for (int i = index; i < newChildren.size(); i++) {
					UserResourceCategory item = (UserResourceCategory) this
							.loadObject(UserResourceCategory.class,
									((Map) newChildren.get(i)).get("id")
											.toString());
					item.setNodeOrder(i + 1);
					this.getUserResourceCategoryDao().saveOrUpdateObject(item);
				}
			}
			node.setParentUserResourceCategory(newParent);
		}
		node.setNodeOrder(index);
		this.getUserResourceCategoryDao().saveOrUpdateObject(node);
	}

	/**
	 * 保存 feed的拖动信息
	 * 
	 * @return
	 */
	private void saveFeedMoveOrder(FeedSubscribe fs, Integer index,
			Serializable oldParentId, Serializable newParentId,
			Serializable userId) {
		Integer oldIndex = fs.getFeedOrder();
		// 取得原parent id下的所有feed subscribe
		List oldFeedSubscribes = this.getFeedSubscribeDao()
				.getFeedSubscribesByUserResourceCategory(oldParentId);
		// 如果是同级拖动
		if (oldParentId.equals(newParentId)) {
			if (index >= oldFeedSubscribes.size()) {
				index = oldFeedSubscribes.size() - 1;
			}
			// 如果向下移动
			if (index > oldIndex) {
				for (int i = oldIndex + 1; i <= index; i++) {
					FeedSubscribe item = (FeedSubscribe) oldFeedSubscribes
							.get(i);
					item.setFeedOrder(i - 1);
					this.getFeedSubscribeDao().saveOrUpdateObject(item);
				}
			} else {
				// 如果向上移动
				for (int i = index; i < oldIndex; i++) {
					FeedSubscribe item = (FeedSubscribe) oldFeedSubscribes
							.get(i);
					item.setFeedOrder(i + 1);
					this.getFeedSubscribeDao().saveOrUpdateObject(item);
				}
			}

		} else {
			// 如果是跨级拖动.
			// 对原parent id 下的feed subscribe 进行处理
			if (oldIndex + 1 <= oldFeedSubscribes.size() - 1) {
				for (int i = oldIndex + 1; i < oldFeedSubscribes.size(); i++) {
					FeedSubscribe item = (FeedSubscribe) oldFeedSubscribes
							.get(i);
					item.setFeedOrder(i - 1);
					this.getFeedSubscribeDao().saveOrUpdateObject(item);
				}
			}
			// 对新parent id 下的feed subscribe 进行处理
			List newFeedSubscribes = this.getFeedSubscribeDao()
					.getFeedSubscribesByUserResourceCategory(newParentId);
			// 对index进行校正
			if (index >= newFeedSubscribes.size()) {
				index = newFeedSubscribes.size() - 1;
			}
			for (int i = index; i < newFeedSubscribes.size(); i++) {
				FeedSubscribe item = (FeedSubscribe) newFeedSubscribes.get(i);
				item.setFeedOrder(i + 1);
				this.getFeedSubscribeDao().saveOrUpdateObject(item);
			}
			fs.setUserResourceCategory((UserResourceCategory) this
					.getUserResourceCategoryDao().getObject(
							UserResourceCategory.class, newParentId));
		}
		fs.setFeedOrder(index);
		this.getFeedSubscribeDao().saveOrUpdateObject(fs);
	}

	public ITreeService getTreeService() {
		return treeService;
	}

	public void setTreeService(ITreeService treeService) {
		this.treeService = treeService;
	}
}
