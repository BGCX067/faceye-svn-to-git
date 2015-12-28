<%@ include file="/templates/headers/headerWithToolBar.jsp"%>
<%@ include file="/templates/headers/ext.jsp"%>
<%@ include file="/templates/headers/log.jsp"%>
<html>
	<head>
		<title><fmt:message key="com.faceye.home.default.title"></fmt:message>
			<fmt:message key="com.faceye.home.default.login"></fmt:message>
		</title>
		
	</head>

	<script type="text/javascript">
Ext.onReady(com.faceye.compoents.core.security.LoginForm.init,com.faceye.compoents.core.security.LoginForm);
</script>
	<body>
	</body>
</html>