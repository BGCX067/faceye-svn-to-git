package com.faceye.components.blog.dao.controller;

import java.io.Serializable;

import com.faceye.components.blog.dao.iface.IDiscusDao;
import com.faceye.components.blog.dao.model.Discus;
import com.faceye.core.componentsupport.dao.controller.DomainDao;
import com.faceye.core.util.helper.PaginationSupport;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.blog.dao.controller.DiscusDao.java
 * @Description:用户评论数据库操作类,操作用户评论.
 */
public class DiscusDao extends DomainDao implements IDiscusDao {

	public PaginationSupport getDiscusByArticleId(Serializable articleId,
			int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		String hql="from "+Discus.class.getName()+" d where d.article.id=:articleId order by d.createTime desc";
		return super.getPageByHQL(hql, "articleId", articleId, pageSize, startIndex);
		
	}

	public PaginationSupport getDiscusByUserId(Serializable userId,
			int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		String hql="from "+Discus.class.getName()+" d where d.user.id=:userId order by d.createTime desc";
		return super.getPageByHQL(hql, "userId", userId, pageSize, startIndex);
	}

	public void save(Discus discus) {
		// TODO Auto-generated method stub
		super.saveOrUpdateObject(discus);
	}

}
