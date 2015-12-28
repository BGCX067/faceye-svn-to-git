<%@ include file="/templates/headers/header.jsp"%>
<html>
	<head>
		<title>FaceYe::</title>


		<link rel="stylesheet" type="text/css"
			href="<c:url value="/scripts/ext/resources/css/ext-all.css"/>" />
		<script type="text/javascript"
			src="<c:url value="/scripts/ext/adapter/ext/ext-base.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/scripts/ext/ext-all.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/scripts/util/Util.js"/>"></script>
			 <script type="text/javascript"
			src="<c:url value="/scripts/common/HomeBuilder.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/scripts/common/Default.js"/>"></script>
         
		<!-- 
			<script type="text/javascript"
			src="<c:url value="/scripts/ext/examples/examples.js"/>"></script>
			<script type="text/javascript"
			src="<c:url value="/scripts/ext/examples/examples.js"/>"></script>
			<link rel="stylesheet" type="text/css"
			href="<c:url value="/css/home.css"/>" /> 
			-->
		<link rel="stylesheet" type="text/css"
			href="<c:url value="/css/Common.css"/>" />
		<script type="text/javascript"
			src="<c:url value="/scripts/ext/build/locale/ext-lang-zh_CN.js"/>"></script>
		<script type="text/javascript">
   Ext.onReady(Default.init,Default);
	</script>
	</head>
<body>
<html:form action="roleAction.do?method=save&entityClass=com.zaodian.core.service.security.model.Role">
  <table>
  <tr>
    <td>register</td>
  </tr>
  <tr>
    <td>Role Name:</td>
    <td>
    <html:hidden property="id"/>
    <html:text property="name"/>
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