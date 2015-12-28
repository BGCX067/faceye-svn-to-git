<%@ include file="/templates/headers/header.jsp"%>
<html>
	<head>

		<title><tiles:getAsString name="title" />
		</title>

	</head>


	<body>
		<div id="Container">
			<div id="TopContainer">
				<tiles:insert name="TopContainer"></tiles:insert>
			</div>
			<div id="MiddleContainer">
				<div id="AdminLeftContainer">
					<tiles:insert name="LeftContainer"></tiles:insert>
				</div>
				<div id="AdminMainContainer">
					<tiles:insert name="MainContainer"></tiles:insert>
				</div>
			</div>
			<div id="FooterContainer">
				<tiles:insert name="FooterContainer"></tiles:insert>
			</div>
		</div>
	</body>
</html>
