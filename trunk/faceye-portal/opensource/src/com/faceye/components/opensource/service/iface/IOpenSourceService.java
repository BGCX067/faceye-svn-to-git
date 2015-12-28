package com.faceye.components.opensource.service.iface;

import java.io.Serializable;

import com.faceye.core.service.iface.IBaseHibernateService;

public interface IOpenSourceService extends IBaseHibernateService {
	/**
	 * 取得开源栏目数据,返回结果为分过页的json数据
	 * @param startIndex
	 * @return
	 */
   public String getOpenSource(int startIndex);
   /**
    * 根据columID取得开源栏目数据　
    * 返回结果为分过页的JSON数据
    * @param columnId
    * @param startIndext
    * @return
    */
   public String getOpenSource(Serializable columnId,int startIndex);
}
