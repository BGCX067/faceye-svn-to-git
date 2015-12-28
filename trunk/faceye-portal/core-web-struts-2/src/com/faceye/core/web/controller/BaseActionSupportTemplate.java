package com.faceye.core.web.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.faceye.core.dao.hibernate.model.BaseObject;
import com.faceye.core.service.iface.IBaseHibernateService;
import com.faceye.core.util.helper.ClassHelper;
import com.faceye.core.util.helper.PaginationSupport;

public abstract class BaseActionSupportTemplate<T extends BaseObject, M extends IBaseHibernateService<T>>
        extends BaseActionSupport {
    protected PaginationSupport page = null;
    protected Class<T> entityClass;
    protected M entityService;
    protected T o = null;

    public T getO() {
        return o;
    }

    public void setO(T o) {
        try {
            if(o==null||StringUtils.isEmpty(o.getId())){
                o = this.getEntityClass().newInstance();
            }
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.o = o;
    }

    public PaginationSupport getPage() {
        return page;
    }

    public void setPage(PaginationSupport page) {
        this.page = page;
    }

    public String list() {
        page=(this.getEntityService().getPage(
                DetachedCriteria.forClass(getEntityClass()),
                super.getHttp().getCurrentIndex(
                        ServletActionContext.getRequest())));
        String success;

        return super.SUCCESS;

    }

    public String update() {
//        try {
//            //o = this.getEntityClass().newInstance();
//        } catch (InstantiationException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        o = this.getEntityService().getObject(this.getEntityClass(),
                super.getHttp().getEntityId(ServletActionContext.getRequest()));
        return super.SUCCESS;
    }

    public String save() {
        T ob=this.getO();
        this.getEntityService().saveOrUpdateObject(ob);
        return super.SUCCESS;
    }

    public String toSave() {
        return super.SUCCESS;
    }

    public Class<T> getEntityClass() {
        return ClassHelper.getSuperClassGenricType(getClass());
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = ClassHelper.getSuperClassGenricType(getClass());
    }

    public M getEntityService() {
        return entityService;
    }

    public void setEntityService(M entityService) {
        this.entityService = entityService;
    }
}
