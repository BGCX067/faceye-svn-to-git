package com.faceye.components.blog.dao.iface;

import java.io.Serializable;

import com.faceye.components.blog.dao.model.Discus;
import com.faceye.core.componentsupport.dao.iface.IDomainDao;
import com.faceye.core.util.helper.PaginationSupport;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.blog.dao.iface.IDiscusDao.java
 * @Description:用户对Blog文章进行评论的数据库层接口.
 */
public interface IDiscusDao extends IDomainDao {
	/**
	 * 取得某一篇文章的评论,按时间顺序进行排列.
	 * @param articleId
	 * @return
	 */
   public PaginationSupport getDiscusByArticleId(Serializable articleId,int startIndex,int pageSize);
   /**
    * 取得某一用户对文章的所有评论,按时间顺序进行排列.
    * @param userId
    * @param startIndex
    * @param pageSize
    * @return
    */
   public PaginationSupport getDiscusByUserId(Serializable userId,int startIndex,int pageSize);
   /**
    * 保存评论数据.
    * @param discus
    */
   public void save(Discus discus);
}
