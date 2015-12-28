<%@ include file="/templates/headers/headerWithToolBar.jsp"%>
<%@ include file="/templates/headers/ext.jsp"%>
<%@ include file="/templates/headers/log.jsp"%>
<html>
	<head>
		<title><fmt:message key="com.faceye.home.default.title"></fmt:message>
			<fmt:message
				key="com.faceye.home.defatul.portlet.subcribe.title.message"></fmt:message>
		</title>
		<link rel="stylesheet" type="text/css"
			href="<c:url value="/scripts/ext-ui/Grid-Row-Action/Grid-Row-Action.css"/>" />
		<script type="text/javascript"
			src="<c:url value="/scripts/ext-ui/Grid-Row-Action/Grid-RowAction.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/scripts/portal/PortletSubscribe.js"/>"></script>
		<script type="text/javascript">
	      com.faceye.portal.UserSubscribePortlet.init();
        </script>
		<style type="text/css">
table#portlet-subscribe-view-template-table {
	font-size: 12px;
	width:95%;
}
table#portlet-subscribe-view-template-table table {
	font-size: 12px;
	width:95%;
}
div#portlet-subscribe-view-template-div{

}
.add-user-portlet {
	background-image:
		url(/faceye/scripts/ext/resources/images/default/tree/drop-add.gif)
		!important;
	)
}
table#user-subscribe-porltet-table {
   margins:5px 5px 0 5px;
}
</style>
	</head>
	<body>
		<div align="right">
			<br>
		</div>
		<div id="portlet-grid"></div>
	</body>
</html>
