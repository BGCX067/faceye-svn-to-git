package com.faceye.components.blog.dao.iface;

import java.io.Serializable;

import com.faceye.core.componentsupport.dao.iface.IDomainDao;
import com.faceye.core.util.helper.PaginationSupport;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.blog.dao.iface.IArticleDao.java
 * @Description:维护用户文章dao.
 */
public interface IArticleDao extends IDomainDao {
	/**
	 * 取得一个用户的所有文章,按时间先后顺序排序.
	 * @param userId
	 * @return
	 */
   public PaginationSupport getArticlesByUserId(Serializable userId,int startIndex,int pageSize);
   
   public PaginationSupport getArticlesByUserIdAndArticleCategoryId(Serializable userId,Serializable articleCategoryId,int startIndex,int pageSize);
   
   /**
    * 取得系统内最新文章排行榜
    */
   public PaginationSupport getNewerArticleOrderList(int startIndex,int pageSize);
   
   /**
    *blog搜索
    */
   public PaginationSupport search(String key,String searchMarker,int startIndex,int pageSize);
}
