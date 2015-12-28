package com.faceye.components.navigation.dao.iface;

import java.io.Serializable;
import java.util.List;

import com.faceye.components.navigation.dao.model.FeedSubscribe;
import com.faceye.components.navigation.dao.model.FeedSubscribeCount;
import com.faceye.core.componentsupport.dao.iface.IDomainDao;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.navigation.dao.iface.IFeedSubscribeDao.java
 * @Description:feed订阅
 */
public interface IFeedSubscribeDao extends IDomainDao {
  public FeedSubscribe getFeedSubcribeByFeedIdAndUserResourceCategoryId(Serializable feedId,Serializable userResourceCategoryId);
  public FeedSubscribeCount getFeedSubscribeCountByFeedId(Serializable feedId);
  public Integer getNextFeedSubscribeOrder(Serializable feedId,Serializable userResourceCategoryId);
  
  public List getFeedSubscribesByUserResourceCategory(Serializable userResourceCategoryId);
}
