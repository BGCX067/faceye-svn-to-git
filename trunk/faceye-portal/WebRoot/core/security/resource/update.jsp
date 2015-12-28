<%@ include file="/templates/headers/header.jsp"%>
<html>
	<head>
		<title>Insert title here</title>
	</head>
	<body>

		<html:form
			action="resourceAction.do?method=save&entityClass=com.zaodian.core.service.security.model.Resource"
			method="post">
			<html:hidden property="id"/>
			<table>
				<tr>
					<td>
						Resource Name:
					</td>
					<td>
						<html:text property="name"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						Resource Type:
					</td>
					<td>
						<html:select property="resourceType">
							<c:forEach items="${resourceTypes}" var="r">
								<html:option value="${r.key}">${r.value}</html:option>
							</c:forEach>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						Resource String:
					</td>
					<td>
						<html:text property="resourceStr" size="50"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<html:submit></html:submit>

					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
