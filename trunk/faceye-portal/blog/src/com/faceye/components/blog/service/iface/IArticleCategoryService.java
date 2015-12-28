package com.faceye.components.blog.service.iface;

import java.io.Serializable;
import java.util.List;

import com.faceye.components.blog.dao.model.ArticleCategory;
import com.faceye.core.componentsupport.service.iface.IDomainService;
import com.faceye.core.util.helper.PaginationSupport;

/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.blog.service.iface.IArticleCategoryService.java
 * @Description:用户文章分类服务类
 */

public interface IArticleCategoryService extends IDomainService {
	public PaginationSupport getAllArticleCategoriesByUserId(Serializable userId);

	public String treeJson(Serializable userId);

	/**
	 * 初始用用户文章分类 如果用户没有文章分类 就为用户创建一个默认分类
	 * 
	 * @param userId
	 */
	public void saveInitUserArticleCategory(Serializable userId);
	
	public Integer getNextNodeOrder(Serializable userId,Serializable parentCategoryId);

	/**
	 * 保存节点的拖动排序信息
	 */
	public void saveArticleCategoryOrder(Serializable userId,
			Serializable articleCategoryId, Integer index,
			Serializable oldParentId, Serializable newParentId);
	
	public Integer getNextArticleCategoryOrder(Serializable userId,Serializable parentCategoryId);
	
	public List toTreeStructItems(List arg0);
	
}
