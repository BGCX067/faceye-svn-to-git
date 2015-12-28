package com.faceye.components.blog.dao.iface;

import java.io.Serializable;

import com.faceye.components.blog.dao.model.ArticleClickCount;
import com.faceye.core.componentsupport.dao.iface.IDomainDao;
import com.faceye.core.util.helper.PaginationSupport;

/**
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.blog.dao.iface.IArticleClickCountDao.java
 * @Description:文章访问记录。
 */
public interface IArticleClickCountDao extends IDomainDao {
	public void save(ArticleClickCount entity);

	public void remove(Serializable id);

	public PaginationSupport getPageArticleClickHistory(Serializable articleId,
			Serializable userId, int startIndex, int pageSize);
}
