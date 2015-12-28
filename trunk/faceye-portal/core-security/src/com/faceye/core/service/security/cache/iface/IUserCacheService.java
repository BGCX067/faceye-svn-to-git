package com.faceye.core.service.security.cache.iface;

import org.acegisecurity.userdetails.UserDetails;

public interface IUserCacheService {
   public void initUserCache();
   
   public void putUserInCache(UserDetails userDetails);
   
   public void modifyUserInCache(UserDetails userDetails);
   
   public void removeUserFormCache(UserDetails userDetails);
   /**
    * key为user id
    * @param key
    * @return
    */
   public UserDetails getUserFromCache(String key);
}
