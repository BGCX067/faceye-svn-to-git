package com.faceye.core.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.faceye.core.util.helper.StringPool;

public class DefaultController extends BaseMultiActionController {

	public ModelAndView home(HttpServletRequest request,
			HttpServletResponse response) {

		return new ModelAndView("default");
	}
/**
 * ȫ��ҳ��ת��
 * @param request
 * @param response
 * @return
 */
	public ModelAndView forward(HttpServletRequest request,
			HttpServletResponse response) {
		String forward = null;
		forward = getHttp().getParameter(request, StringPool.FORWARD);
		if (forward != null) {
			try {
				return new ModelAndView(forward);
			} catch (Exception e) {
				if (log.isDebugEnabled()) {
					log.debug(this.getClass().getName()
							+ ".forward(),����ҳ��ת��ʱ�����쳣,����ԭ����ת��·�������ڻ���ȷ,�쳣Ϊ:"
							+ e.toString());
				}
				return throwException(request, response, e);
			}
		} else {
			if (log.isDebugEnabled()) {
				log.debug(this.getClass() + ".forward(),û��ָ��ת��ҳҳ");
			}
			return new ModelAndView("default");
		}
	}
}
