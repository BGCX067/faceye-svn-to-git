package com.faceye.core.service.security.service.iface;

import java.util.List;
import java.util.Map;

import com.faceye.core.service.iface.IBaseHibernateService;
import com.faceye.core.service.security.dao.iface.ITreeDao;
import com.faceye.core.service.security.model.Tree;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.core.service.security.service.iface.ITreeService.java
 * @Description:树形菜单控制
 */
public interface ITreeService extends IBaseHibernateService{
    /**
     * 生成json树结构。
     * 默认生成第一级节点的json结构
     * @return
     */
  public String treeJSON();
  /**
   * 根据父节点，当点击时生成下一级节点的json结构
   * @param source
   * @param treeid
   * @return
   */
  public String treeJSON(List source,String treeid);
  /**
   * 根据一个指定的结果集，生成第一级节点json结构
   * 当前source中包含的元素结构为Map结构
   * @param source
   * @return
   */
  public String treeJSON(List source);
  /**
   * 是否有父节点
   * @param source
   * @param treeid
   * @return
   */
  public boolean isHaveParent(List source,String treeid);
  public boolean isHaveParent(List source,Map transedTree);
  public boolean isHaveParent(List source,Tree tree);
  /**
   * 是否有子节点
   * 当前List中包含的元素结构为Tree结构
   * @param source
   * @param tree
   * @return
   */
  public boolean isHaveChildren(List source,Tree tree);

 /**
  * 是否有子节点
  * 当前source中包含的元素结构为Map结构
  * @param source
  * @param treeid
  * @return
  */
  public boolean isHaveChildren(List source,String treeid);
  /**
   * 取得一个集合中的所有根节点
   * 当前source中包含的元素结构为Map结构
   * @param source
   * @return
   */
  public List getRoots(List source);
 /**
  * 取得一个指定节点的直接子节点
  * 当前source中包含的元素结构为Map结构
  * @param source
  * @param treeid
  * @return
  */
  public List getDirectChildrenTrees(List source,String treeid);
 /**
  * 取得一个节点的所有子节点，包括直接子节点和间接子节点
  * 当前source中包含的元素结构为Map结构
  * @param source
  * @param treeid
  * @param result
  * @return
  */
  public List getAllChildrenTrees(List source,String treeid,List result);
  /**
   * 取得一个节点的所有父节点
   * 当前source中包含的元素结构为Map结构
   * @param source
   * @param treeid
   * @param result
   * @return
   */
  public List getAllParentTrees(List source,String treeid,List result);
  /**
   * 当前source中包含的元素结构为Map结构
   * 取得一个节点的直接父节点
   * @param source
   * @param treeid
   * @return
   */
  public Map getDirectParentTree(List source,String treeid);
  
  /**
   * 将Tree对像结构转化为统一的Map结构
   * @return
   */
  public List getTransedTrees();
  /***
   * 根据ID从集合中取得一个节点
   * @param source
   * @param treeid
   * @return
   */
  public Map getTree(List source, String treeid);
  /**
   * 如果节点是全集，根据ID取得一个节点
   * 当前source中包含的元素结构为Map结构
   * @param treeid
   * @return
   */
  public Map getTree(String treeid);
}
