package com.faceye.core.service.security.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.faceye.core.service.security.service.iface.IResourceService;
import com.faceye.core.util.helper.StringPool;
import com.faceye.core.web.controller.ExtTemplateAction;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.core.service.security.web.controller.ResourceAction.java
 * @Description:
 */
public class ResourceAction extends ExtTemplateAction {
	 private IResourceService resourceService=null;

	public IResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(IResourceService resourceService) {
		this.resourceService = resourceService;
	}

	@Override
	protected void onInitEntity(HttpServletRequest request, Object o, ActionForm form) {
		// TODO Auto-generated method stub
	
	}

	@Override
	protected void onInitForm(HttpServletRequest request, ActionForm form, Object o) {
		// TODO Auto-generated method stub
		Map resourceTypes=StringPool.getSecurityResourceType();
		super.getHttp().setRequestAttribute(request, "resourceTypes", resourceTypes);
	}
	
	protected String getPageJson(HttpServletRequest request){
		return this.getResourceService().getPageResources(this.getHttp().getCurrentIndex(request));
	}
	/**
	 * 取得resource资源,为permission授权做准备
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getResourcesForPermission(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params=this.getRequestParameterMap(request);
		String json=this.getResourceService().getResourcesByPermission(params.get("permissionId").toString(), new Boolean(params.get("exists").toString()));
		this.jsonPrint(response, json);
		return null;
	}
	
	public ActionForward remove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Map params=this.getRequestParameterMap(request);
		this.getResourceService().removeResources(params.get(StringPool.ENTITY_IDS).toString().split(StringPool.ENTITY_IDS_SPLIT_WITH));
		return null;
	}

}
