<%@ include file="/templates/headers/header.jsp"%>
<html>
<head>
<title>Test List</title>


</head>
<body>

<table>
  <tr>
  <td>
  ID
  </td>
  <td>
  Name
  </td>
  <td>
  EDIT
  </td>
  </tr>
  <s:iterator value="page.items">
    <tr>
      <td>
      <s:property value="id"/>
      </td>
      <td>
      <s:property value="name"/>
      </td>
      <td>
<s:url value="update.do" id="urlid">
 <s:param name="id" value="%{id}" />
 
</s:url>
<s:a errorText="Sorry your request had an error." preInvokeJS="confirm('Are you sure you want to delete this item?')" href="%{urlid}">
                    EDIT</s:a>

      </td>
    </tr>
  </s:iterator>
</table>
</body>
</html>
