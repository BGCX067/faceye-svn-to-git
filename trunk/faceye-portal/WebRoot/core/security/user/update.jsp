<%@ include file="/templates/headers/headerWithToolBar.jsp"%>
<%@ include file="/templates/headers/ext.jsp"%>
<%@ include file="/templates/headers/log.jsp"%>
<html>
	<head>
		<title><fmt:message key="com.faceye.home.default.title"></fmt:message>-<fmt:message key="com.faceye.user.register"></fmt:message></title>
		<script type="text/javascript"
			src="<c:url value="/scripts/ext-ui/TableCloseMenu/TabCloseMenu.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/scripts/portal/Portal-ui.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/scripts/portal/Portal.js"/>"></script>
		<link rel="stylesheet" type="text/css"
			href="<c:url value="/css/portal.css"/>" />
		<link rel="stylesheet" type="text/css"
			href="<c:url value="/css/portlet.css"/>" />
		<script type="text/javascript"
			src="<c:url value="/scripts/portlets/UserRegisterPortlet.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/scripts/components/security/user/User.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/scripts/ext-ui/RemoteValidator/RemoteValidator.js"/>"></script>
	</head>
	<script type="text/javascript">
Ext.onReady(com.faceye.compoents.core.security.Register.init,com.faceye.compoents.core.security.Register);
</script>
	<body>


		<div id="user">

		</div>

	</body>
</html>