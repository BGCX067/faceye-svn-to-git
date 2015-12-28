package com.faceye.test.core.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.faceye.core.service.iface.IService;
import com.faceye.core.web.controller.BaseMultiActionController;
import com.faceye.test.core.domain.Testdao;

public class CrudControllerTest extends BaseMultiActionController {
	private IService service;
	public void setService(IService service){
		this.service=service;
	}
	 public ModelAndView saveOrUpdate(HttpServletRequest request,HttpServletResponse response,Testdao dao){
		   System.out.println(dao.getName());
		   Testdao t=new Testdao();
		   try {
			ServletRequestDataBinder binder=super.createBinder(request, t);
			binder.bind(request);
			BindingResult reult=binder.getBindingResult();
			this.service.getBaseService().getBaseHibernateService().saveOrUpdateObject(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return null;
	   }
}
