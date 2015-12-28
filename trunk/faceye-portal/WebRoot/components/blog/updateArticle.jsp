<%@ include file="/templates/headers/headerWithToolBar.jsp"%>
<%@ include file="/templates/headers/ext-taglib-header.jsp"%>
<%@ include file="/templates/headers/log.jsp"%>
<html>
	<head>
		<script type="text/javascript"
			src="<c:url value="/scripts/ext-ui/miframe/miframe-min.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/scripts/ext-ui/TinyMCE/tiny_mce/tiny_mce.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/scripts/ext-ui/TinyMCE/Ext.ux.TinyMCE.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/scripts/portlets/BlogManagerPanel.js"/>"></script>
		<title><fmt:message key="com.faceye.home.default.title"></fmt:message>
			<fmt:message key="com.faceye.blog.article.new"></fmt:message></title>
	</head>
	<script type="text/javascript">
Ext.ux.TinyMCE.initTinyMCE();
    var url=location.search;
	var params=Ext.urlDecode(url.substring(1));
	var id='';
	if(params.id){
		id=params.id;
		Ext.onReady(com.faceye.portal.portlet.ArticleEditForm.init,com.faceye.portal.portlet.ArticleEditForm);
	}else{
	   Ext.onReady(com.faceye.portal.portlet.ArticleEditForm.init,com.faceye.portal.portlet.ArticleEditForm);
	}
</script>
	<body>

	</body>
</html>