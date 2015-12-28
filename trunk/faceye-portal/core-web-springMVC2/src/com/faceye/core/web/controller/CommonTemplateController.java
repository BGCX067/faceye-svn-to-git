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
 * @���ߣ��κ���
 * @ϵͳ����: �������֧��ϵͳ
 * @����ʱ�䣺2007-1-22
 * @��Ȩ:�������
 * @˵��:�������ݲ���ģ����
 * @������com.faceye.core.web.controller
 */
public abstract class CommonTemplateController extends
		BaseMultiActionController {
	/**
	 * ���ݰ�
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
				log.debug(">>>>>>>>>>>>>>���쳣:" + e.toString());
			}
		}
		return result;
	}

	/**
	 * ����У��ʧ��
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
	 * ��ǰ������׼��
	 * 
	 * @param request
	 * @param command
	 * @param binder
	 */
	public abstract void beforBind(HttpServletRequest request, Object command,
			ServletRequestDataBinder binder);

	/**
	 * ���ݱ������,������ݵ��������޸Ĳ���.
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
	 * �����ݱ���ǰ��׼������
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
	 * �û�ʵ�ֳ�����
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public abstract ModelAndView beforeSave(HttpServletRequest request);

	/**
	 * ҵ�����ݱ������
	 * 
	 * @param request
	 * @param response
	 * @param mav
	 */
	public abstract ModelAndView doSave(HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * ���ݸ���׼��
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
	 * �û�ʵ�ָ���ǰ������׼��
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public abstract ModelAndView beforeUpdate(HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * ����ɾ������.
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
	 * �û�ʵ������ɾ��ҵ�����
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public abstract ModelAndView doRemove(HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * ���¼ɾ��
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
	 * ���ݲ�ѯ
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
