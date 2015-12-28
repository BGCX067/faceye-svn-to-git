<%@ include file="/templates/headers/header.jsp"%>
<html>
	<head>
		<title><fmt:message key="com.faceye.home.default.title"></fmt:message>
			<fmt:message key="com.faceye.home.defatul.title.message"></fmt:message>
		</title>
		<link rel="stylesheet" type="text/css"
			href="<c:url value="/css/home.css"/>" />
	</head>
	<body>
		<div id="default-header">
			<div id="default-log">
				FaceYe
			</div>
			<div id="default-message">
				Welcome to FaceYe.com
			</div>
			<div id="default-my">
				<div id="default-toolbar-feed-home">
					<html:link href="userResourceCategoryAction.do?method=myFeedHome"
						target="_blank">
						<fmt:message key="com.faceye.home.default.rssReader"></fmt:message>
					</html:link>
				</div>
				<div id="default-toolbar-manager">
					<html:link href="default.do?method=forward&forward=default"
						target="_blank">
						<fmt:message key="com.faceye.home.default.manager"></fmt:message>
					</html:link>
				</div>
				<div id="default-toolbar-column-style-select">
					<html:link href="#"
						onclick="com.faceye.portal.Portal.onClickColumnSelect()">
						<fmt:message key="com.faceye.home.default.panelColumn"></fmt:message>
					</html:link>
				</div>
				<c:if test="${user==null}">
					<div id="default-toolbar-register">
						<html:link href="userAction.do?method=forward&forward=register"
							target="_blank">
							<fmt:message key="com.faceye.home.default.register"></fmt:message>
						</html:link>
					</div>
					<div id="default-toolbar-login"
						onclick="com.faceye.compoents.core.security.LoginWindow.init()">
						<html:link href="#"
							onclick="com.faceye.compoents.core.security.LoginWindow.init()">
							<fmt:message key="com.faceye.home.default.login"></fmt:message>
						</html:link>
					</div>
				</c:if>
				<c:if test="${user!=null}">
					<div id="default-toolbar-logout">
						<html:link href="j_acegi_security_logout">
							<fmt:message key="com.faceye.home.default.logout"></fmt:message>
						</html:link>
					</div>
				</c:if>
			</div>
		</div>
	</body>
</html>