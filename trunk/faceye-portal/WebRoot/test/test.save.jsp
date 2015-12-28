<%@ include file="/templates/headers/header.jsp"%>
<html>
<head>
<title>Test List</title>


</head>
<body>
<s:form action="save">
<table>
  
  <s:hidden name="o.id"></s:hidden>
  
  <tr>
  <td>
  Name
  </td>
  <td>
  <s:textfield  name="o.name"></s:textfield>
  </td>
   </tr>
  
 
  <tr>
  <td>
  Action:
  </td>
  <td>
  <s:textfield  name="o.action"></s:textfield>
  </td>
  </tr>
  
  <tr>
   <td colspon="2">
   <s:submit></s:submit>
   </td>
  </tr>
</table>
</s:form>
</body>
</html>
