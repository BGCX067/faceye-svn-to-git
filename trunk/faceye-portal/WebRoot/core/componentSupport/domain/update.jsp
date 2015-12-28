<%@ include file="/templates/headers/header.jsp"%>
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
			href="<c:url value="/scripts/ext/resources/css/ext-all.css"/>" />
		<script type="text/javascript"
			src="<c:url value="/scripts/ext/adapter/ext/ext-base.js"/>"></script>
		<!-- ENDLIBS -->
		<script type="text/javascript"
			src="<c:url value="/scripts/ext/ext-all.js"/>"></script>
			
			<script type="text/javascript"
			src="<c:url value="/scripts/common/common.js"/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/scripts/ext/examples/form/forms.css"/>" />
</head>
<script type="text/javascript">
Ext.onReady(Domain.form,Domain);
</script>
<body>
<div style="width:300px;">
    <div class="x-box-tl"><div class="x-box-tr"><div class="x-box-tc"></div></div></div>
    <div class="x-box-ml"><div class="x-box-mr"><div class="x-box-mc">
        <h3 style="margin-bottom:5px;">Simple Form</h3>
        <div id="form-ct">

        </div>
    </div></div></div>
    <div class="x-box-bl"><div class="x-box-br"><div class="x-box-bc"></div></div></div>
</div>


<div id="form-dialog" style="visibility:hidden;">
</div>

</body>
</html>