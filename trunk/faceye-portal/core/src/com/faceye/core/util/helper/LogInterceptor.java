package com.faceye.core.util.helper;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogInterceptor  implements MethodInterceptor{
    private final Log logger=LogFactory.getLog(this.getClass());
	public Object invoke(MethodInvocation arg0) throws Throwable {
		// TODO Auto-generated method stub
//		 TODO Auto-generated method stub
		Object result;
//		System.out.println("=="+arg0.getMethod().getDeclaringClass()+"==");
//		if(logger.isDebugEnabled()){
		logger.info(">>>"+arg0.getMethod().getDeclaringClass());

		logger.info(">>>"+arg0.getMethod().getName());
//		Object [] o= arg0.getArguments();
//		for(int i =0;i<o.length;i++){
//			logger.info(o[i].getClass());
//		}
//		}
		try{
		result=arg0.proceed();
		}catch(Throwable e){
//			if(logger.isDebugEnabled()){
		  logger.info(">>>"+arg0.getMethod().getDeclaringClass());
		  logger.info(">>>>"+arg0.getMethod().getName());
		  logger.info(">>>>"+e.toString());
//			}
		  return null;
		}finally{
//			if(logger.isDebugEnabled()){

//			}
		}
		
		return result;
	}

}
