package com.faceye.components.blog.service.controller;

import com.faceye.components.blog.dao.iface.IBlogClickHistoryDao;
import com.faceye.components.blog.service.iface.IBlogService;
import com.faceye.core.componentsupport.service.controller.DomainService;
import com.faceye.core.util.helper.PaginationSupport;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.blog.service.controller.BlogService.java
 * @Description:博客点击排行榜
 */
public class BlogService extends DomainService implements IBlogService {
    private IBlogClickHistoryDao blogClickHistoryDao=null;
	public PaginationSupport getPageOfBlogClickHistoryOrderList(
			String periodTime, int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		return this.getBlogClickHistoryDao().getBlogClickOrderList(periodTime, pageSize, startIndex);
	}
	public IBlogClickHistoryDao getBlogClickHistoryDao() {
		return blogClickHistoryDao;
	}
	public void setBlogClickHistoryDao(IBlogClickHistoryDao blogClickHistoryDao) {
		this.blogClickHistoryDao = blogClickHistoryDao;
	}

}
