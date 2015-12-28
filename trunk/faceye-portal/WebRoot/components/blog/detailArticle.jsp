<%@ include file="/templates/headers/headerWithToolBar.jsp"%>
<%@ include file="/templates/headers/ext.jsp"%>
<%@ include file="/templates/headers/log.jsp"%>
<html>
	<head>
		<script type="text/javascript"
			src="<c:url value="/scripts/portlets/BlogManagerPanel.js"/>"></script>
		<title><fmt:message key="com.faceye.blog"></fmt:message>
		</title>
	</head>
	<script type="text/javascript">
//Ext.ux.TinyMCE.initTinyMCE();
    var url=location.search;
	var params=Ext.urlDecode(url.substring(1));
	var id='';
	if(params.id){
		id=params.id;
		Ext.onReady(com.faceye.portal.portlet.ArticleDetail.init,com.faceye.portal.portlet.ArticleDetail);
	}
</script>
	<body>

	</body>
</html>