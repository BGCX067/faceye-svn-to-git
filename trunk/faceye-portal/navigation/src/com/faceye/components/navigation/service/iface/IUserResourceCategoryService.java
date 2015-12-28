package com.faceye.components.navigation.service.iface;

import java.io.Serializable;
import java.util.List;

import com.faceye.components.navigation.dao.model.UserResourceCategory;
import com.faceye.core.componentsupport.service.iface.IDomainService;
import com.faceye.core.service.security.model.User;

public interface IUserResourceCategoryService extends IDomainService {
	/**
	 * 保存用户资源分类
	 * @param user
	 * @param parentUserResourceCategory
	 * @param nowSavedUserResourceCategory
	 */
   public void saveOrUpdateUserResourceCategory(User user,UserResourceCategory parentUserResourceCategory,UserResourceCategory nowSavedUserResourceCategory);
   
   public void saveOrUpdateDefaultUserResourceCategory(Serializable userId);
   /**
    * 根据用户ID，查询取得用户的资源分类
    * @param userId
    * @return
    */
   public List getUserResourceCategoryByUser(Serializable userId);
   /**
    * 删除用户资源分类，同步将本分类下的所有资源转移到默认分类中。
    * @param userResourceCategory
    */
   public void removeUserResourceCategory(Serializable userResourceCategoryId);
   /**
    * 取得用户feed资源根分类
    * @param userId
    * @return
    */
   public UserResourceCategory getRootUserResourceCategory(Serializable userId);
   /**
    * 取得转化后的UserResourceCategory
    * @param userId
    * @return
    */
   public List getTransferUserResourceCategoryByUser(Serializable userId);
   /**
    * 拼装UserResourceCategory 与feed,准备生成树结构。
    */
   public List getUserResourceCategoryAndFeedsTree(Serializable userId);
   /**
    * 建立feed与userResourceCategory之间的订阅关系。
    */
   public void saveOrUpdateBuildRelationShipBetweenFeedAndUserResourceCategory(Serializable feedId,Serializable userResourceCategoryId);
   public void saveOrUpdateBuildRelationShipBetweenFeedAndUserResourceCategory(Serializable userId,Serializable feedId,Serializable userResourceCategoryId,Integer index);
   
   /*
    * 建立feed与userResourceCategory之间的订阅关系。
    */
   public void saveOrUpdateBuildRelationShipBetweenFeedsAndUserResourceCategory(Serializable [] feedIds,Serializable userResourceCategoryId);
   
   /**
    * 解除feed与userResourceCategory之间的订阅关系。
    */
   public void removeRelationShipBetweenFeedAndUserResourceCategory(Serializable feedId,Serializable userResourceCategoryId);
   /**
    * 解除feeds与userResourceCategory之间的订阅关系。
    */
   public void removeRelationShipBetweenFeedsAndUserResourceCategory(Serializable [] feedIds,Serializable userResourceCategoryId);
   
   /***
    * 一个指定用户是否存在至少一个UserResourceCategory
    */
   public boolean isExistsAtLeastUserResourceCategory(Serializable userId);
   
   
   public void saveUserResourceCategoryAndFeedOrder(String nodeId,String oldParentId,String newParentId,Integer index,Serializable userId);
   
}
