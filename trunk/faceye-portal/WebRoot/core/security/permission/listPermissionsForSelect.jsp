<%@ include file="/templates/headers/header.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/extremecomponents.css"/>"/>
<html>
	<head>
		<title>Insert title here</title>
	</head>
<body>
<html:form action="roleAction.do">
<table bgcolor="red" width="100%">
		  <tr>
		    <td>
		    1
		    </td>
		    <td>
		    2
		    </td>
		  </tr>
		  <c:forEach items="${permissions}" var="item">
		   <tr>
		     <td>
		     
		      <form:checkbox path="id" value="${item.id}"/>
		     </td>
		     <td>
		     ${item.name}
		     </td>
		   </tr>
		  </c:forEach>
		  <tr>
		    <td>
		    <input type="submit"/>
		    </td>
		  </tr>
		</table>
</html:form>
	

	</body>
</html>
