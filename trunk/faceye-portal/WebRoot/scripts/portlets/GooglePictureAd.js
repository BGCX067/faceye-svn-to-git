com.faceye.portal.portlet.SinglePortlet={
		init:function(id,name){
	     var portlet = new Ext.ux.Portlet({
		   id : id + '_' + Ext.id(),
		   title : name,
		   tools : com.faceye.portal.PortletTools,
		   html:'<iframe id="content-iframe" frameborder="no" style="width:100%;height:280px;" src="/faceye-ext-portal/ad/google-picture-ad.jsp"></iframe>'
	      });
//	     portlet.on('afterlayout',function(p,layout){
//	    	 alert(1);
//	    	 p.load({
//		    	 url:BP+'ad/google-picture-ad.jsp',
//		    	 text: "正在加载,请稍候...",
//		    	 timeout: 30,
//		    	 scripts: true
//		     });
//	    	 p.doLayout();
//	     });
	     return portlet;
         }
};