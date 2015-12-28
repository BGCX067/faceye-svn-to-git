package com.faceye.core.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.faceye.core.service.iface.IService;
import com.faceye.core.util.exception.ServiceException;
import com.faceye.core.util.helper.Http;
import com.faceye.core.util.helper.StringPool;

/**
 * 
 * @���ߣ��κ���
 * @ϵͳ����: �������֧��ϵͳ
 * @����ʱ�䣺Dec 14, 2006
 * @��Ȩ:�������
 * @˵��:
 * @������com.faceye.core.web.controller
 */
public class BaseMultiActionController extends MultiActionController {
	protected Log log = LogFactory.getLog(this.getClass());

	private IService service = null;

	/**
	 * Http������
	 * 
	 * @return
	 */
	protected Http getHttp() {
		return Http.getHttp();
	}

	/**
	 * ע��������
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(
				Integer.class, true));
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(
				Double.class, true));
	}

	/**
	 * �쳣����
	 * 
	 * @param request
	 * @param response
	 * @param e
	 * @return
	 */
	public ModelAndView throwException(HttpServletRequest request,
			HttpServletResponse response, Exception e) {
		try {
			if (log.isDebugEnabled()) {
				log.error("��׽�������쳣,�쳣����Ϊ:" + e.toString());
			}
			return new ModelAndView("exception");
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.error("�ڲ�׽�쳣ʱ,�������쳣,�쳣����Ϊ:" + e.toString());
			}
			return new ModelAndView("exception");
		}
	}
	/**
	 * ϵͳȫ�ֱ���ɹ���Ϣ
	 * @param request
	 * @param response
	 * @return
	 */
	protected ModelAndView saveSuccess(HttpServletRequest request,
			HttpServletResponse response){
		return new ModelAndView("system.save.success");
	}
	
	/**
	 * ȫ��ɾ���ɹ���Ϣ
	 * @param request
	 * @param response
	 * @return
	 */
	protected ModelAndView removeSuccess(HttpServletRequest request,
			HttpServletResponse response){
		return new ModelAndView("system.remove.success");
	}
	public void saveMessage(HttpServletRequest request, String msg) {
		List messages = (List) request.getSession().getAttribute(
				StringPool.MESSAGE);

		if (messages == null) {
			messages = new ArrayList();
		}
		messages.add(msg);
		request.getSession().setAttribute(StringPool.MESSAGE, messages);
	}

	/**
	 * ����IDȡ��ʵ��.
	 * 
	 * @param classz
	 * @param id
	 * @return
	 */
	protected Object getEntity(Class classz, Serializable id) {
		Object o = null;
		try {
			o = this.getService().getBaseService().getBaseHibernateService()
					.loadObject(classz, id);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug(">>>>>>>>ȡ��ʵ��ʱ�����쳣,�쳣����Ϊ:" + e.toString());
			}
		}
		if (o == null) {
			if (log.isDebugEnabled()) {
				log.debug(">>>>>>>>ȡ���ն���");
			}
		}
		return o;
	}
	
	protected Serializable getEntityID(HttpServletRequest request){
		String id=this.getHttp().getParameter(request, StringPool.ENTITY_ID);
		return (Serializable)id;
	}

	/**
	 * ɾ������
	 * 
	 * @return
	 */
	protected void removeObject(Class classz, Serializable id) {
		try {
			String objectId = id.toString();
			this.getService().getBaseService().getBaseHibernateService()
					.removeObject(classz, objectId);
		} catch (DataIntegrityViolationException e) {
			log
					.debug(">>>>>>>>>>>ɾ�����ݳ����쳣,���ڹ�������,����ֱ��ɾ��,�쳣����Ϊ:"
							+ e.toString());
		} catch (ServiceException e) {
			if (log.isErrorEnabled()) {
				log.debug(">>>>>>>>ɾ�����ݳ����쳣" + e.toString());
			}
		}
	}
/***
*�򵥶��󱣴�
*
*/
protected void saveOrUpdateObject(Object o){
	try{
	this.getService().getBaseService().getBaseHibernateService().saveOrUpdateObject(o);
}catch(ServiceException e){
	if(log.isErrorEnabled()){
		log.debug(">>>>>>>>��������ʱ�����쳣�������쳣�ķ���Ϊ��"+this.getClass().getName()+"saveOrUpdateObejct(),�쳣����Ϊ:"+e.toString());
	}
}
}


	public IService getService() {
		return service;
	}

	public void setService(IService service) {
		this.service = service;
	}

}
