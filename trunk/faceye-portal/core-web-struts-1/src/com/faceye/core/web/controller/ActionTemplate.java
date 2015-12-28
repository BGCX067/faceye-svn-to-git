package com.faceye.core.web.controller;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.web.util.WebUtils;

import com.faceye.core.componentsupport.dao.model.DataType;
import com.faceye.core.util.helper.DetachedCriteriaUtil;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.util.helper.StringPool;

/**
 * @author：宋海鹏
　* @Copy Right:早点网络
 * @System:早点网络支持系统
　* @Create Time:2007-9-22
 *　@Package com.faceye.core.web.controller.ActionTemplate.java
 * @Description:
 */
public abstract class ActionTemplate extends BaseAction {
	protected DetachedCriteriaUtil getDetachedCriteriaUtil() {
		return DetachedCriteriaUtil.getDetachedCriteriaUtil();
	}
	  
/**
 * 加载数据
 */
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			Serializable objectId = super.getHttp().getEntityId(request);
			String entityClass = super.getHttp().getParameter(request,
					StringPool.ENTITY_CLASS);
			ActionMessages messages = super.getMessages(request);
			Object o = null;
			if (objectId == null || StringUtils.isEmpty(entityClass)) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"system.exception.entity.on.detial"));
				if (!messages.isEmpty()) {
					super.saveMessages(request, messages);
				}
				return super.globalMessage(mapping, form, request, response,
						messages);
			} else {
				Class classz = Class.forName(entityClass).newInstance()
						.getClass();
				o = super.getEntity(classz, objectId);
				super.getHttp().setRequestAttribute(request, super.DETAIL, o);
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return super.throwErrors(mapping, form, request, response, e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return super.throwErrors(mapping, form, request, response, e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return super.throwErrors(mapping, form, request, response, e);
		}
		return mapping.findForward(super.DETAIL);
	}

	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.query(mapping, form, request, response);
	}

	
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String entityClassName = this.getHttp().getEntityClassName(request);
			String queryType = this.getHttp().getParameter(request,
					StringPool.QUERY_TYPE);
			DetachedCriteria detachedCriteria = null;
			if (StringUtils.isNotEmpty(entityClassName)) {
				Class classz = Class.forName(entityClassName).newInstance()
						.getClass();
				Map source = WebUtils.getParametersStartingWith(request,
						StringPool.QUERY_PARAMS_IDENTIFER);
				detachedCriteria = this.getDetachedCriteriaUtil()
						.getDetachedCriteria(classz, source);
				PaginationSupport ps = this.getService().getBaseService()
						.getBaseHibernateService().getPage(detachedCriteria,
								super.getHttp().getCurrentIndex(request));
				super.getHttp().setRequestAttribute(request,
						StringPool.PAGINATION_SUPPORT, ps);
				return mapping.findForward(super.LIST);
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �����ɾ�����
	 */
	public ActionForward remove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			Serializable objectId = super.getHttp().getEntityId(request);
			String entityClass = super.getHttp().getParameter(request,
					StringPool.ENTITY_CLASS);
			ActionMessages messages = super.getMessages(request);
			Object o = null;
			if (objectId == null || StringUtils.isEmpty(entityClass)) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"system.exception.entity.on.remove"));
				if (!messages.isEmpty()) {
					super.saveMessages(request, messages);
				}

			} else {
				Class classz = Class.forName(entityClass).newInstance()
						.getClass();
				super.getService().getBaseService().getBaseHibernateService()
						.removeObject(classz, objectId);
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"system.global.success.on.remove"));
				if (!messages.isEmpty()) {
					super.saveMessages(request, messages);
				}

			}
			return super.globalMessage(mapping, form, request, response,
					messages);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			return super.throwErrors(mapping, form, request, response, e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			return super.throwErrors(mapping, form, request, response, e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return super.throwErrors(mapping, form, request, response, e);
		} catch (Exception e){
			return super.throwErrors(mapping, form, request, response, e);
		}

	}

	/**
	 * ����ݱ���ģ��
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			saveToken(request);
			Object o = this.initEntity(request, form);
			return this.baseSave(mapping, form, request, response, o);
		} catch (Exception e) {
			return super.throwErrors(mapping, form, request, response, e);
		}
	}

	/**
	 * ��ݸ���ģ��
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			this.ininForm(request, form);
			this.updateFormBean(mapping, request, form);
			return mapping.findForward(super.SAVE);
		} catch (Exception e) {
			return super.throwErrors(mapping, form, request, response, e);
		}
	}

	/**
	 * Form ��ʼ��ģ��
	 * 
	 * @param request
	 * @param form
	 */
	protected void ininForm(HttpServletRequest request, ActionForm form) {
		Serializable objectId = super.getHttp().getEntityId(request);
		String entityClass = super.getHttp().getParameter(request,
				StringPool.ENTITY_CLASS);
		Object o = null;
		if (StringUtils.isEmpty(entityClass)) {
			log.info("method:initForm, entityClassName is empty");
		} else {
			// if objectId is null,this an add new record operator,else this is
			// an update operator
			if (objectId == null) {
				onInitForm(request, form, o);
			} else {
				o = super.getEntity(entityClass, objectId);
				copyModel2Form(form, o);
				onInitForm(request, form, o);
			}
		}

	}

	protected Object initEntity(HttpServletRequest request, ActionForm form) {
		Object o = null;
		try {
			String entityClassName = getHttp().getEntityClassName(request);
			if (StringUtils.isNotEmpty(entityClassName)) {
				o = Class.forName(entityClassName).newInstance();
				this.copyForm2Model(o, form);
				this.onInitEntity(request, o, form);
			} else {
				log.info("method:initEntity,entityClassName is null");
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("method:initEntity,error is:" + e.getMessage());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("method:initEntity,error is:" + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("method:initEntity,error is:" + e.getMessage());
		}
		return o;
	}

	public ActionForward generatorJSONList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Map params=super.getHttp().getParameterMap(request);
			String hql="select t.id,t.name from "+DataType.class.getName()+ " t";
			PaginationSupport ps=super.getService().getBaseService().getBaseHibernateService().getPageByHQL(hql, super.getHttp().getCurrentIndex(request));
			JSONArray jsonArray=JSONArray.fromObject(ps.transItems());
			response.getWriter().print("{\"total\":"+ps.getTotalCount()+",\"root\":"+jsonArray.toString()+"}"); 
			return null;
		} catch (Exception e) {
			return super.throwErrors(mapping, form, request, response, e);
		}
	}


}
