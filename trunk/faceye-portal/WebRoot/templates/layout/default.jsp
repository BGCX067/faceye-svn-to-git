<%@ include file="/templates/headers/ext-taglib-header.jsp"%>
<html>
	<head>
		<title>Home Page of Common Platform For Project</title>
		<script type="text/javascript" src="<c:url value="/scripts/common/Layout.js"/>"></script>
		<style type="text/css">
	html, body {
        font:normal 12px verdana;
        margin:0;
        padding:0;
        border:0 none;
        overflow:hidden;
        height:100%;
    }
	.x-panel-body p {
	    margin:5px;
	}
    .x-column-layout-ct .x-panel {
        margin-bottom:5px;
    }
    .x-column-layout-ct .x-panel-dd-spacer {
        margin-bottom:5px;
    }
    .settings {
        background-image:url(../shared/icons/fam/folder_wrench.png) !important;
    }
    .nav {
        background-image:url(../shared/icons/fam/folder_go.png) !important;
    }
    </style>
	<script type="text/javascript">
   Ext.onReady(Home.init,Home);   
	</script>
</head>
<body>
<div id="north">
    <p>www.faceye.com Manager.Desigh By Song Hai Peng.</p>
  </div>
  </body>
</html>