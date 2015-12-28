package com.faceye.components.navigation.service.iface;

import java.io.Serializable;
import java.util.List;

import com.faceye.core.componentsupport.service.iface.IDomainService;
import com.faceye.core.util.helper.PaginationSupport;
/**
 *  
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.navigation.service.iface.ICategoryService.java
 * @Description:网站分类业务类
 */
public interface ICategoryService extends IDomainService {
  public String getCategories();
  /**
   * 取得一个父分类的所有子分类
   * @param parentId
   * @return
   */
  public PaginationSupport getAllChildrenCategories(Serializable parentId);
  public List transferCategories();
}
