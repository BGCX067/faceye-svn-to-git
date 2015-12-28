<%@ include file="/templates/headers/header.jsp"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<div id="message">
		<html:messages id="messages" message="true">
			<bean:write name="messages" />
		</html:messages>
</div>
</body>
</html>