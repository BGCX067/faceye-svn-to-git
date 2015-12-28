package com.faceye.components.blog.service.controller;

import java.io.Serializable;

import com.faceye.components.blog.dao.iface.IDiscusDao;
import com.faceye.components.blog.dao.model.Discus;
import com.faceye.components.blog.service.iface.IDiscusService;
import com.faceye.core.componentsupport.service.controller.DomainService;
import com.faceye.core.util.helper.PaginationSupport;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.blog.service.controller.DiscusService.java
 * @Description:对用户blog文章进行评论的服务类实现.
 */
public class DiscusService extends DomainService implements IDiscusService {
    private IDiscusDao discusDao=null;
	public IDiscusDao getDiscusDao() {
		return discusDao;
	}

	public void setDiscusDao(IDiscusDao discusDao) {
		this.discusDao = discusDao;
	}

	public PaginationSupport getDiscusByArticleId(Serializable articleId,
			int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		return this.getDiscusDao().getDiscusByArticleId(articleId, startIndex, pageSize);
	}

	public PaginationSupport getDiscusByUserId(Serializable userId,
			int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		return this.getDiscusByUserId(userId, startIndex, pageSize);
	}

	public void save(Discus discus) {
		// TODO Auto-generated method stub
      this.getDiscusDao().save(discus);
	}

}
