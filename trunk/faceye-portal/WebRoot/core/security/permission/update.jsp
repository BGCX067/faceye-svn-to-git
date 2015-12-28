<%@ include file="/templates/headers/header.jsp"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<html:form action="permissionAction.do?method=save&entityClass=com.zaodian.core.service.security.model.Permission">
  <html:hidden property="id"></html:hidden>
  <table>
  <tr>
    <td>permission</td>
  </tr>
  <tr>
    <td>permissionName:</td>
    <td>
     <html:text property="name"></html:text>
    </td>
  </tr>
   <tr>
    <td>
     <html:submit></html:submit>
    </td>
  </tr>
</table>
</html:form>

</body>
</html>