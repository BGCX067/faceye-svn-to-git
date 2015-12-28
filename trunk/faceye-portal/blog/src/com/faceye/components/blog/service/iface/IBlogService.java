package com.faceye.components.blog.service.iface;

import com.faceye.core.componentsupport.service.iface.IDomainService;
import com.faceye.core.util.helper.PaginationSupport;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.blog.service.iface.IBlogService.java
 * @Description:博客服务类
 */
public interface IBlogService extends IDomainService {
	/**
	 * 博客点击排行榜。
	 * @param periodTime
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
   public PaginationSupport getPageOfBlogClickHistoryOrderList(String periodTime,int startIndex,int pageSize);
}
