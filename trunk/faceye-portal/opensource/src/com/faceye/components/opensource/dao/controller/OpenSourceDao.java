package com.faceye.components.opensource.dao.controller;

import java.io.Serializable;

import com.faceye.components.opensource.dao.iface.IOpenSourceDao;
import com.faceye.components.opensource.dao.model.OpenSource;
import com.faceye.core.dao.hibernate.controller.BaseHibernateDao;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.util.helper.StringPool;

public class OpenSourceDao extends BaseHibernateDao implements IOpenSourceDao {

	public PaginationSupport getOpenSource(int startIndex) {
		// TODO Auto-generated method stub
		return this.getOpenSource(null, startIndex);
	}

	public PaginationSupport getOpenSource(Serializable columnId, int startIndex) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer(
				"select o.id,o.name,o.url,o.content from "
						+ OpenSource.class.getName() + " o ");
		if (columnId != null) {
			if (columnId.toString().indexOf(StringPool.CHARACTER_COMMA) != -1) {
				String columnids[] =columnId.toString().split(StringPool.CHARACTER_COMMA);
				hql.append(" where ");
				for(int i=0;i<columnids.length;i++){
					hql.append("o.column.id=\'"+columnids[i]+"\'");
					if(i!=columnids.length-1){
						hql.append(" or ");
					}
				}
				return this.getPageByHQL(hql.toString(), startIndex);
			} else {
				hql.append(" where o.column.id=:columnId");
				return this.getPageByHQL(hql.toString(), "columnId", columnId,
						startIndex);
			}
			
		} else {
			return this.getPageByHQL(hql.toString(), startIndex);
		}

	}
}
