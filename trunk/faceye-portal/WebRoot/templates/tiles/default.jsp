<%@ include file="/templates/headers/header.jsp"%>
<html>
	<head>

		<title><tiles:getAsString name="title" />
		</title>
	</head>
	<style type="text/css">

</style>
	<body>
		<div id="Container">
			<div id="TopContainer">
				<tiles:insert name="TopContainer"></tiles:insert>
			</div>
			<div id="MiddleContainer">
				<div id="LeftContainer">
					<tiles:insert name="LeftContainer"></tiles:insert>
				</div>

				<div id="MainContainer">
					<tiles:insert name="MainContainer"></tiles:insert>
				</div>

				<div id="RightContainer">
					<tiles:insert name="RightContainer"></tiles:insert>
				</div>
			</div>
			<div id="FooterContainer">
				<tiles:insert name="FooterContainer"></tiles:insert>
			</div>
		</div>
	</body>
</html>
