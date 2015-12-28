com.faceye.portal.portlet.SinglePortlet={
	init:function(id,name){
	     var portlet = new Ext.ux.Portlet({
		   id : id + '_' + Ext.id(),
		   title : name,
		   tools : com.faceye.portal.PortletTools,
		   html:'<iframe id="content-iframe" frameborder="no" style="width:100%;height:300px;" src="/faceye-ext-portal/ad/google-words-picture-ad.jsp"></iframe>'
	      });
	     return portlet;
     }
};