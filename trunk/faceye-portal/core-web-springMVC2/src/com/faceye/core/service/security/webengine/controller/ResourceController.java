package com.faceye.core.service.security.webengine.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.faceye.core.service.security.model.Resource;
import com.faceye.core.service.security.service.iface.IResourceService;
import com.faceye.core.util.helper.StringPool;
import com.faceye.core.web.controller.CommonTemplateController;

public class ResourceController extends CommonTemplateController {
   
	private IResourceService resourceService;
	
	public void beforBind(HttpServletRequest request, Object command,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub

	}

	
	public ModelAndView beforeSave(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map resourceTypes=StringPool.getSecurityResourceType();
		return new ModelAndView("security.resource.update","resourceTypes",resourceTypes);
	}

	
	public ModelAndView beforeUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ModelAndView doMultiRemove(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Object doQuery(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Map map=new HashMap();
		map.put(StringPool.QUERY_TYPE, StringPool.QUERY_TYPE_BY_HQL);
		map.put(StringPool.QUERY_HQL, "from Resource r");
		map.put(StringPool.FORWARD, "security.resource.list");
		return map;
	}

	
	public ModelAndView doRemove(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ModelAndView doSave(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Resource resource=new Resource();
		BindingResult result=super.bindObject(request, resource);
		super.getService().getBaseService().getBaseHibernateService().saveOrUpdateObject(resource);
		return null;
	}


	public IResourceService getResourceService() {
		return resourceService;
	}


	public void setResourceService(IResourceService resourceService) {
		this.resourceService = resourceService;
	}

}
