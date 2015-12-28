package com.faceye.core.service.security.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import com.faceye.core.service.security.service.iface.IResourceService;
import com.faceye.core.util.helper.StringPool;
import com.faceye.core.web.controller.ActionTemplate;
/**
 * 
 * @author���κ���
��* @Copy Right:�������
 * @System:�������֧��ϵͳ
��* @Create Time:2007-8-19
 *��@Package And File Name:com.faceye.core.service.security.web.controller.ResourceAction.java
 * @Description:ϵͳ��Դ������,���ڿؿ���ϵͳ����Դ����.
 */
public class ResourceAction extends ActionTemplate {
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

}
