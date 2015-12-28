package com.faceye.core.web.controller;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.StringConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.faceye.core.service.iface.IService;
import com.faceye.core.service.security.model.User;
import com.faceye.core.util.helper.CollectionUtil;
import com.faceye.core.util.helper.DateConverter;
import com.faceye.core.util.helper.Http;
import com.faceye.core.util.helper.StringPool;

public abstract class BaseDispatchAction extends DispatchAction {

	protected final static String SAVE = StringPool.ENTITY_SAVE;
	protected final static String LIST = StringPool.ENTITY_LIST;
	protected final static String DETAIL = StringPool.ENTITY_DETAIL;
	protected Log log = LogFactory.getLog(this.getClass());

	private IService service = null;

	protected Http getHttp() {
		return Http.getHttp();
	}

	/**
	 * ��Bean Factory ȡ��Bean
	 * 
	 * @param beanName
	 * @return
	 */
	public Object getBean(String beanName) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servlet.getServletContext());
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

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (isCancelled(request)) {
			ActionForward af = cancelled(mapping, form, request, response);
			if (af != null) {
				return af;
			}
		}
		// Get the parameter. This could be overridden in subclasses.
		String parameter = getParameter(mapping, form, request, response);
		// Get the method's name. This could be overridden in subclasses.
		String name = getMethodName(mapping, form, request, response, parameter);
		// Prevent recursive calls
		if ("execute".equals(name) || "perform".equals(name)) {
			String message = messages.getMessage("dispatch.recursive", mapping
					.getPath());
			log.error(message);
			throw new ServletException(message);
		}

		// Invoke the named method, and return the result
		return dispatchMethod(mapping, form, request, response, name);
	}

	protected ActionMessages getMessages(HttpServletRequest request) {
		ActionMessages messages = null;
		HttpSession session = request.getSession();

		if (request.getAttribute(Globals.MESSAGE_KEY) != null) {
			messages = (ActionMessages) request
					.getAttribute(Globals.MESSAGE_KEY);
			saveMessages(request, messages);
		} else if (session.getAttribute(Globals.MESSAGE_KEY) != null) {
			messages = (ActionMessages) session
					.getAttribute(Globals.MESSAGE_KEY);
			saveMessages(request, messages);
			session.removeAttribute(Globals.MESSAGE_KEY);
		} else {
			messages = new ActionMessages();
		}

		return messages;
	}

	protected ActionForward globalMessage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("MESSAGE");
	}

	protected ActionForward globalMessage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, ActionMessages messages) {
		if (!messages.isEmpty()) {
			super.saveMessages(request, messages);
		}
		return this.globalMessage(mapping, form, request, response);
	}

	protected void removeFormBean(ActionMapping mapping,
			HttpServletRequest request) {
		// Remove the obsolete form bean
		if (mapping.getAttribute() != null) {
			if ("request".equals(mapping.getScope())) {
				request.removeAttribute(mapping.getAttribute());
			} else {
				HttpSession session = request.getSession();
				session.removeAttribute(mapping.getAttribute());
			}
		}
	}

	protected void updateFormBean(ActionMapping mapping,
			HttpServletRequest request, ActionForm form) {
		// Remove the obsolete form bean
		if (mapping.getAttribute() != null) {
			if ("request".equals(mapping.getScope())) {
				request.setAttribute(mapping.getAttribute(), form);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute(mapping.getAttribute(), form);
			}
		}
	}

	protected ActionForm getActionForm(ActionMapping mapping,
			HttpServletRequest request) {
		ActionForm actionForm = null;

		// Remove the obsolete form bean
		if (mapping.getAttribute() != null) {
			if ("request".equals(mapping.getScope())) {
				actionForm = (ActionForm) request.getAttribute(mapping
						.getAttribute());
			} else {
				HttpSession session = request.getSession();
				actionForm = (ActionForm) session.getAttribute(mapping
						.getAttribute());
			}
		}
		return actionForm;
	}

	protected ActionForward throwErrors(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	protected ActionForward throwErrors(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			Exception e) {
		log.debug(">>>>faceye error:" + e.toString());
		return null;
	}

	protected ActionForward throwErrors(Exception e) {
		return null;
	}

	/**
	 * ���񿽱�����
	 */
	protected Object copyForm2Model(Object o, ActionForm form) {
		try {
			BeanUtils.copyProperties(o, form);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			log.error(">>>>faceye error when copyForm2Model:" + e.toString());
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			log.error(">>>>faceye error when copyForm2Model:" + e.toString());
		}

		return o;
	}

	protected ActionForm copyModel2Form(ActionForm form, Object o) {
		try {
			BeanUtils.copyProperties(form, o);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			log.error(">>>>faceye error when copyModel2Form:" + e.toString());
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			log.error(">>>>faceye error when copyModel2Form:" + e.toString());
		}

		return form;
	}

	protected Object getEntity(Class entityClass, Serializable id) {
		Object o = null;
		try {
			o = getService().getBaseService().getBaseHibernateService()
					.getObject(entityClass, id);
		} catch (Exception e) {
			log.error(e.toString());
		}
		return o;
	}

	/**
	 * 加载实体,根据实体类及ID
	 * 
	 * @param entityClass
	 * @param request
	 * @return
	 */
	protected Object getEntity(String entityClassName, Serializable id) {
		Object o = null;
		Class classz = null;
		try {
			if (id != null && StringUtils.isNotEmpty(entityClassName)) {
				classz = Class.forName(entityClassName);
				o = getService().getBaseService().getBaseHibernateService()
						.getObject(classz, id);
			}
		} catch (Exception e) {
			log.error(e.toString());
		}
		return o;
	}

	protected Object getEntity(Class entityClass, HttpServletRequest request) {
		Object o = null;
		Serializable id = this.getHttp().getEntityId(request);
		if (id != null) {
			o = this.getEntity(entityClass, id);
		}
		return o;
	}

	protected Object getEntity(HttpServletRequest request) {
		Object o = null;
		Map params = this.getRequestParameterMap(request);
		if (params.containsKey(StringPool.ENTITY_CLASS)
				&& params.containsKey(StringPool.ENTITY_ID)) {
			if (StringUtils.isNotEmpty(params.get(StringPool.ENTITY_CLASS)
					.toString())
					&& StringUtils.isNotEmpty(params.get(StringPool.ENTITY_ID)
							.toString())) {
				String entityClassName = params.get(StringPool.ENTITY_CLASS)
						.toString();
				Serializable id = params.get(StringPool.ENTITY_ID).toString();
				try {
					o = this.getEntity(entityClassName, id);
				} catch (LinkageError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return o;
	}

	/**
	 * 取得当前用户
	 * 
	 * @param request
	 * @return
	 */
	protected User getUser(HttpServletRequest request) {
		Object username = request.getSession().getAttribute(
				StringPool.BLOG_USER);
		if (null != username && StringUtils.isNotEmpty(username.toString())) {
			return (User) this.getService().getBaseService()
					.getBaseHibernateService().getObject(User.class,
							"username", username);
		} else {
			Serializable userId = "";
			userId = this.getHttp().getUserId(request);
			if (null != userId) {
				return (User) this.getEntity(User.class, userId);
			} else {
				return null;
			}
		}

	}

	protected ActionForward baseSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, Object o) {
		try {
			getService().getBaseService().getBaseHibernateService()
					.saveOrUpdateObject(o);
		} catch (Exception e) {
			return this.throwErrors(mapping, form, request, response, e);
		}
		return this.globalMessage(mapping, form, request, response);
	}

	protected ActionForward baseSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, Object o,
			ActionMessages messages) {
		try {
			getService().getBaseService().getBaseHibernateService()
					.saveOrUpdateObject(o);
			if (!messages.isEmpty()) {
				this.saveMessages(request, messages);
				return this.globalMessage(mapping, form, request, response);
			}
		} catch (Exception e) {
			return this.throwErrors(mapping, form, request, response, e);
		}
		return this.globalMessage(mapping, form, request, response);
	}

	protected ActionForward baseSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, Object o,
			String forwardName) {
		try {
			getService().getBaseService().getBaseHibernateService()
					.saveOrUpdateObject(o);
			return mapping.findForward(forwardName);
		} catch (Exception e) {
			return this.throwErrors(mapping, form, request, response, e);
		}
	}

	protected ActionForward baseSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, Object o,
			ActionMessages messages, String forwardName) {
		try {
			if (o == null) {

				return this.globalMessage(mapping, form, request, response);
			}
			getService().getBaseService().getBaseHibernateService()
					.saveOrUpdateObject(o);
			if (!messages.isEmpty()) {
				super.saveMessages(request, messages);
			}
			if (StringUtils.isNotEmpty(forwardName)) {
				return mapping.findForward(forwardName);
			} else {
				return this.globalMessage(mapping, form, request, response);
			}
		} catch (Exception e) {
			return this.throwErrors(mapping, form, request, response, e);
		}
	}

	/**
	 * 保存数据
	 * 
	 * @param o
	 */
	protected void saveOrUpate(Object o) {
		try {
			this.getService().getBaseService().getBaseHibernateService()
					.saveOrUpdateObject(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected ActionForward baseQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String forward = this.getHttp().getParameter(request,
				StringPool.FORWARD) == null ? null : this.getHttp()
				.getParameter(request, StringPool.FORWARD);
		String queryType = this.getHttp().getParameter(request,
				StringPool.QUERY_TYPE) == null ? null : this.getHttp()
				.getParameter(request, StringPool.QUERY_TYPE);
		String params = this.getHttp().getParameter(request,
				StringPool.QUERY_PARAMS) == null ? null : this.getHttp()
				.getParameter(request, StringPool.QUERY_PARAMS);
		String values = this.getHttp().getParameter(request,
				StringPool.QUERY_VALUES) == null ? null : this.getHttp()
				.getParameter(request, StringPool.QUERY_VALUES);
		String entityClass = this.getHttp().getParameter(request,
				StringPool.ENTITY_CLASS) == null ? null : this.getHttp()
				.getParameter(request, StringPool.ENTITY_CLASS);
		String namedHQL = this.getHttp().getParameter(request,
				StringPool.QUERY_NAMED_HQL) == null ? null : this.getHttp()
				.getParameter(request, StringPool.QUERY_NAMED_HQL);

		return null;
	}

	public ActionForward forward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String forward = getHttp().getParameter(request, StringPool.FORWARD);
		if (StringUtils.isEmpty(forward)) {
			return throwErrors(mapping, form, request, response);
		}
		return mapping.findForward(forward);
	}

	/**
	 * 取得request parameters map
	 * 
	 * @param request
	 * @return
	 */
	protected Map getRequestParameterMap(HttpServletRequest request) {
		return CollectionUtil.getCollectionUtil().transRequestParametersMap(
				request.getParameterMap());
	}

	/**
	 * 根据KEY值，取得request parameter map 中的一个值
	 * 
	 * @param key
	 * @param request
	 * @return
	 */
	protected String get(Object key, HttpServletRequest request) {
		Map params = this.getRequestParameterMap(request);
		if (params.containsKey(key)) {
			if (null != params.get(key)) {
				return params.get(key).toString();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			Map source = this.getRequestParameterMap(request);
			String forward = source.get(StringPool.FORWARD) == null ? "system.tree.common.list"
					: source.get(StringPool.FORWARD).toString();
			return mapping.findForward(forward);
		} catch (Exception e) {
			return throwErrors(mapping, form, request, response, e);
		}
	}

	public IService getService() {
		return service;
	}

	public void setService(IService service) {
		this.service = service;
	}
}
