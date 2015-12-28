package com.faceye.core.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.faceye.core.util.exception.ServiceException;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.util.helper.StringPool;
import com.faceye.core.util.helper.StringUtil;

/**
 * 
 * @作者：宋海鹏
 * @系统名称: 早点网络支持系统
 * @开发时间：2007-1-22
 * @版权:早点网络
 * @说明:常用数据操作模板类
 * @包名：com.faceye.core.web.controller
 */
public abstract class CommonTemplateController extends
		BaseMultiActionController {
	/**
	 * 数据绑定
	 * 
	 * @param request
	 * @param command
	 * @return
	 * @throws Exception
	 */
	protected BindingResult bindObject(HttpServletRequest request,
			Object command) {
		BindingResult result = null;
		ServletRequestDataBinder binder = null;
		try {
			binder = super.createBinder(request, command);
			beforBind(request, command, binder);
			binder.bind(request);
			Validator[] validators = getValidators();
			if (validators != null) {
				for (Validator validator : validators) {
					if (validator.supports(command.getClass())) {
						ValidationUtils.invokeValidator(validator, command,
								binder.getBindingResult());
					}
				}
			}
			result = binder.getBindingResult();
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug(">>>>>>>>>>>>>>绑定异常:" + e.toString());
			}
		}
		return result;
	}

	/**
	 * 数据校验失败
	 * 
	 * @param request
	 * @return
	 */
	protected ModelAndView afterBindError(HttpServletRequest request,
			BindingResult result, ModelAndView mav) {
		mav.addAllObjects(result.getModel());
		ModelAndView beforeSaveModelAndView = beforeSave(request);
		mav.addAllObjects(beforeSaveModelAndView.getModelMap());
		return mav;
	}

	/**
	 * 绑定前的数据准备
	 * 
	 * @param request
	 * @param command
	 * @param binder
	 */
	public abstract void beforBind(HttpServletRequest request, Object command,
			ServletRequestDataBinder binder);

	/**
	 * 数据保存操作,完成数据的新增与修改操作.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = doSave(request, response);
		if(mav==null){
			return this.saveSuccess(request, response);
		}
		return mav;
	}

	/**
	 * 做数据保存前的准备工作
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView prepareSave(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = beforeSave(request);
		if(mav.getModelMap().isEmpty()||mav.getModel().isEmpty()){
//			mav.addObject("command",new HashMap());
//			mav.addAllObjects(new HashMap());
			BindException exception=null;
			Errors errors=null;
			mav.addObject(exception);
			
		}
		return mav;
	}

	/**
	 * 用户实现抽象类
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public abstract ModelAndView beforeSave(HttpServletRequest request);

	/**
	 * 业务数据保存操作
	 * 
	 * @param request
	 * @param response
	 * @param mav
	 */
	public abstract ModelAndView doSave(HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * 数据更新准备
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView prepareUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = beforeUpdate(request, response);
		return mav;
	}

	/**
	 * 用户实现更新前的数据准备
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public abstract ModelAndView beforeUpdate(HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * 数据删除操作.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView remove(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = doRemove(request, response);
		return mav;
	}

	/**
	 * 用户实现数据删除业务操作
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public abstract ModelAndView doRemove(HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * 多记录删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView multiRemove(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = doMultiRemove(request, response);
		return mav;
	}

	public abstract ModelAndView doMultiRemove(HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * 数据查询
	 */
	public ModelAndView query(HttpServletRequest request,
			HttpServletResponse response) {
		// ModelAndView mav=doQuery(request,response);
		Object o = doQuery(request, response);
		PaginationSupport ps = null;
		ModelAndView mav = null;
		String forward = null;
		String queryType = null;
		String[] params = null;
		Object[] values = null;
		StringUtil stringUtil = new StringUtil();
		int currentIndex = getHttp().getCurrentIndex(request);
		try {
			if (o instanceof Map) {
				HashMap map = (HashMap) o;
				if (map.containsKey(StringPool.FORWARD)) {
					forward = map.get(StringPool.FORWARD).toString();
				}
				if (map.containsKey(StringPool.QUERY_TYPE)) {
					queryType = map.get(StringPool.QUERY_TYPE).toString();
					if (queryType
							.equals(StringPool.QUERY_TYPE_BY_DetachedCriteria)) {
						DetachedCriteria detachedCriteria = (DetachedCriteria) map
								.get(StringPool.QUERY_DetachedCriteria);
						ps = getService().getBaseService()
								.getBaseHibernateService().getPage(
										detachedCriteria, currentIndex);

					} else if (queryType.equals(StringPool.QUERY_TYPE_BY_HQL)) {
						String hql = map.get(StringPool.QUERY_HQL).toString();

						if (map.containsKey(StringPool.QUERY_PARAMS)) {
							String paramList = map.get(StringPool.QUERY_PARAMS)
									.toString();
							params = stringUtil.split(paramList,
									StringPool.QUERY_DISTRIBUTE_CHARACTER);
							values = (Object[]) map
									.get(StringPool.QUERY_VALUES);
						}
						ps = getService().getBaseService()
								.getBaseHibernateService().getPageByHQL(hql,
										params, values, currentIndex);

					} else if (queryType
							.equals(StringPool.QUERY_TYPE_BY_NAMED_HQL)) {
						String queryName = map.get(StringPool.QUERY_NAMED_HQL)
								.toString();
						if (map.containsKey(StringPool.QUERY_PARAMS)) {
							String paramList = map.get(StringPool.QUERY_PARAMS)
									.toString();
							params = stringUtil.split(paramList,
									StringPool.QUERY_DISTRIBUTE_CHARACTER);
							values = (Object[]) map
									.get(StringPool.QUERY_VALUES);
						}
						ps = getService().getBaseService()
								.getBaseHibernateService().getPage(queryName,
										params, values, currentIndex);

					} else if (queryType
							.equals(StringPool.QUERY_TYPE_BY_NAMED_SQL)) {

					} else if (queryType.equals(StringPool.QUERY_TYPE_BY_SQL)) {

					} else {

					}
				}
			} else {

			}
			 mav=new ModelAndView(forward,StringPool.PAGINATION_SUPPORT,ps);
		} catch (ServiceException e) {

		}
		return mav;
	}

	public abstract Object doQuery(HttpServletRequest request,
			HttpServletResponse response);
}
