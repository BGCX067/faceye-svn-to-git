package com.faceye.components.blog.service.iface;

import java.io.Serializable;

import com.faceye.core.componentsupport.service.iface.IDomainService;
import com.faceye.core.util.helper.PaginationSupport;

/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.blog.service.iface.IArticleService.java
 * @Description:用户文章服务类
 */
public interface IArticleService extends IDomainService {
	/**
	 * 取得一个用户的所有文章,按时间先后顺序排序.
	 * 
	 * @param userId
	 * @return
	 */
	public PaginationSupport getArticlesByUserId(Serializable userId,
			int startIndex, int pageSize);
/**
 * 取得一个用户的文章,参数为用户ID,以及用户的文章分类
 * @param userId
 * @param articleCategoryId
 * @param startIndex
 * @param pageSize
 * @return
 */
	public PaginationSupport getArticlesByUserIdAndArticleCategoryId(
			Serializable userId, Serializable articleCategoryId,
			int startIndex, int pageSize);
	/**
	 * 删除文章
	 * @param id
	 */
  public void remove(Serializable id);
  
  /**
   * 取得系统内最新文章排行榜
   */
  public PaginationSupport getNewerArticleOrderList(int startIndex,int pageSize);
}
