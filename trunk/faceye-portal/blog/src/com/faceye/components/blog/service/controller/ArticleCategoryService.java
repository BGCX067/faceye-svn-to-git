package com.faceye.components.blog.service.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.faceye.components.blog.dao.iface.IArticleCategoryDao;
import com.faceye.components.blog.dao.model.ArticleCategory;
import com.faceye.components.blog.service.iface.IArticleCategoryService;
import com.faceye.components.portal.dao.iface.IPortalContainerDao;
import com.faceye.components.portal.dao.model.PortalContainer;
import com.faceye.core.componentsupport.service.controller.DomainService;
import com.faceye.core.service.security.service.iface.ITreeService;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.util.helper.StringPool;

public class ArticleCategoryService extends DomainService implements
		IArticleCategoryService {
	private IArticleCategoryDao articleCategoryDao = null;
	private IPortalContainerDao portalContainerDao=null;
	private ITreeService treeService = null;

	public ITreeService getTreeService() {
		return treeService;
	}

	public void setTreeService(ITreeService treeService) {
		this.treeService = treeService;
	}

	public IArticleCategoryDao getArticleCategoryDao() {
		return articleCategoryDao;
	}

	public void setArticleCategoryDao(IArticleCategoryDao articleCategoryDao) {
		this.articleCategoryDao = articleCategoryDao;
	}

	public PaginationSupport getAllArticleCategoriesByUserId(Serializable userId) {
		// TODO Auto-generated method stub
		return this.getArticleCategoryDao().getAllArticleCategoriesByUserId(
				userId);
	}

	private Map toTreeStruct(ArticleCategory arg0) {
		Map o = new HashMap();
		o.put(StringPool.TREE_ID, arg0.getId());
		o.put(StringPool.TREE_NAME, arg0.getName());
		// o.put(StringPool.TREE_ICON_Cls, "icon-feed-parent");
		// o.put(StringPool.TREE_CLS, "feed-parent");
		o.put(StringPool.TREE_ORDER, arg0.getNodeOrder());
		o.put(StringPool.TREE_PARENTID,
				null == arg0.getParentArticleCategory() ? null : arg0
						.getParentArticleCategory().getId());
		return o;
	}

	public String treeJson(Serializable userId) {
		// TODO Auto-generated method stub
		List items = new ArrayList();
		List categories = this.getAllArticleCategoriesByUserId(userId)
				.getItems();
		if (CollectionUtils.isNotEmpty(categories)) {
			for (int i = 0; i < categories.size(); i++) {
				items.add(this
						.toTreeStruct((ArticleCategory) categories.get(i)));
			}
		}
		if (CollectionUtils.isNotEmpty(items)) {
			return this.getTreeService().treeJSON(items);
		} else {
			return null;
		}
	}
	
	/**
	 * 转化为树形结构集合
	 */
	public List toTreeStructItems(List arg0){
		List items = new ArrayList();
		if (CollectionUtils.isNotEmpty(arg0)) {
			for (int i = 0; i < arg0.size(); i++) {
				items.add(this
						.toTreeStruct((ArticleCategory) arg0.get(i)));
			}
		}
		return items;
	}

	public void saveInitUserArticleCategory(Serializable userId) {
		// TODO Auto-generated method stub
		List items=this.getAllArticleCategoriesByUserId(userId).getItems();
		if(CollectionUtils.isEmpty(items)){
			ArticleCategory articleCategory=new ArticleCategory();
			PortalContainer portalContainer=this.getPortalContainerDao().getPortalContainerByUserId(userId);
			articleCategory.setName(StringPool.BLOG_ARTICLE_DEFAULT_USER_CATEGORY_NAME);
			articleCategory.setNodeOrder(new Integer(0));
			articleCategory.setPortalContainer(portalContainer);
			this.saveOrUpdateObject(articleCategory);
		}
		
	}

	public void saveArticleCategoryOrder(Serializable userId,
			Serializable articleCategoryId, Integer index,
			Serializable oldParentId, Serializable newParentId) {
		// TODO Auto-generated method stub
		if(null==oldParentId){
			oldParentId=new String("0");
		}
		if(null==newParentId){
			newParentId=new String("0");
		}
		ArticleCategory currentCategory=(ArticleCategory) this.getObject(ArticleCategory.class, articleCategoryId);
		Integer oldIndex=currentCategory.getNodeOrder();
		List items=this.getAllArticleCategoriesByUserId(userId).getItems();
		List transferedItems=this.toTreeStructItems(items);
		List directChildren=null;
		//取得原父节点的所有子节点
		if(!oldParentId.equals("0")){
			directChildren=this.getTreeService().getDirectChildrenTrees(transferedItems, oldParentId.toString());
		}else{
			directChildren=this.getTreeService().getRoots(transferedItems);
		}
		//如果是同级移动
		if(oldParentId.equals(newParentId)){
			// 对index进行校正
			if (index > directChildren.size() - 1) {
				index = directChildren.size() - 1;
			}
			// 如果向上移动
			if (oldIndex > index) {
				for(int i=index;i<oldIndex;i++){
					ArticleCategory item=(ArticleCategory)this.getArticleCategoryDao().getObject(ArticleCategory.class, ((Map)directChildren.get(i)).get(StringPool.TREE_ID).toString());
					item.setNodeOrder(i+1);
					this.getArticleCategoryDao().saveOrUpdateObject(item);
				}
			}else{
				//如果向下移动.
				for(int i=oldIndex+1;i<=index;i++){
					ArticleCategory item=(ArticleCategory)this.getArticleCategoryDao().getObject(ArticleCategory.class, ((Map)directChildren.get(i)).get(StringPool.TREE_ID).toString());
					item.setNodeOrder(i-1);
					this.getArticleCategoryDao().saveOrUpdateObject(item);
				}
			}
		}else{
			//如果是跨级移动
			//取得新父节点的所有子节点,如果新父节点是根节点,取得本树的所有根节点
			List newParentChildren=null;
			ArticleCategory parentArticleCategory=null;
			if(newParentId.equals("0")){
				newParentChildren=this.getTreeService().getRoots(transferedItems);
			}else{
				newParentChildren=this.getTreeService().getDirectChildrenTrees(transferedItems, newParentId.toString());
				parentArticleCategory=(ArticleCategory) this.getArticleCategoryDao().getObject(ArticleCategory.class, newParentId);
			}
			// 处理原父节点下的子节点,填补当前节点移走后留下的空缺
			if (oldIndex + 1 <= directChildren.size() - 1) {
				for (int i = oldIndex + 1; i < directChildren.size(); i++) {
					ArticleCategory item=(ArticleCategory)this.getArticleCategoryDao().getObject(ArticleCategory.class, ((Map)directChildren.get(i)).get(StringPool.TREE_ID).toString());
					item.setNodeOrder(i - 1);
					this.getArticleCategoryDao().saveOrUpdateObject(item);
				}
			}
			// 处理新插入的父节点下的子节点
			if (index <= newParentChildren.size() - 1) {
				for (int i = index; i < newParentChildren.size(); i++) {
					ArticleCategory item = (ArticleCategory) this.getArticleCategoryDao().getObject(ArticleCategory.class, 
									((Map) newParentChildren.get(i)).get("id")
											.toString());
					item.setNodeOrder(i + 1);
					this.getArticleCategoryDao().saveOrUpdateObject(item);
				}
			}
			currentCategory.setParentArticleCategory(parentArticleCategory);
		}
		currentCategory.setNodeOrder(index);
		this.getArticleCategoryDao().saveOrUpdateObject(currentCategory);
	}



	public IPortalContainerDao getPortalContainerDao() {
		return portalContainerDao;
	}

	public void setPortalContainerDao(IPortalContainerDao portalContainerDao) {
		this.portalContainerDao = portalContainerDao;
	}

	public Integer getNextNodeOrder(Serializable userId,
			Serializable parentCategoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getNextArticleCategoryOrder(Serializable userId,
			Serializable parentCategoryId) {
		// TODO Auto-generated method stub
		Integer nextOrder=this.getNextArticleCategoryOrder(userId, parentCategoryId);
		return nextOrder;
	}

}
