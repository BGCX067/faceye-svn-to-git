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
 * @作者：宋海鹏
 * @系统名称: 早点网络支持系统
 * @开发时间：Dec 14, 2006
 * @版权:早点网络
 * @说明:
 * @包名：com.faceye.core.web.controller
 */
public class BaseMultiActionController extends MultiActionController {
	protected Log log = LogFactory.getLog(this.getClass());

	private IService service = null;

	/**
	 * Http操作类
	 * 
	 * @return
	 */
	protected Http getHttp() {
		return Http.getHttp();
	}

	/**
	 * 注册新类型
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(
				Integer.class, true));
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(
				Double.class, true));
	}

	/**
	 * 异常捕获
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
				log.error("捕捉到传入异常,异常类型为:" + e.toString());
			}
			return new ModelAndView("exception");
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.error("在捕捉异常时,出现新异常,异常类型为:" + e.toString());
			}
			return new ModelAndView("exception");
		}
	}
	/**
	 * 系统全局保存成功信息
	 * @param request
	 * @param response
	 * @return
	 */
	protected ModelAndView saveSuccess(HttpServletRequest request,
			HttpServletResponse response){
		return new ModelAndView("system.save.success");
	}
	
	/**
	 * 全局删除成功消息
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
	 * 根据ID取得实体.
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
				log.debug(">>>>>>>>取得实体时发生异常,异常类型为:" + e.toString());
			}
		}
		if (o == null) {
			if (log.isDebugEnabled()) {
				log.debug(">>>>>>>>取到空对象");
			}
		}
		return o;
	}
	
	protected Serializable getEntityID(HttpServletRequest request){
		String id=this.getHttp().getParameter(request, StringPool.ENTITY_ID);
		return (Serializable)id;
	}

	/**
	 * 删除数据
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
					.debug(">>>>>>>>>>>删除数据出现异常,存在关联数据,不能直接删除,异常类型为:"
							+ e.toString());
		} catch (ServiceException e) {
			if (log.isErrorEnabled()) {
				log.debug(">>>>>>>>删除数据出现异常" + e.toString());
			}
		}
	}
/***
*简单对象保存
*
*/
protected void saveOrUpdateObject(Object o){
	try{
	this.getService().getBaseService().getBaseHibernateService().saveOrUpdateObject(o);
}catch(ServiceException e){
	if(log.isErrorEnabled()){
		log.debug(">>>>>>>>保存数据时出现异常，出现异常的方法为："+this.getClass().getName()+"saveOrUpdateObejct(),异常类型为:"+e.toString());
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
