<%@ include file="/templates/headers/ext-taglib-header.jsp"%>
<html>
	<head>
		<title>List Compontent</title>
		<link rel="stylesheet" type="text/css"
			href="<c:url value="/scripts/ext/examples/grid/grid-examples.css"/>" />
			<script type="text/javascript"
			src="<c:url value="/scripts/util/Util.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/scripts/portal/Portlet.js"/>"></script>
		<link rel="stylesheet" type="text/css"
			href="<c:url value="/css/Common.css"/>" />
		<script type="text/javascript" src="<c:url value="/scripts/ext/build/locale/ext-lang-zh_CN.js"/>"></script>
			
			
	<style type="text/css">
        body .x-panel {
            margin-bottom:20px;
        }
        .icon-grid {
            background-image:url(../shared/icons/fam/grid.png) !important;
        }
        #button-grid .x-panel-body {
            border:1px solid #99bbe8;
            border-top:0 none;
        }
        .add {
            background-image:url(/faceye/scripts/ext/examples/shared/icons/fam/add.gif) !important;
        }
        .option {
            background-image:url(/faceye/scripts/ext/examples/shared/icons/fam/plugin.gif) !important;
        }
        .remove {
            background-image:url(/faceye/scripts/ext/examples/shared/icons/fam/delete.gif) !important;
        }
        .save {
            background-image:url(/faceye/scripts/ext/examples/shared/icons/save.gif) !important;
        }
    </style>
	</head>
	<script type="text/javascript">
Ext.onReady(com.faceye.portal.Portlet.init,com.faceye.portal.Portlet);
</script>
	<body>
		<div id="topic-grid"></div>
	
		<div id="sub-tree"></div>
	</body>
</html>
