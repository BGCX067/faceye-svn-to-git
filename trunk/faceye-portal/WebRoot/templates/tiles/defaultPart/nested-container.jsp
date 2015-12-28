<%@ include file="/templates/headers/header.jsp"%>
<html>
	<head>
		<title>Reorder TreePanel</title>
		<link rel="stylesheet" type="text/css"
			href="<c:url value="/scripts/ext/resources/css/ext-all.css"/>" />
		<script type="text/javascript"
			src="<c:url value="/scripts/ext/adapter/ext/ext-base.js"/>"></script>

		<!-- ENDLIBS -->
		<script type="text/javascript"
			src="<c:url value="/scripts/ext/ext-all.js"/>"></script>
		<!-- Common Styles for the examples -->
		<script type="text/javascript"
			src="<c:url value="/scripts/common/common.js"/>"></script>
		

		<style type="text/css">
	html, body {
        font:normal 12px verdana;
        margin:0;
        padding:0;
        border:0 none;
        overflow:hidden;
        height:100%;
    }
	#header{
	    background: url(images/header-bar.gif) repeat-x bottom;
	    border-bottom: 1px solid #083772;
	    padding:5px 4px;
	}
	#footer{
	    background: url(images/header-bar.gif) repeat-x bottom;
	    border-top: 1px solid #083772;
	    padding:2px 4px;
	    color:white;
	    font:normal 8pt arial,helvetica;
    }
	#nav {
	}
	#nav, #inner1, #inner2 {
	    padding:10px;
	}
	#content p {
	    margin:0px;
	}
	#nav li {
	    padding:2px;
	    padding-left:10px;
	    background-image:url(images/bullet.gif);
	    background-position: -3px 6px;
	    background-repeat: no-repeat;
	    font-size:8pt;
	    display: block;
    }
    .x-layout-panel-north, .x-layout-panel-south, #content .x-layout-panel-center{
        border:0px none;
    }
    #content .x-layout-panel-south{
        border-top:1px solid #aca899;
    }
    #content .x-layout-panel-center{
        border-bottom:1px solid #aca899;
    }
    #north{
	    font:normal 8pt arial, helvetica;
	    padding:4px;
	}
    </style>
		<script type="text/javascript">

	
	Ext.EventManager.onDocumentReady(WelcomeControl.init, WelcomeControl, true);
	
	
	function onClickHref(url){
	  alert("2f2_"+url);
	  Ext.get('info-iframe').dom.src=href;
	}
	</script>

	</head>
	<body class="ytheme-gray">

		<script type="text/javascript" src="../examples.js"></script>
		<!-- EXAMPLES -->
		<div id="container">
			<div id="north" class="x-layout-inactive-content">
				<html:link
					href="default.do?method=forward&forward=system.admin.face">System.admin.face</html:link>
				<html:link
					href="treeAction.do?method=update&entityClass=com.zaodian.core.service.security.model.Tree">Add Tree</html:link>
				<html:link
					href="treeAction.do?method=query&entityClass=com.zaodian.core.service.security.model.Tree">List Trees</html:link>
				<html:link
					href="resourceAction.do?method=update&entityClass=com.zaodian.core.service.security.model.Resource">Add Resource</html:link>
				<html:link
					href="resourceAction.do?method=query&entityClass=com.zaodian.core.service.security.model.Resource">List Resources</html:link>
				<html:link
					href="roleAction.do?method=update&entityClass=com.zaodian.core.service.security.model.Role">Add Role</html:link>
				<html:link
					href="roleAction.do?method=query&entityClass=com.zaodian.core.service.security.model.Role">List Role</html:link>
				<html:link
					href="permissionAction.do?method=update&entityClass=com.zaodian.core.service.security.model.Permission">Add Permission</html:link>
				<html:link
					href="permissionAction.do?method=query&entityClass=com.zaodian.core.service.security.model.Permission">List Permission</html:link>
				<html:link
					href="userAction.do?method=update&entityClass=com.zaodian.core.service.security.model.User">Add User</html:link>
				<html:link
					href="userAction.do?method=query&entityClass=com.zaodian.core.service.security.model.User">List User</html:link>
				<br />
				<!-- create Dialog Layout -->


				<!-- Ent Create Dialog Layout -->

			</div>
			<div id="nav" class="x-layout-inactive-content">
				<div id="tree-div">
				</div>
			</div>

			<div id="content" class="x-layout-inactive-content">
				
				<iframe id="content-iframe" frameborder="no"></iframe>
			</div>

		</div>


	</body>
</html>
