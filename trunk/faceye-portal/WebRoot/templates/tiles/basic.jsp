<%@ include file="/templates/headers/header.jsp"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<div id="Container">
			<div id="TopContainer">
				<tiles:insert name="TopContainer"></tiles:insert>
			</div>
			<div id="BasicModdleContainer">
					<tiles:insert name="MainContainer"></tiles:insert>
			</div>
			<div id="FooterContainer">
				<tiles:insert name="FooterContainer"></tiles:insert>
			</div>
		</div>
</body>
</html>