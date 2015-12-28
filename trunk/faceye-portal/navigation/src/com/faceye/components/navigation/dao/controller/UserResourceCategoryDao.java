package com.faceye.components.navigation.dao.controller;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.CollectionUtils;

import com.faceye.components.navigation.dao.iface.IUserResourceCategoryDao;
import com.faceye.components.navigation.dao.model.FeedSubscribe;
import com.faceye.components.navigation.dao.model.UserResourceCategory;
import com.faceye.core.componentsupport.dao.controller.DomainDao;
import com.faceye.core.service.security.model.User;
import com.faceye.core.util.helper.StringPool;

/**
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.navigation.dao.controller.UserResourceCategoryDao.java
 * @Description:用户资源分类实现类
 */
public class UserResourceCategoryDao extends DomainDao implements IUserResourceCategoryDao
{

	public List getUserResourceCategoryByUser(Serializable userId)
	{
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserResourceCategory.class, "u");
		String hql = "from " + UserResourceCategory.class.getName() + " u";
		if (null != userId)
		{
			hql += " where u.user.id=" + "\'" + userId + "\'";
		}
		hql += " order by u.nodeOrder asc";

		return this.getAllByHQL(hql);
	}

	public void removeUserResourceCategory(UserResourceCategory userResourceCategory)
	{
		// TODO Auto-generated method stub、
		this.removeObject(userResourceCategory);

	}

	public void saveOrUpdateUserResourceCategory(User user, UserResourceCategory parentUserResourceCategory, UserResourceCategory nowSavedUserResourceCategory)
	{
		// TODO Auto-generated method stub
		nowSavedUserResourceCategory.setParentUserResourceCategory(parentUserResourceCategory);
		nowSavedUserResourceCategory.setUser(user);
		this.saveOrUpdateObject(nowSavedUserResourceCategory);

	}

	public UserResourceCategory getRootUserResourceCategory(Serializable userId)
	{
		// TODO Auto-generated method stub
		try
		{
			String hql = "from " + UserResourceCategory.class.getName() + " u where u.user.id=:uId and u.name=:name";
			List list = this.getAllByHQL(hql, new String[] { "uId", "name" }, new Object[] { userId, StringPool.USER_RESORUCE_CATEGORY_ROOT_NAME });
			return (UserResourceCategory) list.get(0);
		} catch (Exception e)
		{
			System.out.println(e.toString());
			return null;
		}
	}

	public Integer getNextNodeOrderOfUserResourceCategory(Serializable userId, Serializable parentUserResourceCategoryId)
	{
		// TODO Auto-generated method stub
		Integer o = new Integer(0);
		// int count=this.getCountByHQL("select count(*) from ", values)
		String hql = "";
		List result = null;

		if (null == parentUserResourceCategoryId || StringUtils.isEmpty(parentUserResourceCategoryId.toString()))
		{
			hql = "select count(*) from " + UserResourceCategory.class.getName() + " u where u.user.id=? and u.parentUserResourceCategory is null";
			int count = this.getCountByHQL(hql, userId);
			if (count == 0)
			{
				return o;
			}
			hql = "select max(u.nodeOrder) from " +
			        UserResourceCategory.class.getName() + " u where u.user.id=:userId and u.parentUserResourceCategory is null";
			result = this.getAllByHQL(hql, new String[] { "userId" }, new Object[] { userId });
		} else
		{
			hql = "select count(*) from " + UserResourceCategory.class.getName() + " u where u.user.id=? and u.parentUserResourceCategory.id=?";
			int count = this.getCountByHQL(hql, new Object[] { userId, parentUserResourceCategoryId });
			if (count == 0)
			{
				return o;
			}
			hql = "select max(u.nodeOrder) from " +
			        UserResourceCategory.class.getName() + " u where u.user.id=:userId and u.parentUserResourceCategory.id=:pId";
			result = this.getAllByHQL(hql, new String[] { "userId", "pId" }, new Object[] { userId, parentUserResourceCategoryId });
		}

		if (null != result && result.size() > 0)
		{
			if (result.get(0) != null)
			{
				o = (Integer) result.get(0) + 1;
			}
		}
		return o;
	}

}
