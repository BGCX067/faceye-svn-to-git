package com.faceye.core.util.helper;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import bsh.org.objectweb.asm.Constants;

import com.faceye.core.service.security.cache.iface.ICacheService;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.core.util.helper.StartUpListener.java
 * @Description:服务器启动时加载一些必要的基础数据
 * 目前所做的主要操作是将用户,受保护的系统资源,添加到cache中
 * 未来可能还会增加一些其它的初始化数据
 */
public class StartupListener extends ContextLoaderListener implements ServletContextListener{
	private static final Log log = LogFactory.getLog(StartupListener.class);
	public void contextInitialized(ServletContextEvent event) {
        if (log.isDebugEnabled()) {
            log.debug("initializing startup context...");
        }

        // call Spring's context ContextLoaderListener to initialize
        // all the context files specified in web.xml
        super.contextInitialized(event);

        ServletContext context = event.getServletContext();
      

        // Orion starts Servlets before Listeners, so check if the config
        // object already exists
//        Map config = (HashMap) context.getAttribute(Constants.CONFIG);
//
//        if (config == null) {
//            config = new HashMap();
//        }
//        
//        if (context.getInitParameter("theme") != null) {
//            config.put("theme", context.getInitParameter("theme"));
//        }

        ApplicationContext ctx =
            WebApplicationContextUtils.getRequiredWebApplicationContext(context);
          ICacheService cacheService=(ICacheService) ctx.getBean("cacheService");
          cacheService.getUserCacheService().initUserCache();
          cacheService.getResourceCacheService().initResourceCache();
//        setupContext(context);
    }
	
	
//	 public static void setupContext(ServletContext context) {
//	        ApplicationContext ctx = 
//	            WebApplicationContextUtils.getRequiredWebApplicationContext(context);
//
//	        LookupManager mgr = (LookupManager) ctx.getBean("lookupManager");
//
//	        // get list of possible roles
//	        context.setAttribute(Constants.AVAILABLE_ROLES, mgr.getAllRoles());
//
//	        if (log.isDebugEnabled()) {
//	            log.debug("Drop-down initialization complete [OK]");
//	        }
//	    }
}
