/**
 * www.faceye.com网络支持系统 
 * 作者:宋海鹏 ecsun@sohu.com/myecsun@hotmail.com/QQ:82676683/技术交流群:56927478
 * 说明:开源项目集porlet
 */
com.faceye.portal.portlet.SinglePortlet = {
	init : function(id, name) {
		var portlet = new Ext.ux.Portlet({
			id : id + '_' + Ext.id(),
			title : name,
			tools : com.faceye.portal.PortletTools
		});
		return portlet;
	}
}