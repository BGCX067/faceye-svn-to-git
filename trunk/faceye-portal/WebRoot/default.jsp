<%@ include file="/templates/headers/header.jsp"%>
<html>
	<head>
		<title><fmt:message key="com.faceye.home.default.title"></fmt:message><fmt:message key="com.faceye.home.defatul.title.message"></fmt:message></title>
	</head>
	<body>
		<c:redirect url="default.do?method=forward&forward=home"></c:redirect>
	</body>
</html>