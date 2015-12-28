package com.faceye.core.intercept;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoggerInterceptor implements MethodInterceptor {
	private final Log logger = LogFactory.getLog(getClass());

	public Object invoke(MethodInvocation arg0) throws Throwable {
		// TODO Auto-generated method stub
		Object result = null;
		try {
			logger.info("Faceye：" + arg0.getMethod().getDeclaringClass());
			logger.info("Faceye:" + arg0.getMethod().getName());
			result = arg0.proceed();
		} catch (Throwable e) {
			logger.error("Faceye" + e.toString());
		} finally {
			logger.info(">>>执行完毕");
		}
		return result;
	}

}
