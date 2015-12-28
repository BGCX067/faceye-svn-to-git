package com.faceye.core.service.security.dao.iface;

import java.io.Serializable;
import java.util.List;

import com.faceye.core.dao.hibernate.iface.IBaseHibernateDao;

public interface IColumnDao extends IBaseHibernateDao {
   /**
    * 取得所有栏目数据
    * @param startIndex
    * @return
    */
   public List getColumns();
   /**
    * 根据指定栏目ID，返回其直接子栏目
    * @param columnId
    * @param startIndex
    * @return
    */
   public List getColumns(Serializable columnId);
}
