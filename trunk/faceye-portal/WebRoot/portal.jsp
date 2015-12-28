<%@ include file="/templates/headers/headerWithToolBar.jsp"%>
<%@ include file="/templates/headers/ext.jsp"%>
<%@ include file="/templates/headers/log.jsp"%>
<html>
	<head>
		<title><fmt:message key="com.faceye.home.default.title"></fmt:message>
		</title>
		<link rel="stylesheet" type="text/css"
			href="<c:url value="/css/portal.css"/>" />
		<link rel="stylesheet" type="text/css"
			href="<c:url value="/css/portlet.css"/>" />
		<script type="text/javascript"
			src="<c:url value="/scripts/portal/Portal-ui.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/scripts/ext-ui/TableCloseMenu/TabCloseMenu.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/components/navigation/feed/feed-viewer/FeedSubscribe.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/scripts/portal/Portal.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/scripts/lib/util/ClassLoader.js"/>"></script>
			<script type="text/javascript"
			src="<c:url value="/scripts/portlets/BlogManagerPanel.js"/>"></script>
		
		<script type="text/javascript">
   Ext.onReady(com.faceye.portal.Portal.init,com.faceye.portal.Portal);
	</script>

	</head>
	<body>
	
		<div id="subscribe-select-tree"></div>
	</body>
</html>