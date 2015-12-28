package com.faceye.core.service.security.service.iface;

import java.io.Serializable;

import com.faceye.core.service.iface.IBaseHibernateService;

public interface IRoleService extends IBaseHibernateService {
    public void saveOrUpdateRolePermissions(Serializable roleid,String []permissionIds);
}
