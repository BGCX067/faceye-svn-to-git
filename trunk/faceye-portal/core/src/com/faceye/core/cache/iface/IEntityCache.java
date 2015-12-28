package com.faceye.core.cache.iface;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.core.cache.iface.IEntityCache.java
 * @Description:实体缓存管理
 */
public interface IEntityCache {
	/**
	 * 将实体放入cache中
	 * 这一动作可能发生在：
	 * 1。实体新增时
	 * 2。实体修改时
	 * 3.实体cache过期后进行查询时
	 * @param o
	 */
	public void putEntityInCache(Object o);
	/**
	 * 从cache中删除实体
	 * 这一动作可能发生在：
	 * 1.实体从系统删除时
	 * 2.更新实体时，实体在cache中的旧对像要处理时
	 * 3.查询实体对像，同时实体在cache中过期时
	 * @param o
	 */
	public void removeEntityFromCache(Object o);
	/**
	 * 从cache中加载实体。
	 * @param o
	 * @return
	 */
	public Object getEntityFromCache(Object o);
	
}
