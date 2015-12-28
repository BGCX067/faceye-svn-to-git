package com.faceye.core.service.security.dao.controller;

import java.io.Serializable;
import java.util.List;

import com.faceye.core.dao.hibernate.controller.BaseHibernateDao;
import com.faceye.core.service.security.dao.iface.IColumnDao;
import com.faceye.core.service.security.model.Column;

public class ColumnDao extends BaseHibernateDao implements IColumnDao {

	public List getColumns() {
		// TODO Auto-generated method stub
		return this.getColumns(null);
	}

	public List getColumns(Serializable columnId) {
		// TODO Auto-generated method stub
		String hql="from "+Column.class.getName()+ " c";
		if(null==columnId){
			return this.getAllByHQL(hql);
		}else{
			hql+=" where c.parentColumn.id=:columnId";
			return this.getAllByHQL(hql, "columnId", columnId);
		}
	}


}
