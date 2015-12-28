<%@ include file="/templates/headers/header.jsp"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<table>
  <tr>
  <td>


  <html:link href="default.do?method=forward&forward=default">Home</html:link>
  </td>
    <td>


    
    <html:link href="user.do?method=forward&forward=save">Register</html:link>
    </td>
    <td>
     <a href="default.do?method=forward&forward=system.admin.index">Manage</a>
    </td>
    <td>
    	<html:link href="default.do?method=forward&forward=test.feed-viewer">Test Feed Viewer</html:link>
    </td>
    <td>
      <html:link href="default.do?method=forward&forward=system.admin.face">System.admin.facce</html:link>
    </td>
      </tr>
</table>
</body>
</html>