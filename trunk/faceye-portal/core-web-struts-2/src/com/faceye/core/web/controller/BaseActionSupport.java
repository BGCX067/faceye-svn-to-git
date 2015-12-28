package com.faceye.core.web.controller;

import java.sql.Date;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.StringConverter;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.faceye.core.service.iface.IService;
import com.faceye.core.util.helper.DateConverter;
import com.faceye.core.util.helper.Http;
/**
 * 
 * @author���κ���
��* @Copy Right:�������
 * @System:�������֧��ϵͳ
��* @Create Time:2007-10-22
 *��@Package And File Name:com.faceye.core.web.controller.BaseActionSupport.java
 * @Description:
 */
public class BaseActionSupport extends ActionSupport {
    private IService service = null;

    public IService getService() {
        return service;
    }
    public void setService(IService service) {
        this.service = service;
    }
    protected Http getHttp() {
        return Http.getHttp();
    }
    public Object getBean(String beanName) {
        ApplicationContext ctx = WebApplicationContextUtils
                .getRequiredWebApplicationContext(ServletActionContext.getServletContext());
        return ctx.getBean(beanName);
    }
    static {
        registConverter();
    }

    public static void registConverter() {
        ConvertUtils.register(new StringConverter(), String.class);
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        ConvertUtils.register(new LongConverter(null), Long.class);
        ConvertUtils.register(new FloatConverter(null), Float.class);
        ConvertUtils.register(new DoubleConverter(null), Double.class);
        ConvertUtils.register(new DateConverter("yyyy-MM-dd"), Date.class);
    }
}
