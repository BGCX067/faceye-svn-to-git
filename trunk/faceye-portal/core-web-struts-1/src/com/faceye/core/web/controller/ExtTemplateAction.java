package com.faceye.core.web.controller;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import com.faceye.core.util.exception.ServiceException;
import com.faceye.core.util.helper.JSONUtil;
import com.faceye.core.util.helper.StringPool;

/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.core.web.controller.ExtTemplateAction.java
 * @Description:基于Ext的基类,用于处理ext相关的基础功能.
 */
public abstract class ExtTemplateAction extends BaseAction {

	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = super.getRequestParameterMap(request);
		String json = "";
		try {
			Class clazz = Class.forName(params.get(StringPool.ENTITY_CLASS)
					.toString());
			Object o = super.getEntity(clazz, request);
			json = this.object2Json(o);
			this.jsonPrint(response, JSONUtil.rowJson(json));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String json = this.getPageJson(request);
		this.jsonPrint(response, json);
		return null;
	}

	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 删除数据
	 */
	public ActionForward remove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = super.getRequestParameterMap(request);
		String entityClass = "";
		String ids = "";
		String entityId = "";
		try {
			// 一次性删除多条记录
			if (params.containsKey(StringPool.ENTITY_IDS)) {
				// 判断id 及entityClass 满足情况
				ids = params.get(StringPool.ENTITY_IDS).toString();
				if (params.containsKey(StringPool.ENTITY_CLASS)) {
					if (StringUtils.isNotEmpty(params.get(
							StringPool.ENTITY_CLASS).toString())) {
						entityClass = params.get(StringPool.ENTITY_CLASS)
								.toString();
					} else {
						entityClass = this.getEntityClass().getName();
					}
				} else {
					entityClass = this.getEntityClass().getName();
				}
				// 如果满足以上条件，则执行删除动作
				if (StringUtils.isNotEmpty(ids)
						&& StringUtils.isNotEmpty(entityClass)) {
					String id[] = ids.split(StringPool.ENTITY_IDS_SPLIT_WITH);
					super.getService().getBaseService()
							.getBaseHibernateService().removeMultiObjects(
									Class.forName(entityClass), id);
				}
				// 一次删除一条数据
			} else if (params.containsKey(StringPool.ENTITY_ID)) {
				// 判断id,entityClass满足情况
				if (params.containsKey(StringPool.ENTITY_CLASS)) {
					if (StringUtils.isNotEmpty(params.get(
							StringPool.ENTITY_CLASS).toString())) {
						entityClass = params.get(StringPool.ENTITY_CLASS)
								.toString();
					} else {
						entityClass = this.getEntityClass().getName();
					}
				} else {
					entityClass = this.getEntityClass().getName();
				}
				entityId = params.get(StringPool.ENTITY_ID).toString();
				// 如果满足以上条件，则执行删除动作
				if (StringUtils.isNotEmpty(entityId)
						&& StringUtils.isNotEmpty(entityClass)) {

					this.getService().getBaseService()
							.getBaseHibernateService().removeObject(
									ClassUtils.forName(entityClass), entityId);

				}
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			Map params = super.getRequestParameterMap(request);
			Object o = this.initEntity(request, form);
			this.saveOrUpate(o);
			return null;
		} catch (Exception e) {
			log.info("faceye exception:" + e.toString() + " in method save()");
			return null;
		}
	}

	/**
	 * 更新数据
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Map params = super.getRequestParameterMap(request);
		Class clazz = null;
		Serializable entityId = null;
		Object o = null;
		String json = "";
		try {
			// 判断是否满足对像加载条件
			if (params.containsKey(StringPool.ENTITY_CLASS)) {
				if (StringUtils.isNotEmpty(params.get(StringPool.ENTITY_CLASS)
						.toString())) {
					clazz = ClassUtils.forName(params.get(
							StringPool.ENTITY_CLASS).toString());
				}
			} else {
				if (this.getEntityClass() != null) {
					clazz = this.getEntityClass();
				}
			}
			if (params.containsKey(StringPool.ENTITY_ID)) {
				if (StringUtils.isNotEmpty(params.get(StringPool.ENTITY_ID)
						.toString())) {
					entityId = params.get(StringPool.ENTITY_ID).toString();
				}
			}
			// 如果满足加载对像的条件
			if (null != clazz && entityId != null) {
				o = super.getEntity(clazz, entityId);
				// 将对像转化为json结构
				json = this.object2Json(o);
			}
			// 当对像已经成功加载，并已将对像的数据结构转化为json结构
				this.jsonPrint(response, JSONUtil.rowJson(json));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LinkageError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * JSON数据输出
	 * 
	 * @param response
	 * @param json
	 */
	protected void jsonPrint(HttpServletResponse response, String json) {
		response.setCharacterEncoding("UTF-8");
		try {
			System.out.println("JSON IS: " + json);
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 生产对像 如果是新增操作,则生产一个新的对像,并调用onInitEntity()初始化必要的数据
	 * 是新增操作,或是修改操作,是通过Entity_ID 是否为进行判段.
	 */
	protected Object initEntity(HttpServletRequest request, ActionForm form) {
		Object o = null;
		try {
			Map params = super.getRequestParameterMap(request);
			if (params.containsKey(StringPool.ENTITY_CLASS)
					&& StringUtils.isNotEmpty(params.get(
							StringPool.ENTITY_CLASS).toString())) {
				if (params.containsKey(StringPool.ENTITY_ID)
						&& StringUtils.isNotEmpty(params.get(
								StringPool.ENTITY_ID).toString())) {
					o = super.getEntity(request);
				} else {

					o = ClassUtils.forName(
							params.get(StringPool.ENTITY_CLASS).toString())
							.newInstance();

				}
				/*
				 * 如果没有从Request中取得Class类型，那么将要多子类中根据方法： protected Class
				 * getEntityClass()取得Class类型。
				 * 
				 */
			} else if (this.getEntityClass() != null) {
				if (params.containsKey(StringPool.ENTITY_ID)
						&& StringUtils.isNotEmpty(params.get(
								StringPool.ENTITY_ID).toString())) {
					o = super.getEntity(this.getEntityClass(), params.get(
							StringPool.ENTITY_ID).toString());

				} else {

					o = ClassUtils.forName(this.getEntityClass().getName())
							.newInstance();

				}
			}
			if (o != null) {
				BeanUtils.copyProperties(o, params);
				this.onInitEntity(request, o, form);
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
		} catch (LinkageError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}

	/**
	 * 取得分页使用的JSON数据
	 * 
	 * @param request
	 * @return
	 */
	protected String getPageJson(HttpServletRequest request) {
		return null;
	}

	protected String object2Json(Object o) {
		String json = null;
		// 将对像转化为json结构
		if (null != o) {
			Class clazz = o.getClass();
			if (ClassUtils.hasMethod(clazz, StringPool.REFLECTION_METHOD_MAP,
					null)) {
				json = JSONArray.fromObject(ReflectionUtils.invokeMethod(
						ClassUtils.getMethodIfAvailable(clazz,
								StringPool.REFLECTION_METHOD_MAP, null), o))
						.toString();
			} else if (ClassUtils.hasMethod(clazz,
					StringPool.REFLECTION_METHOD_JSON, null)) {
				json = "["
						+ ReflectionUtils
								.invokeMethod(
										ClassUtils
												.getMethodIfAvailable(
														clazz,
														StringPool.REFLECTION_METHOD_JSON,
														null), o).toString()
						+ "]";
			} else {
				json = JSONArray.fromObject(o).toString();
			}
		}
		return json;
	}
}
