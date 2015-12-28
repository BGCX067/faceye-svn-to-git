package com.faceye.components.blog.dao.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.faceye.components.blog.dao.iface.IArticleDao;
import com.faceye.components.blog.dao.model.Article;
import com.faceye.components.blog.dao.model.ArticleClickCount;
import com.faceye.components.blog.dao.model.Discus;
import com.faceye.core.componentsupport.dao.controller.DomainDao;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.util.helper.StringPool;

public class ArticleDao extends DomainDao implements IArticleDao {

	public PaginationSupport getArticlesByUserId(Serializable userId,
			int startIndex, int pageSize) {
		// TODO Auto-generated method stu
		String hql = "from "
				+ Article.class.getName()
				+ " a where a.articleCategory.portalContainer.user.id=:userId order by a.createTime desc";
		PaginationSupport ps = this.getPageByHQL(hql, "userId", userId,
				pageSize, startIndex);
		return this.parsePaginationSupport(ps);
	}

	public PaginationSupport getArticlesByUserIdAndArticleCategoryId(
			Serializable userId, Serializable articleCategoryId,
			int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		String hql = "from "
				+ Article.class.getName()
				+ " a where a.articleCategory.portalContainer.user.id=:userId and a.articleCategory.id=:articleCategoryId order by a.createTime desc";
		PaginationSupport ps = this.getPageByHQL(hql, new String[] { "userId",
				"articleCategoryId" },
				new Object[] { userId, articleCategoryId }, pageSize,
				startIndex);
		return this.parsePaginationSupport(ps);
	}

	/**
	 * 加入评论数量及访问数量
	 * 
	 * @param ps
	 * @return
	 */
	private PaginationSupport parsePaginationSupport(PaginationSupport ps) {
		List items = ps.getItems();
		if (CollectionUtils.isNotEmpty(items)) {
			List result = new ArrayList();
			String hql = "select count(d.id) from " + Discus.class.getName()
					+ " d where d.article.id=?";
			String hql2 = "select count(a.id) from "
					+ ArticleClickCount.class.getName()
					+ " a where a.article.id=?";
			Iterator it = items.iterator();
			while (it.hasNext()) {
				Article item = (Article) it.next();
				Integer discusCount = this.getCountByHQL(hql, item.getId());
				Integer clickCount = this.getCountByHQL(hql2, item.getId());
				item.setDiscusCount(discusCount);
				item.setClickCount(clickCount);
				result.add(item);
			}
			ps.setItems(result);
		}
		return ps;
	}

	public PaginationSupport getNewerArticleOrderList(int startIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		String hql = "from " + Article.class.getName()
				+ " a order by a.createTime desc";
		PaginationSupport ps = super.getPageByHQL(hql, pageSize, startIndex);
		return ps;
	}

	public PaginationSupport search(String key, String searchMarker,
			int startIndex, int pageSize) {
		String hql = "from " + Article.class.getName() + " a ";
		if (StringUtils.isNotEmpty(searchMarker)) {
			if (StringUtils.equals(searchMarker, StringPool.BLOG_SEARCH_ALL)) {
				hql+=" where a.name like '%"+key+"%' or a.content like '%"+key+"%'";
			} else if (StringUtils.equals(searchMarker,
					StringPool.BLOG_SEARCH_TITLE_ONLY)) {
                 hql+="where a.name like '%"+key+"%'";
			}
		}
		PaginationSupport ps=this.getPageByHQL(hql, pageSize, startIndex);
		return parseContent(ps,key);
	}
	
	private PaginationSupport parseContent(PaginationSupport ps,String key){
		
		return ps;
	}

}
