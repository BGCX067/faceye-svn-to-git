package com.faceye.components.navigation.dao.iface;

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
 * @Package com.faceye.components.navigation.dao.iface.IFeedDao.java
 * @Description:feed导航DAO
 */
public interface IFeedDao extends IDomainDao {
    public PaginationSupport getFeeds(int startIndex);
    public PaginationSupport getFeeds(Serializable columnId,int startIndex);
    public PaginationSupport getFeeds(Serializable columnId,Serializable categoryId,Serializable userId,int startIndex,int pageSize);
    
    public PaginationSupport getFeedsByCategory(Serializable categoryId,int startIndex,int pageSize);
}
