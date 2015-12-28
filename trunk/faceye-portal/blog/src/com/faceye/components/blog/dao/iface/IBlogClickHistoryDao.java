package com.faceye.components.blog.dao.iface;

import java.io.Serializable;

import com.faceye.components.blog.dao.model.BlogClickHistory;
import com.faceye.core.componentsupport.dao.iface.IDomainDao;
import com.faceye.core.util.helper.PaginationSupport;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.blog.dao.iface.IBlogClickHistoryDao.java
 * @Description:博客点击日志历史及统计
 */
public interface IBlogClickHistoryDao extends IDomainDao {
	/**
	 * 保存访问记录
	 * @param entity
	 */
   public void save(BlogClickHistory entity);
   /**
    * 查询访问记录
    * @param portalContainerId，博客ID(portal容器编号)
    * @param startTime
    * @param endTime
    * @param startIndex
    * @param pageSize
    * @return
    */
   public PaginationSupport getPageBlogClickHistory(Serializable portalContainerId,String startTime,String endTime,int startIndex,int pageSize);
   /**
    * 取得博客在一定时间段的点击量排行榜。
    * 可以按周，月，季度，年度，总点击量等。
    * @param timePeriod 时段
    * @param pageSize
    * @param startIdex
    * @return
    */
   public PaginationSupport getBlogClickOrderList(String timePeriod,int pageSize,int startIndex);
}
