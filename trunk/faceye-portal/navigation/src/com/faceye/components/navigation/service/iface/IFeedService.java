package com.faceye.components.navigation.service.iface;

import java.io.Serializable;

import com.faceye.core.service.iface.IBaseHibernateService;
import com.faceye.core.util.helper.PaginationSupport;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.navigation.service.iface.IFeedService.java
 * @Description:feed 导航服务接口
 */
public interface IFeedService extends IBaseHibernateService {
	/**
	 * 取得Feeds
	 * @param startIndex
	 * @return
	 */
   public String getFeeds(int startIndex);
   /**
    * 根据栏目取得Feeds
    * @param columnId
    * @param startIndex
    * @return
    */
   public String getFeeds(Serializable columnId,int startIndex);
   
   /**
    * 根据栏目.feed分类,用户ID取得feeds资源
    * @param columnId
    * @param categoryId
    * @param userId
    * @param startIndex
    * @param pageSize
    * @return
    */
   public String getFeeds(Serializable columnId,Serializable categoryId,Serializable userId,int startIndex,int pageSize);
   
   public void removeFeed(Serializable feedId);
   
   public PaginationSupport getFeedsByCategory(Serializable categoryId,int startIndex,int pageSize);
   
}
