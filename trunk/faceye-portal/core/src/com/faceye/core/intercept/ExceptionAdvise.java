package com.faceye.core.intercept;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.ServletException;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.dao.DataAccessException;

import com.faceye.core.util.exception.DBException;
import com.faceye.core.util.exception.ServiceException;


public class ExceptionAdvise implements ThrowsAdvice, MethodInterceptor {

	private final Log logger = LogFactory.getLog(getClass());

	public void afterThrowing(Method method, Object[] args, Object target,
			DataAccessException e) {
		// TODO Auto-generated method stub
		// if(logger.isDebugEnabled()){
		logger.error("com.faceye:" + method.getClass());
		logger.error("com.faceye:" + method.getName());
		logger.error("com.faceye:" + e.toString());
		// }
	}



	public void afterThrowing(Method method, Object[] args, Object target,
			ServiceException e) {
		// TODO Auto-generated method stub
		// if(logger.isDebugEnabled()){
		logger.error("com.faceye:" + method.getClass());
		logger.error("com.faceye:" + method.getName());
		logger.error("com.faceye:" + e.toString());
		// }
	}

	public void afterThrowing(Method method, Object[] args, Object target,
			Exception e) {
		// TODO Auto-generated method stub
		// if(logger.isDebugEnabled()){
		logger.error("com.faceye:" + method.getClass());
		logger.error("com.faceye:" + method.getName());
		logger.error("com.faceye:" + e.toString());
		// }
	}

	//
	public void afterThrowing(Method method, Object[] args, Object target,
			Throwable throwable) {
		// TODO Auto-generated method stub
		// if(logger.isDebugEnabled()){
		logger.error("com.faceye:" + method.getClass());
		logger.error("com.faceye:" + method.getName());
		logger.error("com.faceye:" + throwable.toString());
		// }
	}

	public void afterThrowing(Method method, Object[] args, Object target,
			ObjectNotFoundException e) {
		// TODO Auto-generated method stub
		// if(logger.isDebugEnabled()){
		logger.error("com.faceye:" + method.getClass());
		logger.error("com.faceye:" + method.getName());
		logger.error("com.faceye:" + e.toString());
		// }
	}

	public void afterThrowing(Method method, Object[] args, Object target,
			ServletException e) {
		// TODO Auto-generated method stub
		// if(logger.isDebugEnabled()){
		System.out.println(7);
		logger.error("com.faceye:" + method.getClass());
		logger.error("com.faceye:" + method.getName());
		logger.error("com.faceye:" + e.toString());
		// }
	}

	public void afterThrowing(ServiceException e) {
		// logger.error(":"+method.getClass());
		// logger.error("com.faceye::"+method.getName());
		logger.error("com.faceye:" + e.toString());
	}

	public void afterThrowing(Exception e) {
		logger.error("com.faceye:" + e.toString());
	}

	public void afterThrowing(DBException e) {
		logger.error("com.faceye:" + e.toString());
	}

	public Object invoke(MethodInvocation arg0) throws Throwable {
		// TODO Auto-generated method stub
		Object result;
		logger.error("<<-----------------Start com.faceye.log in Class----------------->>");
		logger.error("com.faceye:Current Run Class is-" + arg0.getMethod().getDeclaringClass());
		logger.error("com.faceye:Current Run method is-" + arg0.getMethod().getName());
		try {
			result = arg0.proceed();
			return result;
		} catch (ServiceException e) {
			logger.error("com.faceye:"+ arg0.getMethod().getDeclaringClass());
			logger.error("com.faceye:" + arg0.getMethod().getName());
			logger.error("com.faceye:" + e.toString());
			e.printStackTrace();
			return null;
		} catch (DBException e) {
			logger.error("com.faceye:" + arg0.getMethod().getDeclaringClass());
			logger.error("com.faceye:" + arg0.getMethod().getName());
			logger.error("com.faceye:" + e.toString());
			e.printStackTrace();
			return null;
		} catch (ServletException e) {
			logger.error("com.faceye:" + arg0.getMethod().getDeclaringClass());
			logger.error("com.faceye:" + arg0.getMethod().getName());
			logger.error("com.faceye:" + e.toString());
			e.printStackTrace();
			return null;
		} catch (DataAccessException e) {
			logger.error("com.faceye:" + arg0.getMethod().getDeclaringClass());
			logger.error("com.faceye:" + arg0.getMethod().getName());
			logger.error("com.faceye:" + e.toString());
			e.printStackTrace();
			return null;
		} catch (HibernateException e) {
			logger.error("com.faceye:" + arg0.getMethod().getDeclaringClass());
			logger.error("com.faceye:" + arg0.getMethod().getName());
			logger.error("com.faceye:" + e.toString());
			e.printStackTrace();
			return null;
		} catch (NullPointerException e) {
			logger.error("com.faceye:" + arg0.getMethod().getDeclaringClass());
			logger.error("com.faceye::" + arg0.getMethod().getName());
			logger.error("com.faceye:" + e.toString());
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			logger.error("com.faceye:" + arg0.getMethod().getDeclaringClass());
			logger.error("com.faceye::" + arg0.getMethod().getName());
			logger.error("com.faceye:" + e.toString());

			e.printStackTrace();
			return null;
		} catch (Throwable e) {
			logger.error("com.faceye:" + arg0.getMethod().getDeclaringClass());
			logger.error("com.faceye:" + arg0.getMethod().getName());
			logger.error("com.faceye:" + e.toString());
			return null;
		} finally {
			logger
					.info("<<-----------------END----------------->>");
		}

	}

}
