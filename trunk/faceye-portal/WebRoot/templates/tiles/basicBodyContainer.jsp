<%@ include file="/templates/headers/header.jsp"%>
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/scripts/ext/resources/css/ext-all.css"/>" />
<!-- LIBS -->     
<script type="text/javascript" src="<c:url value="/scripts/ext/yui-utilities.js"/>"></script>  
<script type="text/javascript" src="<c:url value="/scripts/ext/ext-yui-adapter.js"/>"></script>    
<!-- ENDLIBS -->
    <script type="text/javascript" src="<c:url value="/scripts/ext/ext-all.js"/>"></script>


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
	    margin:5px;
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
    </style>
	<script type="text/javascript">
	Example = function(){
	        return {
	            init : function(){
	               var layout = new Ext.BorderLayout(document.body, {
	                    west: {
	                        split:true,
	                        initialSize: 200,
	                        titlebar: true,
	                        collapsible: true,
	                        minSize: 100,
	                        maxSize: 400
	                    },
	                    center: {
	                        autoScroll: false
	                    }
	                });
	                layout.beginUpdate();
	                
	                layout.add('west', new Ext.ContentPanel('nav', {title: 'Navigation', fitToFrame:true, closable:false}));
	                var innerLayout = new Ext.BorderLayout('content', {
	                    south: {
	                        split:true,
	                        initialSize: 200,
	                        minSize: 100,
	                        maxSize: 400,
	                        autoScroll:true,
	                        collapsible:true,
	                        titlebar: true
	                    },
	                    center: {
	                        autoScroll:true
	                    }
	                });
	                innerLayout.add('south', new Ext.ContentPanel('inner1', "More Information"));
	                innerLayout.add('center', new Ext.ContentPanel('inner2'));
	                layout.add('center', new Ext.NestedLayoutPanel(innerLayout));
	                layout.endUpdate();
	           }
	     };
	       
	}();
	Ext.EventManager.onDocumentReady(Example.init, Example, true);
	</script>
</head>
<body class="ytheme-gray">
<script type="text/javascript" src="<c:url value="/scripts/ext/examples.js"/>"></script><!-- EXAMPLES -->
<div id ="container">
  <div id="nav" class="x-layout-inactive-content">
      <ul>
        <li><a href="http://developer.yahoo.com/yui/" target="_blank"><img src="/blog/images/icons/yahoo.gif" width="16" height="16" border="0" align="absmiddle">&nbsp;Yahoo! UI Library</a></li>
	    <li><a href="http://www.ajaxian.com/" target="_blank"><img src="/blog/images/icons/ajaxian.gif" width="16" height="16" border="0" align="absmiddle">&nbsp;Ajaxian.com</a></li>
      </ul> 
  </div>
  <div id="content" class="x-layout-inactive-content"></div>
  <div id="inner1" class="x-layout-inactive-content">
        yyyyyyyyyyyyy
  </div>
  <div id="inner2" class="x-layout-inactive-content">
        yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy
  </div>
</div>






</body>
</html>