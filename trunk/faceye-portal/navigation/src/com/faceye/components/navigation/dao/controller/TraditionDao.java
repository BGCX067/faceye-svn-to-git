package com.faceye.components.navigation.dao.controller;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.faceye.components.navigation.dao.iface.ITraditionDao;
import com.faceye.components.navigation.dao.model.Tradition;
import com.faceye.core.dao.hibernate.controller.BaseHibernateDao;
import com.faceye.core.util.helper.PaginationSupport;

public class TraditionDao extends BaseHibernateDao implements ITraditionDao {

	public PaginationSupport getTraditions(int startIndex) {
		// TODO Auto-generated method stub
		return this.getTraditions(null, startIndex);
	}

	public PaginationSupport getTraditions(Serializable columnId, int startIndex) {
		// TODO Auto-generated method stub
		String hql = " from " + Tradition.class.getName() + " t";
		if (columnId != null) {
			hql += " where t.column.id=:columnId";
			return this.getPageByHQL(hql, "columnId", columnId, startIndex);
		} else {
			return this.getPageByHQL(hql, startIndex);
		}
	}

	public List getAllTraditions() {
		// TODO Auto-generated method stub
		return this.getTraditionsByCategory(null, -1).getItems();
	}

	public List getAllTraditionsByCategory(Serializable categoryId) {
		// TODO Auto-generated method stub
		return this.getTraditionsByCategory(categoryId, -1).getItems();
	}

	public PaginationSupport getTraditionsByCategory(Serializable categoryId,
			int startIndex) {
		String hql = "from " + Tradition.class.getName() + " t";
		if (null != categoryId && StringUtils.isNotEmpty(categoryId.toString())) {
			hql += " where t.category.id=:categoryId";
			if (startIndex != -1) {
				return this.getPageByHQL(hql, "categoryId", categoryId,
						startIndex);
			} else {
				return new PaginationSupport(this.getAllByHQL(hql,
						"categoryId", categoryId));
			}
		} else {
			if (startIndex != -1) {
				return this.getPageByHQL(hql, startIndex);
			} else {
				return new PaginationSupport(this.getAllByHQL(hql));
			}
		}
	}

}
