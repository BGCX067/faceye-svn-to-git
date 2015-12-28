package com.faceye.test.core.service;

import junit.framework.TestCase;
import com.faceye.core.service.iface.IBaseService;
import org.springframework.web.context.*;
/**
*²âÊÔ·´Éä
*/

public class TestReflect extends TestCase{
	 public void setUp(){
   	}
	
   public void testInterface(){
   	System.out.println(IBaseService.class.getName());
   	System.out.println(WebApplicationContext.class.getName() + ".ROOT");
    }
   
   public void testelseIf(){
	   int a=50;
	   if(a>10){
		   System.out.println("1");
	   }else if(a>20){
		   System.out.println("2");
	   }else if(a>30){
		   System.out.println("3");
	   }else{
		   System.out.println("-");
	   }
   }
}