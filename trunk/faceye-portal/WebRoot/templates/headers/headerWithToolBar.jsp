<%@ include file="header.jsp"%>
<!-- default tool bar -->
<div id="default-header">
	<div id="default-log">
		FaceYe
	</div>
	<div id="default-message">
		Welcome to FaceYe.com
	</div>
	<div id="default-my" style="float:right;">
		<c:if test="${user!=null}">
			<span>
				<fmt:message key="com.faceye.home.default.welcome"></fmt:message>
				${user.username}!
			</span>
		</c:if>
		<c:if test="${user==null}">
			<span>
				<fmt:message key="com.faceye.home.default.withoutLogin"></fmt:message>
				!
			</span>
		</c:if>
		<c:if test="${(param.method!='home')}">
		<span>
		  <fmt:message key="com.faceye.home.default.home"></fmt:message>
		</span></c:if>
		<c:if test="${user!=null}">
			<span>
				<html:link href="userResourceCategoryAction.do?method=myFeedHome"
					target="_blank">
					<fmt:message key="com.faceye.home.default.rssReader"></fmt:message>
				</html:link>
			</span>
			<span>
				<html:link href="portalContainerAction.do?method=my" target="_blank">
					<fmt:message key="com.faceye.home.default.desktop"></fmt:message>
				</html:link>
			</span>
		</c:if>
		<c:if test="${(user!=null)&&(user.username=='admin')}">
			<span>
				<html:link href="default.do?method=forward&forward=default"
					target="_blank">
					<fmt:message key="com.faceye.home.default.manager"></fmt:message>
				</html:link>
			</span>
		</c:if>
		<c:if test="${user==null}">
			<span>
				<html:link href="default.do?method=forward&forward=login">
					<fmt:message key="com.faceye.home.default.login"></fmt:message>
				</html:link>
			</span>
			<span>
				<html:link href="userAction.do?method=forward&forward=register"
					target="_blank">
					<fmt:message key="com.faceye.home.default.register"></fmt:message>
				</html:link>
			</span>
		</c:if>
		<c:if test="${user!=null}">
			<span>
				<html:link href="j_acegi_security_logout">
					<fmt:message key="com.faceye.home.default.logout"></fmt:message>
				</html:link>
			</span>
		</c:if>
	</div>
</div>
