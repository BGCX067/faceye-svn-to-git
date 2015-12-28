package com.faceye.components.blog.service.iface;

import java.io.Serializable;

import com.faceye.components.blog.dao.model.ArticleClickCount;
import com.faceye.core.componentsupport.service.iface.IDomainService;
import com.faceye.core.util.helper.PaginationSupport;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.blog.service.iface.IArticleClickCountService.java
 * @Description:文章访问服务类
 */
public interface IArticleClickCountService extends IDomainService {
	public void save(ArticleClickCount entity);

	public void remove(Serializable id);

	public PaginationSupport getPageArticleClickHistory(Serializable articleId,
			Serializable userId, int startIndex, int pageSize);
}
