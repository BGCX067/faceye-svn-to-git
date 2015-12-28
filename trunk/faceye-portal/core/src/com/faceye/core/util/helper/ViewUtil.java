package com.faceye.core.util.helper;

public class ViewUtil {
   private static ViewUtil viewUtil=null;
   private ViewUtil(){
	   
   }
   public static synchronized ViewUtil getViewUtil(){
	   if(viewUtil==null){
		   viewUtil=new ViewUtil();
	   }
	   return viewUtil;
   }

}
