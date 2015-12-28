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
 * 全局页面转发
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
							+ ".forward(),进行页面转发时发生异常,可能原因是转发路径不存在或不正确,异常为:"
							+ e.toString());
				}
				return throwException(request, response, e);
			}
		} else {
			if (log.isDebugEnabled()) {
				log.debug(this.getClass() + ".forward(),没有指定转发页页");
			}
			return new ModelAndView("default");
		}
	}
}
